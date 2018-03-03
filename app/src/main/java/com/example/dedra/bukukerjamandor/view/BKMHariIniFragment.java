package com.example.dedra.bukukerjamandor.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dedra.bukukerjamandor.MainActivity;
import com.example.dedra.bukukerjamandor.adapter.AktivitasAdapter;
import com.example.dedra.bukukerjamandor.DividerItemDecoration;
import com.example.dedra.bukukerjamandor.R;
import com.example.dedra.bukukerjamandor.app.Config;
import com.example.dedra.bukukerjamandor.helper.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Dedra on 02/10/2017.
 */

public class BKMHariIniFragment extends Fragment {
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;

    private DatabaseHelper db;
    private String TAG = MainActivity.class.getSimpleName();
    String apiKey,userId;
    int i=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*if(i==0) {
            View view = inflater.inflate(R.layout.bkm_today_null, container, false);
            return view;
        }*/

        //else{
        View view = inflater.inflate(R.layout.fragment_bkm_hari_ini, container, false);
        return view;
        //}

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new DatabaseHelper(getActivity());

        final SharedPreferences sharedPreferencesUid= this.getActivity().getSharedPreferences(Config.SHARED_PREF_ID,
                Context.MODE_PRIVATE);
        final SharedPreferences sharedPreferencesApi = this.getActivity().getSharedPreferences(Config.SHARED_PREF_API,
                Context.MODE_PRIVATE);
        userId = sharedPreferencesUid.getString(Config.USERID_SHARED_PREF, "");
        apiKey = sharedPreferencesApi.getString(Config.APIKEY_SHARED_PREF, "");

        Log.d("uid", userId);
        Log.d("api", apiKey);


        /*if (i == 0) {
            i++;
            getActivity().setTitle("BKM Hari Ini");
        }
        */

        //else {
        dataSet = new ArrayList<>();
        //initDataset();

        rvView = (RecyclerView) view.findViewById(R.id.rv_main);

        /*
         * Kita menggunakan LinearLayoutManager untuk list standar
         * yang hanya berisi daftar item
         * disusun dari atas ke bawah
         */

        layoutManager = new LinearLayoutManager(this.getActivity());
        rvView.setLayoutManager(layoutManager);

        adapter = new AktivitasAdapter(dataSet);

        rvView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        rvView.setAdapter(adapter);

//        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), TambahActivity.class);
//                getActivity().startActivity(i);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        getActivity().setTitle("BKM Hari Ini");
        //}
    }

/*    private void initDataset() {

        *//*
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         *//*
        dataSet.add("Tanam Sawit");
        dataSet.add("Semprot Lalang");

    }*/
}
