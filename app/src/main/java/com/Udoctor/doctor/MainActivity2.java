package com.Udoctor.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Udoctor.doctor.doctorside.DoctorLogin;
import com.Udoctor.doctor.doctorside.DoctorSignup;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity2 extends AppCompatActivity {


    private FirebaseDatabase database;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        database=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();

    }

    public void statloginPatient(View view) {
        mAuth.signOut();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(MainActivity2.this, gso);
        googleSignInClient.signOut();
        startActivity(new Intent(this,loginActivity.class));
    }

    public void statloginDoctor(View view) {
        mAuth.signOut();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(MainActivity2.this, gso);
        googleSignInClient.signOut();
        startActivity(new Intent(this, DoctorLogin.class));
    }
}

//        SharedPreferences prefs = getSharedPreferences("IdChoose",MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();

//        editor.putString("userCategory","Patient");
//        editor.apply();