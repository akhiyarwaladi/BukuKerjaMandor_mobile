package com.example.dedra.bukukerjamandor;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dedra.bukukerjamandor.view.TambahActivity;

import java.util.ArrayList;

/**
 * Created by Dedra on 29/08/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> rvData;

    public RecyclerViewAdapter(ArrayList<String> inputData) {
        rvData = inputData;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == rvData.size()) ? R.layout.recyclerview_tambah_aktivitas : R.layout.view_rv_item;
    }

    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // di tutorial ini kita hanya menggunakan data String untuk tiap item
        public TextView tvTitle;
        public TextView tvTambah;
        //public TextView tvSubtitle;

        public ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_title);
            tvTambah = (TextView) v.findViewById(R.id.tv_tambah);
            //tvSubtitle = (TextView) v.findViewById(R.id.tv_subtitle);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;

        // membuat view baru
        if(viewType == R.layout.view_rv_item) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item,
                    parent, false);
        }
        else{
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_tambah_aktivitas,
                    parent, false);
        }

        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == rvData.size()) {
            holder.tvTambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), TambahActivity.class);
                    view.getContext().startActivity(i);
                }
            });
        }
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut
        else {
            final String name = rvData.get(position);
            holder.tvTitle.setText(name);
            //holder.tvSubtitle.setText("Frau " + position);
        }
    }

}