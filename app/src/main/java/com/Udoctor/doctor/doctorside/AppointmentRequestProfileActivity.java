package com.Udoctor.doctor.doctorside;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.Udoctor.doctor.Details_doctor;
import com.Udoctor.doctor.R;

public class AppointmentRequestProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_request_profile);

//        Toolbar tb=(Toolbar) findViewById(R.id.details_tb);
//        setSupportActionBar(tb);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //statusbar color
        getWindow().setStatusBarColor(ContextCompat.getColor(AppointmentRequestProfileActivity.this,R.color.deepgreen));
    }
}