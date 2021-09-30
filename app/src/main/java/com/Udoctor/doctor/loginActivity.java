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

//import com.example.doctor.R;
import com.Udoctor.doctor.Models.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class loginActivity extends AppCompatActivity {

    private TextView signinToup;
    private ImageButton signin;
    private Button _Lbtn;
    private EditText _Lemail,_Lpass;
    ProgressDialog progressDialog;


    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN=99;
    FirebaseDatabase database;


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null)
        {
                Intent intent=new Intent(getApplicationContext(),homeActivity.class);
                startActivity(intent);
                finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        signinToup = findViewById(R.id.regtxtbtn);
        signin = findViewById(R.id.GINBtn);
        _Lemail=findViewById(R.id.Lemail);
        _Lpass=findViewById(R.id.Lpass);
        _Lbtn=findViewById(R.id.Lbtn);


        progressDialog = new ProgressDialog(loginActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("login to your account");


//        SharedPreferences getprefs = getSharedPreferences("IdChoose",MODE_PRIVATE);
//        String userType = getprefs.getString("userCategory","Patient");
//        Toast.makeText(loginActivity.this, userType, Toast.LENGTH_SHORT).show();


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);




        ////// On Click Listeners


        _Lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String _Lemailtxt,_Lpasstxt;

            _Lemailtxt=_Lemail.getText().toString();
            _Lpasstxt=_Lpass.getText().toString();

                if (TextUtils.isEmpty(_Lemailtxt) ||TextUtils.isEmpty(_Lpasstxt))
                {
                    Toast.makeText(loginActivity.this, "Empty creanditial", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loginUser(_Lemailtxt,_Lpasstxt);

                }
            }

            private void loginUser(String lemailtxt, String lpasstxt) {
                mAuth.signInWithEmailAndPassword(lemailtxt,lpasstxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                                startActivity(new Intent(loginActivity.this, homeActivity.class));
                        }
                        else
                        {
                            Toast.makeText(loginActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }


                    }
                });


            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        onlysignIn();
                    }
                });
        signinToup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,signupActivity.class));
                finish();
            }
        });





    if(mAuth.getCurrentUser()!=null)
    {
        startActivity(new Intent(loginActivity.this,homeActivity.class));
    }


    }
//
    private void onlysignIn(){
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
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {

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

//                            database.getReference().child("Users").child(user.getUid()).setValue(users);

                            //Intent
                            startActivity(new Intent(loginActivity.this,homeActivity.class));
                            finish();

                            //Toast
                            Toast.makeText(loginActivity.this, "Authentication Success", Toast.LENGTH_SHORT).show();

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(loginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

}