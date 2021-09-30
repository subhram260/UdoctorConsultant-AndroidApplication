package com.Udoctor.doctor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.StatusBarManager;
import android.os.Bundle;

//import com.example.doctor.R;


public class Details_doctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_doctor);



       Toolbar tb=(Toolbar) findViewById(R.id.details_tb);
       setSupportActionBar(tb);
       getSupportActionBar().setDisplayShowTitleEnabled(false);

       //statusbar color
       getWindow().setStatusBarColor(ContextCompat.getColor(Details_doctor.this,R.color.deepgreen));

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
//        super.onSupportNavigateUp();
    }
}