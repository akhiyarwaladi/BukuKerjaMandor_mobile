package com.example.dedra.bukukerjamandor.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dedra.bukukerjamandor.R;
import com.example.dedra.bukukerjamandor.app.Config;
import com.example.dedra.bukukerjamandor.app.EndPoint;
import com.example.dedra.bukukerjamandor.app.MyApplication;
import com.example.dedra.bukukerjamandor.helper.DatabaseHelper;
import com.example.dedra.bukukerjamandor.model.Aktivitas;
import com.example.dedra.bukukerjamandor.model.Material;
import com.example.dedra.bukukerjamandor.model.RKHAktivitas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Dedra on 02/10/2017.
 */

public class AktivitasFragment extends Fragment {
    DatabaseHelper db;
    Button foto_btn, foto_btn2;
    private Spinner spAktivitas, spSektorTanam, spBlokTanam;
    List<String> liAktivitas, liSektorTanam, liBlokTanam;
    ArrayAdapter<String> adapter1, adapter2;
    List<Aktivitas> allAktivitas;
    List<RKHAktivitas> allRKHAktivitas;
    private String TAG = TambahActivity.class.getSimpleName();
    private static final int PICK_IMAGE_REQUEST = 10012;
    private static final int PICK_PICT_REQUEST = 10013;
    ImageView imgPreview;
    Bitmap bitmap;
    String kode_aktivitas;
    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tambah_aktivitas, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // database handler
        db = new DatabaseHelper(getActivity());

        foto_btn = (Button) getActivity().findViewById(R.id.tambah_foto_btn);
        foto_btn2 = (Button) getActivity().findViewById(R.id.tambah_foto_btn2);
        imgPreview = (ImageView) getActivity().findViewById(R.id.imageViewActivity);
        spAktivitas = (Spinner) getActivity().findViewById(R.id.spinner_aktivitas);
        spSektorTanam = (Spinner) getActivity().findViewById(R.id.spinner_sektor_tanam);
        spBlokTanam = (Spinner) getActivity().findViewById(R.id.spinner_blok_tanam);
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

        loadSpinnerAktivitas();
        spAktivitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String label = parent.getItemAtPosition(position).toString();

                String[] parts = label.split(Pattern.quote(" - "));
                kode_aktivitas = parts[0];

                // Showing selected spinner item
                Log.d("Aktivitas: ", label);
                Log.d("Kode Aktivitas: ", kode_aktivitas);

                loadSpinnerSektorBlokTanam();
                spSektorTanam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // On selecting a spinner item
                        String label = parent.getItemAtPosition(position).toString();

                        // Showing selected spinner item
                        Log.d("Sektor tanam: ", label);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                spBlokTanam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // On selecting a spinner item
                        String label = parent.getItemAtPosition(position).toString();

                        // Showing selected spinner item
                        Log.d("Blok Tanam: ", label);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void loadSpinnerAktivitas(){
        // Spinner Drop down elements
        List<String> aktivitas = db.getAktivitas();

        // Creating adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, aktivitas);

        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spAktivitas.setAdapter(adapter);
    }

    public void loadSpinnerSektorBlokTanam(){
        // Spinner Drop down elements
        List<String> sektor_tanam = db.getSektorTanam(kode_aktivitas);
        List<String> blok_tanam = db.getBlokTanam(kode_aktivitas);

        // Creating adapter for spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, sektor_tanam);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, blok_tanam);

        // Drop down layout style - list view with radio button
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spSektorTanam.setAdapter(adapter1);
        spBlokTanam.setAdapter(adapter2);
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
