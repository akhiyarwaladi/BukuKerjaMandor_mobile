package com.example.dedra.bukukerjamandor.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dedra.bukukerjamandor.R;
import com.example.dedra.bukukerjamandor.view.TambahPegawaiActivity;

import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Dedra on 29/08/2017.
 */

public class PegawaiAdapter extends RecyclerView.Adapter<PegawaiAdapter.ViewHolder> {

    private ArrayList<String> rvData;
    Context mContext;
    public PegawaiAdapter(ArrayList<String> inputData) {
        rvData = inputData;
    }
    private static final int TAG_REQ_PEG = 1;

    @Override
    public int getItemViewType(int position) {
        return (position == rvData.size()) ? R.layout.recyclerview_tambah_pegawai : R.layout.view_rv_item;
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
        mContext = parent.getContext();
        View itemView;

        // membuat view baru
        if(viewType == R.layout.view_rv_item) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item,
                    parent, false);
        }
        else{
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_tambah_pegawai,
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
                    Intent i = new Intent(view.getContext(), TambahPegawaiActivity.class);
                    ((Activity) mContext).startActivityForResult(i, TAG_REQ_PEG);
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