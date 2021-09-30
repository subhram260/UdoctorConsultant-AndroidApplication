package com.Udoctor.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Udoctor.doctor.Models.DoctorUser;
import com.Udoctor.doctor.Models.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

//import com.example.doctor.R;

public class signupActivity extends AppCompatActivity {

    private TextView signupToin;
    private EditText _Rname,_Remail,_Rpass,_Rpassconfirm;
    private Button _Rbtn;
    private ImageButton GUPBtn;
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=99;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // ID assign to variable
        signupToin = findViewById(R.id.logtxtbtn);
        _Rname= findViewById(R.id.Rname);
        _Remail= findViewById(R.id.Remail);
        _Rpass= findViewById(R.id.Rpass);
        _Rpassconfirm= findViewById(R.id.Rpassconfirm);
        _Rbtn= findViewById(R.id.Rbtn);
        GUPBtn= findViewById(R.id.GUPBtn);


        progressDialog = new ProgressDialog(signupActivity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're Creating your account");


//        SharedPreferences getprefs = getSharedPreferences("IdChoose",MODE_PRIVATE);
//        String userType = getprefs.getString("userCategory","Patient");

        mAuth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        _Rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String _Rnametxt,_Remailtxt,_Rpasstxt,_Rpassconfirmtxt;
            _Rnametxt=_Rname.getText().toString();
            _Remailtxt=_Remail.getText().toString();
            _Rpasstxt=_Rpass.getText().toString();
            _Rpassconfirmtxt=_Rpassconfirm.getText().toString();

            if (TextUtils.isEmpty(_Rnametxt) || TextUtils.isEmpty(_Remailtxt) ||TextUtils.isEmpty(_Rpasstxt) ||TextUtils.isEmpty(_Rpassconfirmtxt))
            {
                Toast.makeText(signupActivity.this, "Empty creanditial", Toast.LENGTH_SHORT).show();
            }
            else if(_Rpasstxt.length() < 6)
            {
                Toast.makeText(signupActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
            }
            else
            {
                progressDialog.show();
                registerUser(_Rnametxt,_Remailtxt,_Rpasstxt,_Rpassconfirmtxt);
            }



            }

            private void registerUser(String rnametxt, String remailtxt, String rpasstxt, String rpassconfirmtxt) {

                mAuth.createUserWithEmailAndPassword(remailtxt,rpasstxt).addOnCompleteListener(signupActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful())
                    {
                        Toast.makeText(signupActivity.this, remailtxt, Toast.LENGTH_SHORT).show();



                        String id =task.getResult().getUser().getUid();

                            Users users = new Users();
                            users.setMail(remailtxt);
                            users.setUserName(rnametxt);
                            users.setPassword(rpasstxt);

                            database.getReference().child("Users").child(id).setValue(users);

                            Toast.makeText(signupActivity.this, "Registering user successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(signupActivity.this,loginActivity.class));
                            finish();


//                        else if(userType=="Doctor")
//                        {
//                            DoctorUser doctorUsers = new DoctorUser();
//                            doctorUsers.setDoctormail(remailtxt);
//                            doctorUsers.setDoctorName(rnametxt);
//                            doctorUsers.setDoctorpassword(rpasstxt);
//
//                            database.getReference().child("Doctors").child(id).setValue(doctorUsers);
//
//                            Toast.makeText(signupActivity.this, "Registering Doctor successfully", Toast.LENGTH_SHORT).show();
////                            startActivity(new Intent(signupActivity.this,loginActivity.class));
//                            finish();
//                        }
//

                    }
                    else
                    {
                        Toast.makeText(signupActivity.this, "Registering user failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            }
        });

        // SignUp TO Signin
        signupToin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signupActivity.this,loginActivity.class));
                finish();
            }
        });
        GUPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();
            }
        });
    }

    //Google Api methods
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            //getdata
                            Users users = new Users();
                            users.setUserid(user.getUid());
                            users.setUserName(user.getDisplayName());
                            users.setProfilePic(user.getPhotoUrl().toString());
                            users.setMail(user.getEmail());
                            database.getReference().child("Users").child(user.getUid()).setValue(users);

                            //Intent
                            startActivity(new Intent(signupActivity.this,homeActivity.class));
                            finish();

                            //Toast
                            Toast.makeText(signupActivity.this, "Authentication Success", Toast.LENGTH_SHORT).show();

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(signupActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }



}