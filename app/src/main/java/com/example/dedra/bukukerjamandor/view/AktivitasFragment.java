package com.example.dedra.bukukerjamandor.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dedra.bukukerjamandor.R;


/**
 * Created by Dedra on 02/10/2017.
 */

public class AktivitasFragment extends Fragment {
    Button foto_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tambah_aktivitas, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        foto_btn = (Button) getActivity().findViewById(R.id.tambah_foto_btn);
        foto_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intCamera, 0);
            }
        });

        super.onViewCreated(view, savedInstanceState);

    }

}
