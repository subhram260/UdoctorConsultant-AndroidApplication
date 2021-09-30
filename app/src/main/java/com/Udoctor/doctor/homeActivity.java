package com.Udoctor.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.doctor.R;
import com.Udoctor.doctor.Models.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class homeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private  Toolbar toolbar;
    private CardView detailsdoctor,heart_cv,eye_cv,Dental_cv;


    private TextView name,username_tv;
    private ImageView imageView,userImage_tv;
    private CardView profile;
    private ImageView toolbarimage;


    private FirebaseDatabase database;
    private DatabaseReference userref;
    FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        database=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        userref = database.getReference().child("Users").child(mAuth.getUid());



        //find view by id
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);



        View header=navigationView.getHeaderView(0);
        userImage_tv=(ImageView) header.findViewById(R.id.userimage_picasso);
        username_tv=(TextView) header.findViewById(R.id.username_tv);



        name =(TextView) findViewById(R.id.textView15);
        imageView=findViewById(R.id.imageView);
        profile=findViewById(R.id.profile);
        toolbarimage=findViewById(R.id.toolbarimage);
        detailsdoctor = findViewById(R.id.detailsdoctor);
        heart_cv=findViewById(R.id.heart_cv);
        eye_cv=findViewById(R.id.eye_cv);
        Dental_cv=findViewById(R.id.Dental_cv);




        //Toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        //navigation drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:

//                        Toast.makeText(homeActivity.this,"Home",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.profile:
                        startActivity(new Intent(homeActivity.this,ProfileActivity.class));
                        break;
                    case R.id.appointment:
                        break;
                    case R.id.messages:
                         break;
                    case R.id.logoutbtn:
                        mAuth.signOut();
                        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(getString(R.string.default_web_client_id))
                                .requestEmail()
                                .build();
                        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(homeActivity.this, gso);
                        googleSignInClient.signOut();

                        startActivity(new Intent(homeActivity.this,loginActivity.class));
                        finish();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        //get Google Auth Data
//        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(this);
//        if(signInAccount!=null){
//            name.setText(signInAccount.getDisplayName());
//            Picasso.get().load(signInAccount.getPhotoUrl()).into(imageView);
//            //imageView.setImageURI(signInAccount.getPhotoUrl());
//        }

        //
        userref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
                //String value = dataSnapshot.getValue(String.class);
                //Toast.makeText(homeActivity.this,value,Toast.LENGTH_SHORT).show();
                Users users = dataSnapshot.getValue(Users.class);
                Picasso.get().load(users.getProfilePic())
                        .placeholder(R.drawable.rounded)
                        .into(toolbarimage);
                Picasso.get().load(users.getProfilePic())
                        .placeholder(R.drawable.rounded)
                        .into(userImage_tv);

                username_tv.setText(users.getUserName());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        // Onclick Listner
        heart_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(homeActivity.this,HeartActivity.class));
            }
        });
        eye_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this,EyeActivity.class));
            }
        });
        Dental_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this,DentalActivity.class));
            }
        });
        detailsdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           startActivity(new Intent(homeActivity.this,Details_doctor.class));
            }
        });
        toolbarimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           startActivity(new Intent(homeActivity.this,ProfileActivity.class));
            }
        });



    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    public void startdetails(View view) {
//        startActivity(new Intent(this,Details_doctor.class));

        mAuth.signOut();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(homeActivity.this, gso);
        googleSignInClient.signOut();

        startActivity(new Intent(this,loginActivity.class));
        finish();

    }
}
