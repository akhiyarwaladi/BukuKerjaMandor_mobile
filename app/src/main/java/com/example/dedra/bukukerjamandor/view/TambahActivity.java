package com.example.dedra.bukukerjamandor.view;

/**
 * Created by Dedra on 02/10/2017.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dedra.bukukerjamandor.MainActivity;
import com.example.dedra.bukukerjamandor.R;

public class TambahActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView progress1, progress2, progress3, idle1, idle2, idle3;
    private Button kembaliBtn, lanjutBtn, simpanBtn;
    private int page = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tambah_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager_fragment);

        /** Getting fragment manager */
        FragmentManager fm = getSupportFragmentManager();

        initComponentViews();

        /** Instantiating FragmentPagerAdapter */
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(fm);

        /** Setting the pagerAdapter to the pager object */
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(page);
        pager.addOnPageChangeListener(listener);

        initOnImplementsView();

    }

    private void initComponentViews(){
        progress1 = (ImageView) findViewById(R.id.progress_on_1);
        progress2 = (ImageView) findViewById(R.id.progress_on_2);
        progress3 = (ImageView) findViewById(R.id.progress_on_3);
        idle1 = (ImageView) findViewById(R.id.progress_idle_1);
        idle2 = (ImageView) findViewById(R.id.progress_idle_2);
        idle3 = (ImageView) findViewById(R.id.progress_idle_3);
        kembaliBtn = (Button) findViewById(R.id.kembali);
        lanjutBtn = (Button) findViewById(R.id.lanjut);
        simpanBtn = (Button) findViewById(R.id.simpan);


        progress1.setVisibility(View.VISIBLE);
        idle2.setVisibility(View.VISIBLE);
        idle3.setVisibility(View.VISIBLE);
        progress2.setVisibility(View.INVISIBLE);
        progress3.setVisibility(View.INVISIBLE);
        idle1.setVisibility(View.INVISIBLE);
        simpanBtn.setVisibility(View.INVISIBLE);
        kembaliBtn.setVisibility(View.INVISIBLE);
        lanjutBtn.setVisibility(View.VISIBLE);


    }

    // This is not working, even the `Toast` not appearing
    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                fragmentaktivitas();
                page = 0;
            }
            else if (position == 1){
                fragmentmaterial();
                page = 1;
            }
            else{
                fragmentpegawai();
                page = 2;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void initOnImplementsView(){
        kembaliBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                ViewPager pager = (ViewPager) findViewById(R.id.viewpager_fragment);
                page -= 1;
                if(page == 1){
                    pager.setCurrentItem(page);
                    fragmentmaterial();
                }
                else{
                    pager.setCurrentItem(page);
                    fragmentaktivitas();
                }
            }
        });

        lanjutBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                ViewPager pager = (ViewPager) findViewById(R.id.viewpager_fragment);
                page += 1;
                if(page == 1){
                    pager.setCurrentItem(page);
                    fragmentmaterial();
                }
                else{
                    pager.setCurrentItem(page);
                    fragmentpegawai();
                }
            }
        });

        simpanBtn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
                    builder1.setMessage("Apakah Anda yakin untuk menyimpan data ini?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Ya",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    /** TO-DO save semua data **/
                                    finish();
                                }
                            });

                    builder1.setNegativeButton(
                            "Tidak",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void fragmentaktivitas(){
        progress1.setVisibility(View.VISIBLE);
        idle2.setVisibility(View.VISIBLE);
        idle3.setVisibility(View.VISIBLE);
        progress2.setVisibility(View.INVISIBLE);
        progress3.setVisibility(View.INVISIBLE);
        idle1.setVisibility(View.INVISIBLE);

        simpanBtn.setVisibility(View.INVISIBLE);
        kembaliBtn.setVisibility(View.INVISIBLE);
        lanjutBtn.setVisibility(View.VISIBLE);

        setTitle("Tambah Aktivitas");
    }

    private void fragmentmaterial(){
        progress2.setVisibility(View.VISIBLE);
        idle1.setVisibility(View.VISIBLE);
        idle3.setVisibility(View.VISIBLE);
        progress1.setVisibility(View.INVISIBLE);
        progress3.setVisibility(View.INVISIBLE);
        idle2.setVisibility(View.INVISIBLE);

        simpanBtn.setVisibility(View.INVISIBLE);
        kembaliBtn.setVisibility(View.VISIBLE);
        lanjutBtn.setVisibility(View.VISIBLE);


        setTitle("Tambah Material");
    }

    private void fragmentpegawai(){
        progress3.setVisibility(View.VISIBLE);
        idle1.setVisibility(View.VISIBLE);
        idle2.setVisibility(View.VISIBLE);
        progress1.setVisibility(View.INVISIBLE);
        progress2.setVisibility(View.INVISIBLE);
        idle3.setVisibility(View.INVISIBLE);

        simpanBtn.setVisibility(View.VISIBLE);
        kembaliBtn.setVisibility(View.VISIBLE);
        lanjutBtn.setVisibility(View.INVISIBLE);


        setTitle("Tambah Pegawai");
    }

    private void displaySelectedScreen(int id) {

        switch (id) {
            case R.id.nav_BKM_today:
                break;
            case R.id.nav_calendar:
                break;
            case R.id.nav_logout:
                break;
        }

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Apakah Anda yakin untuk keluar dari perubahan ini?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent.putExtra("id", id);//Add your return data here
                        startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public final int NUM_ITEMS = 3;

        public ViewPagerAdapter(FragmentManager fm){
            super(fm);
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new AktivitasFragment();
                case 1:
                    return new MaterialFragment();
                case 2:
                    return new PegawaiFragment();
                default:
                    return null;
            }
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return  NUM_ITEMS ;
        }

    }

}

