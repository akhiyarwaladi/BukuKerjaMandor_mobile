package com.example.dedra.bukukerjamandor.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dedra.bukukerjamandor.R;
import com.example.dedra.bukukerjamandor.app.Config;
import com.example.dedra.bukukerjamandor.app.EndPoint;
import com.example.dedra.bukukerjamandor.app.MyApplication;
import com.example.dedra.bukukerjamandor.model.Material;
import com.example.dedra.bukukerjamandor.model.Pegawai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dedra on 09/10/2017.
 */

public class TambahPegawaiActivity extends AppCompatActivity {
    private String TAG = TambahActivity.class.getSimpleName();
    List<Pegawai> allPegawai = new ArrayList<Pegawai>();
    String apiKey, userId;
    ArrayAdapter<String> adapter;
    List <String> namaAnggota;
    private Spinner spNamaAnggota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tambah_pegawai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final SharedPreferences sharedPreferencesUid= this.getSharedPreferences(Config.SHARED_PREF_ID,
                Context.MODE_PRIVATE);
        final SharedPreferences sharedPreferencesApi = this.getSharedPreferences(Config.SHARED_PREF_API,
                Context.MODE_PRIVATE);
        userId = sharedPreferencesUid.getString(Config.USERID_SHARED_PREF, "");
        apiKey = sharedPreferencesApi.getString(Config.APIKEY_SHARED_PREF, "");
        spNamaAnggota = (Spinner) findViewById(R.id.spinner_trg_pegawai);

        namaAnggota = new ArrayList<String>();
        getAllPegawai();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, namaAnggota);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNamaAnggota.setAdapter(adapter);
    }

    public void getAllPegawai(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                EndPoint.URL_PEGAWAI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error") == false) {
                        //Toast.makeText(DashboardActivity.this, "Data dapat"+response, Toast.LENGTH_SHORT).show();
                        JSONArray data = obj.getJSONArray("tasks");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject dataObj = (JSONObject) data.get(i);
                            Log.i("dataPegawaiDapat",""+dataObj);
                            String id_pegawai = dataObj.getString("id_pegawai");
                            String nama_pegawai = dataObj.getString("nama_pegawai");
                            String panggilan_pegawai = dataObj.getString("panggilan_pegawai");
                            String jabatan = dataObj.getString("jabatan");
                            String status = dataObj.getString("status");
                            String kode_mandoran = dataObj.getString("kode_mandoran");

                            Pegawai pegawai = new Pegawai( id_pegawai, nama_pegawai, panggilan_pegawai, jabatan, status, kode_mandoran );
                            String spinnerText = id_pegawai + " - " + nama_pegawai + " (" + panggilan_pegawai + ")";

                            namaAnggota.add(spinnerText);
                            allPegawai.add(pegawai);
                        }

                    } else {
                        // error in fetching data
                        Toast.makeText(TambahPegawaiActivity.this, "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(TambahPegawaiActivity.this, "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(TambahPegawaiActivity.this, "Volley errror: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map headers = new HashMap();
                headers.put("Authorization", apiKey);
                headers.put("x-snow-token", "SECRET_API_KEY");

                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        //Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case android.R.id.home:{
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Hapus perubahan ini?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Hapus",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                onBackPressed();
                            }
                        });

                builder1.setNegativeButton(
                        "Batal",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                return true;
            }
            case R.id.action_done:{
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nama", "haha");
                setResult(RESULT_OK, resultIntent);
                finish();
                //onBackPressed();
                //return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}

