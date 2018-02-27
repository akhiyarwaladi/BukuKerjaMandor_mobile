package com.example.dedra.bukukerjamandor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dedra.bukukerjamandor.app.Config;
import com.example.dedra.bukukerjamandor.app.EndPoint;
import com.example.dedra.bukukerjamandor.app.MyApplication;
import com.example.dedra.bukukerjamandor.utils.Keys;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

public class login extends AppCompatActivity {


    @BindView(R.id.inputUsername)
    EditText _usernameText;
    @BindView(R.id.inputPassword)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;

    private String TAG = login.class.getSimpleName();
    ProgressDialog pDialog;
    private boolean loggedIn = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Otentikasi...");
        pDialog.setCancelable(true);

//        _loginButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                //login_1();
//                checkLoginWithPass();
//                showPDialog();
//            }
//        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if (loggedIn) {
            //We will start the Profile Activity
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
        }
    }


    @OnClick(R.id.btn_login)
    public void login() {

        checkLoginWithPass();
        showPDialog();
    }

//    @OnClick(R.id.btnLinkToRegisterScreen)
//    public void register(){
//        Intent intent = new Intent(login.this, register.class);
//        startActivity(intent);
//    }

    private void checkLoginWithPass() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Keys.SHARED_PREFS_KEY, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Keys.USERNAME_SPK, _usernameText.getText().toString());

        final String username = _usernameText.getText().toString();
        final String password = _passwordText.getText().toString();
        //authenticate user
        StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoint.URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(login.this,response,Toast.LENGTH_LONG).show();

                        try {
                            JSONObject obj = new JSONObject(response);

                            boolean b = (boolean) obj.get("error");
                            if (b){
                                Toast.makeText(login.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            }
                            else {

                                String username = String.valueOf(obj.get("username"));
                                String apikey = String.valueOf(obj.get("apiKey"));
                                //String emailaddress = String.valueOf(obj.get("email"));

                                SharedPreferences user = login.this.getSharedPreferences(Config.SHARED_PREF_NAME,
                                        Context.MODE_PRIVATE);
                                SharedPreferences apiKey = login.this.getSharedPreferences(Config.SHARED_PREF_API,
                                        Context.MODE_PRIVATE);

                                //SharedPreferences email = login.this.getSharedPreferences(Config.SHARED_PREF_EMAIL,
                                //        Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = user.edit();
                                SharedPreferences.Editor editor1 = apiKey.edit();

                                //SharedPreferences.Editor editor3 = email.edit();


                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                editor1.putBoolean(Config.API_SHARED_PREF, true);

                                editor.putString(Config.USERNAME_SHARED_PREF, username);
                                editor1.putString(Config.APIKEY_SHARED_PREF, apikey);

                                //editor3.putString(Config.EMAILADDRESS_SHARED_PREF, emailaddress);


                                editor.commit();
                                editor1.commit();

                                //editor3.commit();


                                Log.d("user", username);
                                Log.d("api", apikey);

//                                String fcmregid = FirebaseInstanceId.getInstance().getToken();
//                                updateUser(userid, fcmregid, apikey);
                                Intent i = new Intent(login.this, MainActivity.class);
                                startActivity(i);
                                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                hidePDialog();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("List", "data not successfull access");
                            hidePDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login.this, "Username or Password is empty" ,Toast.LENGTH_LONG).show();
                        hidePDialog();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username", username);
                //params.put("email", email);
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


/*    public void login_1() {
        Log.d(TAG, "Login1");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(login.this,
                R.style.Theme_AppCompat_Light_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Otentikasi...");
        progressDialog.show();

        _usernameText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();

        if(username.equals("ADMIN") && password.equals("admin")){
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginSuccess();
                            // onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 2000);
        }

        else{
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onLoginSuccess or onLoginFailed
                            onLoginFailed();
                            // onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 2000);
        }

    }*/


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        super.finish();

    }

/*    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        startActivity(new Intent(login.this, MainActivity.class));
    }

    public void onLoginFailed() {
        final Toast t = Toast.makeText(getBaseContext(), "Gagal Log Masuk", Toast.LENGTH_LONG);

        t.show();
        _loginButton.setEnabled(true);

        new CountDownTimer(1500, 1000)
        {
            public void onTick(long millisUntilFinished) {t.show();}
            public void onFinish() {t.cancel();}
        }.start();
    }

    public boolean validate() {
        boolean valid = true;

        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (username.isEmpty() || username.length() < 3) {
            _usernameText.setError("masukkan username yang valid");
            valid = false;
        } else {
            _usernameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("masukkan password yang sesuai");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }*/

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

/*
    public static boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return true;
    }

    public static String encodeEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }
*/
}