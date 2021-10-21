package com.Udoctor.doctor.doctorside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Udoctor.doctor.Models.DoctorUser;
import com.Udoctor.doctor.Models.Users;
import com.Udoctor.doctor.ProfileActivity;
import com.Udoctor.doctor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DoctorHome extends AppCompatActivity {

    ImageView arrow,DoctorImage;
    TextView DoctorName,Doctorspecialization;


    FirebaseStorage storage;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    private DatabaseReference userref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home2);

        storage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userref = database.getReference().child("Doctors").child(mAuth.getUid());

        DoctorImage=findViewById(R.id.DoctorImage);
        DoctorName=findViewById(R.id.DoctorName);
        Doctorspecialization=findViewById(R.id.Doctorspecialization);

        getWindow().setStatusBarColor(ContextCompat.getColor(DoctorHome.this, R.color.btncol));

        DoctorName.setText(mAuth.getCurrentUser().getDisplayName());

        database.getReference().child("Doctors").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DoctorUser doctoruser = snapshot.getValue(DoctorUser.class);
                        Picasso.get().load(doctoruser.getProfilePic())
                                .placeholder(R.drawable.rounded)
                                .into(DoctorImage);
                        DoctorName.setText(doctoruser.getDoctorName());
                        Doctorspecialization.setText(doctoruser.getSpelization());



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        try {
        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
                //String value = dataSnapshot.getValue(String.class);
                //Toast.makeText(homeActivity.this,value,Toast.LENGTH_SHORT).show();
//                StorageReference reference = storage.getReference().child("ProfilePictures").child(FirebaseAuth.getInstance().getUid());
                DoctorUser doctorUser = dataSnapshot.getValue(DoctorUser.class);
//                if(doctorUser.getProfilePic()!=null) {
                    Picasso.get().load(doctorUser.getProfilePic())
                            .placeholder(R.drawable.rounded)
                            .into(DoctorImage);
//                }
//                DoctorName.setText(doctorUser.getDoctorName());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        }
        catch (Exception e)
        {

        }

    }

    public void DoctorLogout(View view) {
        mAuth.signOut();
        finish();
    }

    public void startDoctorprofile(View view) {
        startActivity(new Intent(DoctorHome.this,DoctorProfile.class));
    }
}