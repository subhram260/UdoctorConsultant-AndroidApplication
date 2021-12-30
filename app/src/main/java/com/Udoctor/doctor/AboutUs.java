package com.Udoctor.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //statusbar color
        getWindow().setStatusBarColor(ContextCompat.getColor(AboutUs.this,R.color.lightgreen));
    }
}