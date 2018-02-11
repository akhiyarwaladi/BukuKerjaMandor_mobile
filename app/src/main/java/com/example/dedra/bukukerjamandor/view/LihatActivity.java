package com.example.dedra.bukukerjamandor.view;

/**
 * Created by Dedra on 02/10/2017.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dedra.bukukerjamandor.R;

public class LihatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lihat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.content_main, new LihatFragment());
        tx.commit();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.lihat_menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case android.R.id.home:{
                onBackPressed();
                return true;
            }
//            case R.id.action_delete:{
//                onBackPressed();
//                return true;
//            }
//            case R.id.action_edit:{
//                onBackPressed();
//                return true;
//            }
        }

        return super.onOptionsItemSelected(item);
    }

}


