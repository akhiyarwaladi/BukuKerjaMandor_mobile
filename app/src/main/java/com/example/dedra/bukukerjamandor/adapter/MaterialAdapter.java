package com.example.dedra.bukukerjamandor.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dedra.bukukerjamandor.R;
import com.example.dedra.bukukerjamandor.model.Material;
import com.example.dedra.bukukerjamandor.view.TambahMaterialActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dedra on 29/08/2017.
 */

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MyViewHolder> {

    private List<Material> rvData;
    private Context mContext;
    private View itemView;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView nama_material, unit;
        public MyViewHolder(View view) {
            super(view);
            nama_material = (TextView) view.findViewById(R.id.nama_material);
            unit = (TextView) view.findViewById(R.id.label_satuan_kuantitas);
        }
    }
    public MaterialAdapter(Context mContext, List<Material> inputData) {
        this.mContext = mContext;
        this.rvData = inputData;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tambah_rv_material_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Material material = rvData.get(position);
        holder.nama_material.setText(material.getNama_material());
        holder.unit.setText(material.getUnit());

    }

    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size();
    }



}