package com.Udoctor.doctor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.StatusBarManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Udoctor.doctor.Adapters.DentalDoctor;
import com.Udoctor.doctor.Adapters.EyeDoctor;
import com.Udoctor.doctor.Adapters.HeartDoctor;
import com.squareup.picasso.Picasso;

//import com.example.doctor.R;


public class Details_doctor extends AppCompatActivity {

    TextView doctorName,doctorSpecialization;
    ImageView doctorImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_doctor);



        doctorName =findViewById(R.id.doctorName);
        doctorSpecialization=findViewById(R.id.doctorSpecialization);
        doctorImage=findViewById(R.id.doctorImage);



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
               Toast.makeText(getApplicationContext(), p.toString(), Toast.LENGTH_SHORT).show();
           }
           doctorName.setText(Hdoctor.getNameHeart());
           doctorSpecialization.setText(Hdoctor.getSpecialHeart());
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
          Toast.makeText(getApplicationContext(), pp.toString(), Toast.LENGTH_SHORT).show();
      }
          doctorName.setText(Edoctor.getNameEye());
          doctorSpecialization.setText(Edoctor.getSpecialEye());
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
           Toast.makeText(getApplicationContext(), ppp.toString(), Toast.LENGTH_SHORT).show();
       }
          doctorName.setText(Ddoctor.getNameDental());
          doctorSpecialization.setText(Ddoctor.getSpecialDental());
      }
       catch (Exception eee)
       {

       }

      }

       }


//       doctorImage.setImageURI(Hdoctor.getImageHeart());


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
//        super.onSupportNavigateUp();
    }
}