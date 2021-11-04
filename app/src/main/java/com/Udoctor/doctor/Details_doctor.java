package com.Udoctor.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Udoctor.doctor.Adapters.ApointmentRequestClass;
import com.Udoctor.doctor.Adapters.DentalDoctor;
import com.Udoctor.doctor.Adapters.EyeDoctor;
import com.Udoctor.doctor.Adapters.HeartDoctor;
import com.Udoctor.doctor.Models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

//import com.example.doctor.R;


public class Details_doctor extends AppCompatActivity {

    TextView doctorName,doctorSpecialization;
    ImageView doctorImage;

    FirebaseStorage storage;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    Users users;
    String Did="";
    private String name,pic,email,phone,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_doctor);



        doctorName =findViewById(R.id.doctorName);
        doctorSpecialization=findViewById(R.id.doctorSpecialization);
        doctorImage=findViewById(R.id.doctorImage);


        storage = FirebaseStorage.getInstance();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

       Toolbar tb=(Toolbar) findViewById(R.id.details_tb);
       setSupportActionBar(tb);
       getSupportActionBar().setDisplayShowTitleEnabled(false);

       //statusbar color
       getWindow().setStatusBarColor(ContextCompat.getColor(Details_doctor.this,R.color.deepgreen));

       try {
           HeartDoctor Hdoctor=getIntent().getParcelableExtra("Hdoctor");
           try {
               Picasso.get().load(Hdoctor.getImageHeart())
                       .placeholder(R.drawable.rounded)
                       .into(doctorImage);
           }
           catch (Exception p)
           {
               Picasso.get().load(R.drawable.rounded)
                       .placeholder(R.drawable.rounded)
                       .into(doctorImage);
//               Toast.makeText(getApplicationContext(), p.toString(), Toast.LENGTH_SHORT).show();
           }
           doctorName.setText(Hdoctor.getNameHeart());
           doctorSpecialization.setText(Hdoctor.getSpecialHeart());
           Did= Hdoctor.getDid();
           Toast.makeText(getApplicationContext(),Hdoctor.getDid(),Toast.LENGTH_SHORT).show();
       }catch(Exception e)
       {
      try
      {
          EyeDoctor Edoctor=getIntent().getParcelableExtra("Edoctor");
       try{
           Picasso.get().load(Edoctor.getImageEye())
                  .placeholder(R.drawable.rounded)
                  .into(doctorImage);
      }
      catch (Exception pp)
      {
          Picasso.get().load(R.drawable.rounded)
                  .placeholder(R.drawable.rounded)
                  .into(doctorImage);
//          Toast.makeText(getApplicationContext(), pp.toString(), Toast.LENGTH_SHORT).show();
      }
          doctorName.setText(Edoctor.getNameEye());
          doctorSpecialization.setText(Edoctor.getSpecialEye());
          Did= Edoctor.getDid();
          Toast.makeText(getApplicationContext(),Edoctor.getDid(),Toast.LENGTH_SHORT).show();
      }
      catch (Exception ee)
      {

       try
      {
          DentalDoctor Ddoctor=getIntent().getParcelableExtra("Ddoctor");
         try{
             Picasso.get().load(Ddoctor.getImageDental())
                  .placeholder(R.drawable.rounded)
                  .into(doctorImage);
      }
       catch (Exception ppp)
       {
           Picasso.get().load(R.drawable.rounded)
                   .placeholder(R.drawable.rounded)
                   .into(doctorImage);
//           Toast.makeText(getApplicationContext(), ppp.toString(), Toast.LENGTH_SHORT).show();
       }
          doctorName.setText(Ddoctor.getNameDental());
          doctorSpecialization.setText(Ddoctor.getSpecialDental());
          Did= Ddoctor.getDid();
          Toast.makeText(getApplicationContext(),Ddoctor.getDid(),Toast.LENGTH_SHORT).show();
      }
       catch (Exception eee)
       {

       }

      }

       }


//       doctorImage.setImageURI(Hdoctor.getImageHeart());
//SharedPreferences prefs = getSharedPreferences("Uid",MODE_PRIVATE);
//String Uid= prefs.getString("Uid",null);
        database.getReference().child("Users").child(mAuth.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                          users = snapshot.getValue(Users.class);

                        pic=users.getProfilePic();
                        name=users.getUserName();
                        email=users.getMail();
                        phone=users.getUserphone();
                        gender=users.getUsergender();
//                        usergender.setText(users.getUsergender());
//                        userDOB.setText(users.getUserDOB());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
//        super.onSupportNavigateUp();
    }

    public void bookAppointment(View view) {
        ApointmentRequestClass apointmentRequestClass=new ApointmentRequestClass();
        apointmentRequestClass.setPatientName(name);
        apointmentRequestClass.setPatientEmail(email);
        if(phone!=null)
        {
            apointmentRequestClass.setPatientPhone(phone);
        }
        else
        {
            apointmentRequestClass.setPatientPhone("Phone number not avaliable !!");

        }

        apointmentRequestClass.setPatientImage(pic);
        apointmentRequestClass.setPatientGender(gender);

        database.getReference().child("Doctors").child(Did)
                .child("AppointmentRequest")
                .child(users.getUserid()+"111")
                .setValue(apointmentRequestClass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(),"Appointment Booked successfully",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}