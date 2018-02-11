package com.example.dedra.bukukerjamandor.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dedra.bukukerjamandor.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Dedra on 02/10/2017.
 */

public class AktivitasFragment extends Fragment {
    Button foto_btn, foto_btn2;
    private static final int PICK_IMAGE_REQUEST = 10012;
    private static final int PICK_PICT_REQUEST = 10013;
    ImageView imgPreview;
    Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tambah_aktivitas, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        foto_btn = (Button) getActivity().findViewById(R.id.tambah_foto_btn);
        foto_btn2 = (Button) getActivity().findViewById(R.id.tambah_foto_btn2);
        imgPreview = (ImageView)getActivity().findViewById(R.id.imageViewActivity);
        foto_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intCamera, 0);
                showFileChooser();
            }
        });
        foto_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCamera();
            }
        });

        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PICK_IMAGE_REQUEST){
            if(resultCode == RESULT_OK && data != null && data.getData() != null){
                Uri filePath = data.getData();
                try{

                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), filePath);
                    //imgPreview.setImageBitmap(bitmap);
                    imgPreview.setImageURI(filePath);
                    imgPreview.setVisibility(View.VISIBLE);



                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        else if(requestCode == PICK_PICT_REQUEST){
            Log.d("hasil kamera","oy");
            if(resultCode == RESULT_OK){
                Log.d("hasil kamera","ey");


                bitmap = (Bitmap)data.getExtras().get("data");;
                imgPreview.setImageBitmap(bitmap);
                imgPreview.setVisibility(View.VISIBLE);


            }
        }

    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void showCamera(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, PICK_PICT_REQUEST);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

}
