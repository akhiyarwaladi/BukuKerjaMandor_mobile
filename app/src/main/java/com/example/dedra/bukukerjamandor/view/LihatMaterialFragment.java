package com.example.dedra.bukukerjamandor.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dedra.bukukerjamandor.R;
import com.example.dedra.bukukerjamandor.RecyclerViewAdapter;
import com.example.dedra.bukukerjamandor.adapter.CustomItemClickListener;
import com.example.dedra.bukukerjamandor.adapter.LihatAktivitasAdapter;
import com.example.dedra.bukukerjamandor.adapter.MaterialAdapter;

import java.util.ArrayList;

/**
 * Created by Dedra on 26/11/2017.
 */

public class LihatMaterialFragment extends Fragment {
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;
    //int i=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bkm_hari_ini, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        adapter = new LihatAktivitasAdapter(this.getActivity(), dataSet, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getActivity(), "Posisi " + position, Toast.LENGTH_SHORT).show();
                // do what ever you want to do with it
            }
        });

        rvView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
        rvView.setAdapter(adapter);
    }

    private void initDataset() {

        /*
         * Tambahkan item ke dataset
         * dalam prakteknya bisa bermacam2
         * tidak hanya String seperti di kasus ini
         */
        dataSet.add("Material 1");
        dataSet.add("Material 2");
        dataSet.add("Material 3");
        dataSet.add("Material 4");
        dataSet.add("Material 5");
        dataSet.add("Material 6");

    }
}
