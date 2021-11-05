package com.Udoctor.doctor.doctorside;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Udoctor.doctor.Adapters.ApointmentRequestClass;
import com.Udoctor.doctor.Adapters.HeartDoctor;
import com.Udoctor.doctor.Details_doctor;
import com.Udoctor.doctor.R;
import com.squareup.picasso.Picasso;

import java.text.BreakIterator;

public class AppointmentRequestProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ImageButton callBtn;

    ImageView patientImage;
    TextView patientName,patientEmail,patientPhone,patientGender,patientName1,phonetext,gendertext;
    private String phoneNumber,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_request_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbarAppointment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        patientImage = findViewById(R.id.patientImage);
        patientName = findViewById(R.id.patientName);
        patientEmail = findViewById(R.id.patientEmail);
        patientPhone = findViewById(R.id.patientPhone);
        patientGender = findViewById(R.id.patientGender);
        patientName1 = findViewById(R.id.patientName1);
        phonetext = findViewById(R.id.phonetext);
        gendertext = findViewById(R.id.gendertext);
        callBtn = findViewById(R.id.callbtn);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //statusbar color
        getWindow().setStatusBarColor(ContextCompat.getColor(AppointmentRequestProfileActivity.this, R.color.deepgreen));

        ApointmentRequestClass apointmentRequestClass = getIntent().getParcelableExtra("AppointmentpatientDetails");
        try {
            Picasso.get().load(apointmentRequestClass.getPatientImage())
                    .placeholder(R.drawable.rounded)
                    .into(patientImage);
        } catch (Exception p) {
            Picasso.get().load(R.drawable.rounded)
                    .placeholder(R.drawable.rounded)
                    .into(patientImage);
//               Toast.makeText(getApplicationContext(), p.toString(), Toast.LENGTH_SHORT).show();
        }
        patientName.setText(apointmentRequestClass.getPatientName());
        patientEmail.setText(apointmentRequestClass.getPatientEmail());
        patientName1.setText(apointmentRequestClass.getPatientName());
        phoneNumber = apointmentRequestClass.getPatientPhone();
        gender = apointmentRequestClass.getPatientGender();
        if(apointmentRequestClass.getPatientPhone() != null) {
        patientPhone.setText(apointmentRequestClass.getPatientPhone());
        }
        else {
            patientPhone.setVisibility(View.GONE);//makes it disappear
            phonetext.setVisibility(View.GONE);//makes it disappear

        }
        if (apointmentRequestClass.getPatientGender() != null) {
            patientGender.setText(apointmentRequestClass.getPatientGender());
        }
        else {
            patientGender.setVisibility(View.GONE);//makes it disappear
            gendertext.setVisibility(View.GONE);//makes it disappear
        }
            Toast.makeText(getApplicationContext(), phoneNumber + gender, Toast.LENGTH_SHORT).show();
            callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(AppointmentRequestProfileActivity.this, DashboardActivity.class));

                }
            });
        }
    }
