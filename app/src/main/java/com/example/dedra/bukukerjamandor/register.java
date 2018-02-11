package com.example.dedra.bukukerjamandor;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import android.content.Intent;
import android.support.annotation.BinderThread;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dedra.bukukerjamandor.app.EndPoint;
import com.example.dedra.bukukerjamandor.app.MyApplication;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class register extends AppCompatActivity {
    ProgressDialog pDialog;

    @BindView(R.id.name)
    EditText etName;

    @BindView(R.id.email)
    EditText etEmail;

    @BindView(R.id.password)
    EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(true);
    }


    @OnClick(R.id.btnRegister)
    public void register() {

        Register();
        showPDialog();
    }

    @OnClick(R.id.btnLinkToLoginScreen)
    public void login(){
        Intent intent = new Intent(register.this, login.class);
        startActivity(intent);
    }
    private void Register(){
        final String name = etName.getText().toString();
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoint.URL_REGISTER,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            boolean b = (boolean) obj.get("error");
                            if (b){
                                Toast.makeText(register.this, "Username already exist", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                Intent i = new Intent(register.this, login.class);
                                startActivity(i);
                                Toast.makeText(register.this, "Succesfully registered" ,Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("List", "data not successfull access"); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(register.this,"Username or Password is empty",Toast.LENGTH_LONG).show();
                    }

                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put("name", name);
                params.put("email", email);
                params.put("password", password);

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map headers = new HashMap();
                headers.put("x-snow-token", "SECRET_API_KEY");

                return headers;
            }
        };
        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }

    private void showPDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hidePDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }

}
