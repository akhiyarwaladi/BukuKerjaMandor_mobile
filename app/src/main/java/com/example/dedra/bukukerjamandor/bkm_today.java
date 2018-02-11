package com.example.dedra.bukukerjamandor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Dedra on 03/08/2017.
 */

public class bkm_today extends Fragment {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;
    //int i=0;

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

        /*if (i == 0) {
            i++;
            getActivity().setTitle("BKM Hari Ini");
        }
        */

        //else {
            dataSet = new ArrayList<>();
            initDataset();

            rvView = (RecyclerView) view.findViewById(R.id.rv_main);

        /*
         * Kita menggunakan LinearLayoutManager untuk list standar
         * yang hanya berisi daftar item
         * disusun dari atas ke bawah
         */

            layoutManager = new LinearLayoutManager(this.getActivity());
            rvView.setLayoutManager(layoutManager);

            adapter = new RecyclerViewAdapter(dataSet);

            rvView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
            rvView.setAdapter(adapter);

            getActivity().setTitle("BKM Hari Ini");
        //}
    }

    

    private void initDataset() {

        /*
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        dataSet.add("Tanam Sawit");
        dataSet.add("Semprot Lalang");

    }
}
