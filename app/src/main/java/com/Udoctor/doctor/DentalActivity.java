package com.Udoctor.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.Udoctor.doctor.Adapters.DentalAdapter;
import com.Udoctor.doctor.Adapters.DentalDoctor;
import com.Udoctor.doctor.Adapters.HeartAdapter;
import com.Udoctor.doctor.Adapters.HeartDoctor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DentalActivity extends AppCompatActivity {

    private Toolbar toolbar;

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    DentalAdapter dentalAdapter;
    ArrayList<DentalDoctor> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dental);

        //statusbar color
        getWindow().setStatusBarColor(ContextCompat.getColor(DentalActivity.this, R.color.btncol));


        toolbar=(Toolbar)findViewById(R.id.tool_bar_dental);
        recyclerView = findViewById(R.id.dentalDoctorlist);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Dentallist");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        dentalAdapter = new DentalAdapter(this,list);
        recyclerView.setAdapter(dentalAdapter);


        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    DentalDoctor dentalDoctor = dataSnapshot.getValue(DentalDoctor.class);
                    list.add(dentalDoctor);
                }
                dentalAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}