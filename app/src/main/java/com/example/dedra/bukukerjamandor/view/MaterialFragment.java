package com.example.dedra.bukukerjamandor.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dedra.bukukerjamandor.R;
import com.example.dedra.bukukerjamandor.adapter.MaterialAdapter;
import com.example.dedra.bukukerjamandor.app.Config;
import com.example.dedra.bukukerjamandor.app.EndPoint;
import com.example.dedra.bukukerjamandor.app.MyApplication;
import com.example.dedra.bukukerjamandor.model.Material;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dedra on 02/10/2017.
 */

public class MaterialFragment extends Fragment{
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;
    //int i=0;
    private String TAG = TambahActivity.class.getSimpleName();
    List<Material> allMaterial;
    String apiKey, userId;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // load data here
        }else{
            // fragment is no longer visible
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bkm_hari_ini, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataSet = new ArrayList<>();
        final SharedPreferences sharedPreferencesUid= this.getActivity().getSharedPreferences(Config.SHARED_PREF_ID,
                Context.MODE_PRIVATE);
        final SharedPreferences sharedPreferencesApi = this.getActivity().getSharedPreferences(Config.SHARED_PREF_API,
                Context.MODE_PRIVATE);
        userId = sharedPreferencesUid.getString(Config.USERID_SHARED_PREF, "");
        apiKey = sharedPreferencesApi.getString(Config.APIKEY_SHARED_PREF, "");

        Log.d("uid", userId);
        Log.d("api", apiKey);

        initDataset();

        rvView = (RecyclerView) view.findViewById(R.id.rv_main);
        allMaterial = new ArrayList<>();

        adapter = new MaterialAdapter(this.getActivity(), allMaterial);

        /*
         * Kita menggunakan LinearLayoutManager untuk list standar
         * yang hanya berisi daftar item
         * disusun dari atas ke bawah
         */

        layoutManager = new LinearLayoutManager(this.getActivity());
        rvView.setLayoutManager(layoutManager);


        rvView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        rvView.setAdapter(adapter);
        getMaterial();
    }

    public void getMaterial(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                EndPoint.URL_MATERIAL, new Response.Listener<String>() {
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
                            Log.i("dataDapat",""+dataObj);
                            String kode_material = dataObj.getString("kode_material");
                            String nama_material = dataObj.getString("nama_material");
                            String unit = dataObj.getString("unit");

                            Material material = new Material( kode_material, nama_material, unit );
                            allMaterial.add(material);
                        }
                        adapter.notifyDataSetChanged();

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
                Toast.makeText(getActivity(), "Volley errror: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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


    private void initDataset() {

        /*
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        dataSet.add("Material 1");
        dataSet.add("Material 2");
        //dataSet.add("Material 3");
        //dataSet.add("Material 4");
        //dataSet.add("Material 5");
        //dataSet.add("Material 6");

    }
}
