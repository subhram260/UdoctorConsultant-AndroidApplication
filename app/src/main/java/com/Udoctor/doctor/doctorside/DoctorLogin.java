package com.Udoctor.doctor.doctorside;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Udoctor.doctor.R;
import com.Udoctor.doctor.homeActivity;
import com.Udoctor.doctor.loginActivity;
import com.Udoctor.doctor.signupActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorLogin extends AppCompatActivity {


    private TextView DsigninToup;
    private ImageButton signin;
    private Button _DLbtn;
    private EditText _DLemail,_DLpass;
    ProgressDialog progressDialog;


    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=99;
    FirebaseDatabase database;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);




        DsigninToup = findViewById(R.id.Dregtxtbtn);
        _DLemail=findViewById(R.id.DLemail);
        _DLpass=findViewById(R.id.DLpass);
        _DLbtn=findViewById(R.id.DLbtn);


        progressDialog = new ProgressDialog(DoctorLogin.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("login to your account");


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        _DLbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _DLemailtxt,_DLpasstxt;

                _DLemailtxt=_DLemail.getText().toString();
                _DLpasstxt=_DLpass.getText().toString();

                if (TextUtils.isEmpty(_DLemailtxt) ||TextUtils.isEmpty(_DLpasstxt))
                {
                    Toast.makeText(DoctorLogin.this, "Empty creanditial", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loginUser(_DLemailtxt,_DLpasstxt);

                }
            }

            private void loginUser(String lemailtxt, String lpasstxt) {
                mAuth.signInWithEmailAndPassword(lemailtxt,lpasstxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(DoctorLogin.this, DoctorHome.class));
                        }
                        else
                        {
                            Toast.makeText(DoctorLogin.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }


                    }
                });


            }
        });

        DsigninToup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorLogin.this, DoctorSignup.class));
                finish();
            }
        });
    }


    public void startDHome(View view) {
    }
}