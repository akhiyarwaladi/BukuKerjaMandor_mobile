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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.example.dedra.bukukerjamandor.helper.DatabaseHelper;
import com.example.dedra.bukukerjamandor.model.Material;
import com.example.dedra.bukukerjamandor.model.Pegawai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Dedra on 09/10/2017.
 */

public class TambahPegawaiActivity extends AppCompatActivity {
    DatabaseHelper db;
    private String TAG = TambahActivity.class.getSimpleName();
//    List<Pegawai> allPegawai = new ArrayList<Pegawai>();
//    String apiKey, userId;
//    ArrayAdapter<String> adapter;
//    List <String> namaAnggota;

    TextView hasil_std;
    private Spinner spNamaAnggota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // database handler
        db = new DatabaseHelper(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tambah_pegawai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        final SharedPreferences sharedPreferencesUid= this.getSharedPreferences(Config.SHARED_PREF_ID,
//                Context.MODE_PRIVATE);
//        final SharedPreferences sharedPreferencesApi = this.getSharedPreferences(Config.SHARED_PREF_API,
//                Context.MODE_PRIVATE);
//        userId = sharedPreferencesUid.getString(Config.USERID_SHARED_PREF, "");
//        apiKey = sharedPreferencesApi.getString(Config.APIKEY_SHARED_PREF, "");

        spNamaAnggota = (Spinner) findViewById(R.id.spinner_trg_pegawai);
        hasil_std = (TextView) findViewById(R.id.HasilKerjaStandar);

        loadSpinnerData();
        spNamaAnggota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String label = parent.getItemAtPosition(position).toString();

                String[] parts = label.split(Pattern.quote(" - "));
                String id_pegawai = parts[0];
                String nama_pegawai = parts[1];

                Log.d("Pegawai yang dipilih: ", nama_pegawai);

                hasil_std.setText(db.getHasilSTD(id_pegawai));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void loadSpinnerData(){
        // Spinner Drop down elements
        List<String> pegawai = db.getPegawai();

        // Creating adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, pegawai);

        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner
        spNamaAnggota.setAdapter(adapter);
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

