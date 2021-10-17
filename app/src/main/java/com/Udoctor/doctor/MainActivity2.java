package com.Udoctor.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.Udoctor.doctor.doctorside.DoctorLogin;
import com.Udoctor.doctor.doctorside.DoctorSignup;


public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void statloginPatient(View view) {

//        SharedPreferences prefs = getSharedPreferences("IdChoose",MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();

//        editor.putString("userCategory","Patient");
//        editor.apply();

        startActivity(new Intent(this,loginActivity.class));
    }

    public void statloginDoctor(View view) {

//        SharedPreferences prefs = getSharedPreferences("IdChoose",MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//
//        editor.putString("userCategory","Doctor");
//        editor.apply();


        startActivity(new Intent(this, DoctorLogin.class));
    }
}