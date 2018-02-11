package com.example.dedra.bukukerjamandor.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dedra.bukukerjamandor.DividerItemDecoration;
import com.example.dedra.bukukerjamandor.R;
import com.example.dedra.bukukerjamandor.RecyclerViewAdapter;
import com.example.dedra.bukukerjamandor.adapter.CustomItemClickListener;
import com.example.dedra.bukukerjamandor.adapter.LihatAktivitasAdapter;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.prolificinteractive.materialcalendarview.R.string.calendar;

/**
 * Created by Dedra on 10/10/2017.
 */

public class LihatFragment extends Fragment {

    private RecyclerView rvView;
    //private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;
    LihatAktivitasAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*if(i==0) {
            View view = inflater.inflate(R.layout.bkm_today_null, container, false);
            return view;
        }*/

        //else{
        View view = inflater.inflate(R.layout.fragment_lihat_aktivitas, container, false);
        return view;
        //}

    }

    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataSet = new ArrayList<>();
        initDataset();

        rvView = (RecyclerView) getView().findViewById(R.id.rv_main);

        /*
         * Kita menggunakan LinearLayoutManager untuk list standar
         * yang hanya berisi daftar item
         * disusun dari atas ke bawah
         */

        layoutManager = new LinearLayoutManager(this.getActivity());
        rvView.setLayoutManager(layoutManager);

        rvView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));

        adapter = new LihatAktivitasAdapter(this.getActivity(), dataSet, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getActivity(), "Posisi " + position, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), DetailAktivitasActivity.class);
                v.getContext().startActivity(i);
                // do what ever you want to do with it
            }
        });

        rvView.setAdapter(adapter);
        getActivity().setTitle("Lihat Aktivitas");
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
