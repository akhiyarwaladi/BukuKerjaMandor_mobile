package com.example.dedra.bukukerjamandor.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dedra.bukukerjamandor.R;
import com.example.dedra.bukukerjamandor.view.TambahMaterialActivity;

import java.util.ArrayList;

/**
 * Created by Dedra on 29/08/2017.
 */

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder> {

    private ArrayList<String> rvData;

    public MaterialAdapter(ArrayList<String> inputData) {
        rvData = inputData;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.tambah_rv_material_item;
        // return (position == rvData.size()) ? R.layout.recyclerview_tambah_material: R.layout.tambah_rv_material_item;
    }

    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // di tutorial ini kita hanya menggunakan data String untuk tiap item
        public TextView nama_material;
        //public TextView tvTambah;
        //public TextView tvSubtitle;

        public ViewHolder(View v) {
            super(v);
            nama_material = (TextView) v.findViewById(R.id.nama_material);
            //tvTambah = (TextView) v.findViewById(R.id.tv_tambah);
            //tvSubtitle = (TextView) v.findViewById(R.id.tv_subtitle);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;

        // membuat view baru
//        if(viewType == R.layout.tambah_rv_material_item) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tambah_rv_material_item,
                    parent, false);
//        }
//        else{
//            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_tambah_material,
//                    parent, false);
//        }

        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        if (position == rvData.size()) {
//            holder.tvTambah.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(view.getContext(), TambahMaterialActivity.class);
//                    view.getContext().startActivity(i);
//                }
//            });
//        }
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut
//        else {
            final String name = rvData.get(position);
            holder.nama_material.setText(name);
            //holder.tvSubtitle.setText("Frau " + position);
//        }
    }

}