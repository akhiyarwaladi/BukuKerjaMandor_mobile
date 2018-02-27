package com.example.dedra.bukukerjamandor.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.dedra.bukukerjamandor.model.Aktivitas;
import com.example.dedra.bukukerjamandor.model.Material;
import com.example.dedra.bukukerjamandor.model.RKHAktivitas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Dedra on 02/10/2017.
 */

public class AktivitasFragment extends Fragment {
    Button foto_btn, foto_btn2;
    private Spinner spAktivitas, spSektorTanam, spBlokTanam;
    List<String> liAktivitas, liSektorTanam, liBlokTanam;
    ArrayAdapter<String> adapter, adapter1, adapter2;
    List<Aktivitas> allAktivitas;
    List<RKHAktivitas> allRKHAktivitas;
    private String TAG = TambahActivity.class.getSimpleName();
    private static final int PICK_IMAGE_REQUEST = 10012;
    private static final int PICK_PICT_REQUEST = 10013;
    ImageView imgPreview;
    Bitmap bitmap;
    String apiKey, userId;
    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tambah_aktivitas, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        foto_btn = (Button) getActivity().findViewById(R.id.tambah_foto_btn);
        foto_btn2 = (Button) getActivity().findViewById(R.id.tambah_foto_btn2);
        imgPreview = (ImageView) getActivity().findViewById(R.id.imageViewActivity);
        spAktivitas = (Spinner) getActivity().findViewById(R.id.spinner_aktivitas);
        spSektorTanam = (Spinner) getActivity().findViewById(R.id.spinner_sektor_tanam);
        spBlokTanam = (Spinner) getActivity().findViewById(R.id.spinner_blok_tanam);
        foto_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intCamera, 0);
                showFileChooser();
            }
        });
        foto_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCamera();
            }
        });

        final SharedPreferences sharedPreferencesUid= this.getActivity().getSharedPreferences(Config.SHARED_PREF_ID,
                Context.MODE_PRIVATE);
        final SharedPreferences sharedPreferencesApi = this.getActivity().getSharedPreferences(Config.SHARED_PREF_API,
                Context.MODE_PRIVATE);
        userId = sharedPreferencesUid.getString(Config.USERID_SHARED_PREF, "");
        apiKey = sharedPreferencesApi.getString(Config.APIKEY_SHARED_PREF, "");

        Log.d("uid", userId);
        Log.d("api", apiKey);
        allAktivitas = new ArrayList<>();
        liAktivitas = new ArrayList<String>();
        getAktivitas();
        adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, liAktivitas){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                    ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spAktivitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getActivity(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAktivitas.setAdapter(adapter);


        /*------------- Sektor Tanam -------------*/
        allRKHAktivitas= new ArrayList<>();
        liSektorTanam = new ArrayList<String>();
        getSektorTanam();
        adapter1 = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, liSektorTanam){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spSektorTanam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getActivity(), "Selected : " + selectedItemText,
                                    Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSektorTanam.setAdapter(adapter1);

        /*------------- Sektor Tanam -------------*/
        liBlokTanam = new ArrayList<String>();
        getBlokTanam();
        adapter2 = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, liBlokTanam){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spBlokTanam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getActivity(), "Selected : " + selectedItemText,
                                    Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBlokTanam.setAdapter(adapter2);

    }

    public void getAktivitas(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                EndPoint.URL_AKTIVITAS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error") == false) {
                        //Toast.makeText(DashboardActivity.this, "Data dapat"+response, Toast.LENGTH_SHORT).show();
                        JSONArray data = obj.getJSONArray("tasks");

                        liAktivitas.add("Pilih Aktivitas yang Telah Dilakukan");
                        for (i = 0; i < data.length(); i++) {
                            JSONObject dataObj = (JSONObject) data.get(i);
                            Log.i("dataAktivitasDapat", "" + dataObj);
                            String kode_aktivitas = dataObj.getString("kode_aktivitas");
                            String nama_aktivitas = dataObj.getString("nama_aktivitas");

                            Aktivitas aktivitas = new Aktivitas(kode_aktivitas, nama_aktivitas);

                            String spinnerText = kode_aktivitas + " - " + nama_aktivitas;

                            allAktivitas.add(aktivitas);
                            liAktivitas.add(spinnerText);
                            }

                    } else {
                        // error in fetching data
                        Toast.makeText(getActivity(), "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(getActivity(), "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(getActivity(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void getSektorTanam(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                EndPoint.URL_RKH_AKTIVITAS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error") == false) {
                        //Toast.makeText(DashboardActivity.this, "Data dapat"+response, Toast.LENGTH_SHORT).show();
                        JSONArray data = obj.getJSONArray("tasks");

                        liSektorTanam.add("Pilih Sektor Tanam");
                        for (i = 0; i < data.length(); i++) {
                            JSONObject dataObj = (JSONObject) data.get(i);
                            Log.i("dataSektorTanamDapat", "" + dataObj);
                            String no_rkh = dataObj.getString("no_rkh");
                            String no_aktivitas = dataObj.getString("no_aktivitas");
                            String kode_aktivitas = dataObj.getString("kode_aktivitas");
                            String sektor_tanam = dataObj.getString("sektor_tanam");
                            String blok_tanam = dataObj.getString("blok_tanam");

                            RKHAktivitas rkhaktivitas = new RKHAktivitas(no_rkh, no_aktivitas,
                                    kode_aktivitas, sektor_tanam, blok_tanam);

                            String spinnerText2 = sektor_tanam;

                            allRKHAktivitas.add(rkhaktivitas);
                            liSektorTanam.add(spinnerText2);
                        }

                    } else {
                        // error in fetching data
                        Toast.makeText(getActivity(), "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(getActivity(), "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(getActivity(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void getBlokTanam(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                EndPoint.URL_RKH_AKTIVITAS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error") == false) {
                        //Toast.makeText(DashboardActivity.this, "Data dapat"+response, Toast.LENGTH_SHORT).show();
                        JSONArray data = obj.getJSONArray("tasks");

                        liBlokTanam.add("Pilih Blok Tanam");
                        for (i = 0; i < data.length(); i++) {
                            JSONObject dataObj = (JSONObject) data.get(i);
                            Log.i("dataSektorTanamDapat", "" + dataObj);
                            String no_rkh = dataObj.getString("no_rkh");
                            String no_aktivitas = dataObj.getString("no_aktivitas");
                            String kode_aktivitas = dataObj.getString("kode_aktivitas");
                            String sektor_tanam = dataObj.getString("sektor_tanam");
                            String blok_tanam = dataObj.getString("blok_tanam");

                            RKHAktivitas rkhaktivitas = new RKHAktivitas(no_rkh, no_aktivitas,
                                    kode_aktivitas, sektor_tanam, blok_tanam);

                            String spinnerText3 = blok_tanam;

                            allRKHAktivitas.add(rkhaktivitas);
                            liBlokTanam.add(spinnerText3);
                        }

                    } else {
                        // error in fetching data
                        Toast.makeText(getActivity(), "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(getActivity(), "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(getActivity(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PICK_IMAGE_REQUEST){
            if(resultCode == RESULT_OK && data != null && data.getData() != null){
                Uri filePath = data.getData();
                try{

                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), filePath);
                    //imgPreview.setImageBitmap(bitmap);
                    imgPreview.setImageURI(filePath);
                    imgPreview.setVisibility(View.VISIBLE);

                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        else if(requestCode == PICK_PICT_REQUEST){
            Log.d("hasil kamera","oy");
            if(resultCode == RESULT_OK){
                Log.d("hasil kamera","ey");

                bitmap = (Bitmap)data.getExtras().get("data");;
                imgPreview.setImageBitmap(bitmap);
                imgPreview.setVisibility(View.VISIBLE);

            }
        }

    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void showCamera(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, PICK_PICT_REQUEST);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

}
