package com.Udoctor.doctor.doctorside;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Udoctor.doctor.Adapters.ApointmentRequestClass;
import com.Udoctor.doctor.Adapters.HeartDoctor;
import com.Udoctor.doctor.Details_doctor;
import com.Udoctor.doctor.R;
import com.squareup.picasso.Picasso;

public class AppointmentRequestProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;

    ImageView patientImage;
    TextView patientName,patientEmail,patientPhone,patientGender,patientName1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_request_profile);

        toolbar=(Toolbar) findViewById(R.id.toolbarAppointment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        patientImage=findViewById(R.id.patientImage);
        patientName=findViewById(R.id.patientName);
        patientEmail=findViewById(R.id.patientEmail);
        patientPhone=findViewById(R.id.patientPhone);
        patientGender=findViewById(R.id.patientGender);
        patientName1=findViewById(R.id.patientName1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //statusbar color
        getWindow().setStatusBarColor(ContextCompat.getColor(AppointmentRequestProfileActivity.this,R.color.deepgreen));

        ApointmentRequestClass apointmentRequestClass=getIntent().getParcelableExtra("AppointmentpatientDetails");
        try {
            Picasso.get().load(apointmentRequestClass.getPatientImage())
                    .placeholder(R.drawable.rounded)
                    .into(patientImage);
        }
        catch (Exception p)
        {
            Picasso.get().load(R.drawable.rounded)
                    .placeholder(R.drawable.rounded)
                    .into(patientImage);
//               Toast.makeText(getApplicationContext(), p.toString(), Toast.LENGTH_SHORT).show();
        }
        patientName.setText(apointmentRequestClass.getPatientName());
        patientEmail.setText(apointmentRequestClass.getPatientEmail());
        patientName1.setText(apointmentRequestClass.getPatientName());
        patientPhone.setText(apointmentRequestClass.getPatientPhone());
        patientGender.setText(apointmentRequestClass.getPatientGender());

    }
}