package com.example.dedra.bukukerjamandor.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dedra.bukukerjamandor.R;

import java.util.ArrayList;

/**
 * Created by Dedra on 29/08/2017.
 */

public class LihatAktivitasAdapter extends RecyclerView.Adapter<LihatAktivitasAdapter.ViewHolder> {

    Context mContext;
    CustomItemClickListener listener;
    private ArrayList<String> rvData;

    public LihatAktivitasAdapter(Context mContext, ArrayList<String> inputData,
                                 CustomItemClickListener listener) {
        this.rvData = inputData;
        this.mContext = mContext;
        this.listener = listener;
    }

    //@Override
    //public int getItemViewType(int position) {
    //    return R.layout.view_rv_item;
    //}

    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // di tutorial ini kita hanya menggunakan data String untuk tiap item
        public TextView tvTitle;
        //public TextView tvTambah;
        //public TextView tvSubtitle;

        public ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_title);
            //tvSubtitle = (TextView) v.findViewById(R.id.tv_subtitle);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item,
                parent, false);
        final ViewHolder mViewHolder = new ViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listener.onItemClick(v, mViewHolder.getPosition());
            }
        });
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut

            final String name = rvData.get(position);
            holder.tvTitle.setText(name);
            //holder.tvSubtitle.setText("Frau " + position);
    }
}
