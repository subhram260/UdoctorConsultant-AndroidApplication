package com.Udoctor.doctor.doctorside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.Udoctor.doctor.Adapters.ApointmentRequestAdapter;
import com.Udoctor.doctor.Adapters.ApointmentRequestClass;
import com.Udoctor.doctor.Adapters.HeartAdapter;
import com.Udoctor.doctor.Adapters.HeartDoctor;
import com.Udoctor.doctor.HeartActivity;
import com.Udoctor.doctor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ApointmentRequest extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ApointmentRequestAdapter apointmentRequestAdapter;
    ArrayList<ApointmentRequestClass> list;

    Toolbar toolbar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apointment_request);

        getWindow().setStatusBarColor(ContextCompat.getColor(ApointmentRequest.this, R.color.btncol));


        toolbar4 = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar4);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar4.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        toolbar=(Toolbar)findViewById(R.id.tool_bar_heart);
        recyclerView = findViewById(R.id.AppointmentRequestId);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Doctors")
                .child(FirebaseAuth.getInstance().getUid()).child("AppointmentRequest");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        apointmentRequestAdapter = new ApointmentRequestAdapter(this,list);
        recyclerView.setAdapter(apointmentRequestAdapter);

        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    ApointmentRequestClass apointmentRequestClass = dataSnapshot.getValue(ApointmentRequestClass.class);
                    list.add(apointmentRequestClass);
                }
                apointmentRequestAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}