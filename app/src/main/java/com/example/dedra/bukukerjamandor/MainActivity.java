package com.example.dedra.bukukerjamandor;

/**
 * Created by Dedra on 01/08/2017.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dedra.bukukerjamandor.app.Config;
import com.example.dedra.bukukerjamandor.helper.DatabaseHelper;
import com.example.dedra.bukukerjamandor.view.BKMHariIniFragment;
import com.example.dedra.bukukerjamandor.view.KalenderFragment;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper db;
    String username;
    TextView namaUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.content_main, new BKMHariIniFragment());
            tx.commit();
        }

        else{
            displaySelectedScreen(id);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        db.closeDB();
        ////////////////////////// SET NAMA USER ///////////////////////////////
        View headerView = navigationView.getHeaderView(0);
        namaUser = (TextView) headerView.findViewById(R.id.textViewUsername);
        final SharedPreferences sharedPreferencesName= getSharedPreferences(Config.SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        username = sharedPreferencesName.getString(Config.USERNAME_SHARED_PREF, "");
        namaUser.setText(username);
        ///////////////////////////////////////////////////////////////////////////
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            db.closeDB();
            super.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySelectedScreen(int id){
        Fragment fragment;

        switch(id){
            case R.id.nav_BKM_today:
                fragment = new BKMHariIniFragment();
                break;
            case R.id.nav_calendar:
                fragment = new KalenderFragment();
                break;
            case R.id.nav_logout:
                fragment = new logout();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Keluar dari aplikasi?");
                alertDialogBuilder.setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                //Getting out sharedpreferences
                                SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                //Getting editor
                                SharedPreferences.Editor editor = preferences.edit();

                                //Puting the value false for loggedin
                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                                //Putting blank value to email
                                editor.putString(Config.APIKEY_SHARED_PREF, "");
                                editor.clear();

                                //Saving the sharedpreferences
                                editor.commit();

                                finish();
                                //Starting login activity
                                //Intent intent = new Intent(MainActivity.this, login.class);
                                //startActivity(intent);
                            }
                        });

                alertDialogBuilder.setNegativeButton("Tidak",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

                //Showing the alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            default:
                fragment = new BKMHariIniFragment();
                break;
        }

        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }

}

