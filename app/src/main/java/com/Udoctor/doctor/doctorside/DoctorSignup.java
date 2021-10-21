package com.Udoctor.doctor.doctorside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Udoctor.doctor.Adapters.DentalDoctor;
import com.Udoctor.doctor.Adapters.EyeDoctor;
import com.Udoctor.doctor.Adapters.HeartDoctor;
import com.Udoctor.doctor.Models.DoctorUser;
import com.Udoctor.doctor.Models.Users;
import com.Udoctor.doctor.R;
import com.Udoctor.doctor.loginActivity;
import com.Udoctor.doctor.signupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorSignup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] users = { "Choose specialization","Heart Specialist", "Eye Specialist", "Dental Specialist" };

    private TextView DsignupToin;
    String specialization = "";
    private EditText _DRname,_DRemail,_DRpass,_DRpassconfirm;
    private Button _DRbtn;
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseDatabase database;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);

        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        DsignupToin = findViewById(R.id.Dlogtxtbtn);
        _DRname= findViewById(R.id.DRname);
        _DRemail= findViewById(R.id.DRemail);
        _DRpass= findViewById(R.id.DRpass);
        _DRpassconfirm= findViewById(R.id.DRpassconfirm);
        _DRbtn= findViewById(R.id.DRbtn);



        progressDialog = new ProgressDialog(DoctorSignup.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're Creating your account");


        mAuth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);




        _DRbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _DRnametxt,_DRemailtxt,_DRpasstxt,_DRpassconfirmtxt,_DRspecialization;
                _DRnametxt=_DRname.getText().toString();
                _DRemailtxt=_DRemail.getText().toString();
                _DRpasstxt=_DRpass.getText().toString();
                _DRpassconfirmtxt=_DRpassconfirm.getText().toString();
                _DRspecialization=specialization;

                if (TextUtils.isEmpty(_DRnametxt) || TextUtils.isEmpty(_DRemailtxt) ||TextUtils.isEmpty(_DRpasstxt) ||TextUtils.isEmpty(_DRpassconfirmtxt)||"Choose specialization".equals(_DRspecialization))
                {
                    Toast.makeText(DoctorSignup.this, "Empty creanditial", Toast.LENGTH_SHORT).show();
                }
                else if(_DRpasstxt.length() < 6)
                {
                    Toast.makeText(DoctorSignup.this, "Password is too short", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog.show();
                    registerUser(_DRnametxt,_DRemailtxt,_DRspecialization,_DRpasstxt,_DRpassconfirmtxt);
                }



            }

            private void registerUser(String rnametxt, String remailtxt,String _DRspecialization, String rpasstxt, String rpassconfirmtxt) {

                mAuth.createUserWithEmailAndPassword(remailtxt,rpasstxt).addOnCompleteListener(DoctorSignup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            Toast.makeText(DoctorSignup.this, remailtxt, Toast.LENGTH_SHORT).show();



                            String id =task.getResult().getUser().getUid();

                            DoctorUser users = new DoctorUser();
                            users.setDoctormail(remailtxt);
                            users.setDoctorName(rnametxt);
                            users.setDoctorpassword(rpasstxt);

                            database.getReference().child("Doctors").child(id).setValue(users);
                            database.getReference().child("Doctors").child(id).child("spelization").setValue(_DRspecialization);

                            if(_DRspecialization=="Heart Specialist")
                            {
                                HeartDoctor doctor=new HeartDoctor();
                                doctor.setImageHeart("");
                                doctor.setNameHeart(rnametxt);
                                doctor.setSpecialHeart(_DRspecialization);
                                database.getReference().child("Heartlist").child(id).setValue(doctor);
                            }
                            if(_DRspecialization=="Eye Specialist")
                            {
                                EyeDoctor doctor=new EyeDoctor();
                                doctor.setImageEye("");
                                doctor.setNameEye(rnametxt);
                                doctor.setSpecialEye(_DRspecialization);
                                database.getReference().child("Eyelist").child(id).setValue(doctor);
                            }
                            if(_DRspecialization=="Dental Specialist")
                            {
                                DentalDoctor doctor=new DentalDoctor();
                                doctor.setImageDental("");
                                doctor.setNameDental(rnametxt);
                                doctor.setSpecialDental(_DRspecialization);
                                database.getReference().child("Dentallist").child(id).setValue(doctor);
                            }

                            Toast.makeText(DoctorSignup.this, "Registering user successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(signupActivity.this,loginActivity.class));
                            finish();

                        }
                        else
                        {
                            Toast.makeText(DoctorSignup.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        DsignupToin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorSignup.this, DoctorLogin.class));
                finish();
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), "Selected User: "+users[position] , Toast.LENGTH_SHORT).show();
        specialization=users[position].toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}