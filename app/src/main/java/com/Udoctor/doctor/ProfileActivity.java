package com.Udoctor.doctor;

import static android.view.View.INVISIBLE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.Udoctor.doctor.Models.Users;
import com.firebase.ui.auth.data.remote.ProfileMerger;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    ImageView arrow,plusimage,roundedimg,editmode;
    EditText username,useremail,userphone,usergender,userDOB;
    ProgressDialog progressDialog;
    Uri sFile;



    FirebaseStorage storage;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    private boolean editmodebool=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


    //statusbar color
    getWindow().setStatusBarColor(ContextCompat.getColor(ProfileActivity.this,R.color.deepgreen));

    storage = FirebaseStorage.getInstance();
    mAuth = FirebaseAuth.getInstance();
    database = FirebaseDatabase.getInstance();

    arrow=findViewById(R.id.arrow);
    plusimage=findViewById(R.id.plusimage);
    roundedimg=findViewById(R.id.roundedimg);
    username=findViewById(R.id.username);
    useremail=findViewById(R.id.useremail);
    userphone=findViewById(R.id.userphone);
    usergender=findViewById(R.id.usergender);
    userDOB=findViewById(R.id.userDOB);
    editmode=findViewById(R.id.editmode);


    progressDialog = new ProgressDialog(ProfileActivity.this);
    progressDialog.setTitle("Profile picture");
    progressDialog.setMessage("uploading....");

    //from Google Auth
    username.setText(mAuth.getCurrentUser().getDisplayName());
    useremail.setText(mAuth.getCurrentUser().getEmail());
    roundedimg.setImageURI(mAuth.getCurrentUser().getPhotoUrl());
    //final StorageReference reference = storage.getReference().child("ProfilePictures").child(FirebaseAuth.getInstance().getUid());
    //roundedimg.setImageURI(reference.child("ProfilePictures").getDownloadUrl().getResult());




    //other settings
//    username.setFocusable(false);
//    useremail.setFocusable(false);
//    userphone.setFocusable(false);
//    usergender.setFocusable(false);
//    userDOB.setFocusable(false);

        username.setEnabled(false);
        useremail.setEnabled(false);
        userphone.setEnabled(false);
        usergender.setEnabled(false);
        userDOB.setEnabled(false);

    database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Users users = snapshot.getValue(Users.class);
                    Picasso.get().load(users.getProfilePic())
                            .placeholder(R.drawable.rounded)
                            .into(roundedimg);

                    username.setText(users.getUserName());
                    useremail.setText(users.getMail());
                    userphone.setText(users.getUserphone());
                    usergender.setText(users.getUsergender());
                    userDOB.setText(users.getUserDOB());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



    arrow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(ProfileActivity.this,homeActivity.class));
            finish();
        }
    });

    plusimage.setVisibility(INVISIBLE);

    plusimage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           try {
               Intent intent = new Intent();
               intent.setAction(Intent.ACTION_GET_CONTENT);
               intent.setType("image/*");
               startActivityForResult(intent, 33);
           }
           catch (Exception e)
           {
               Toast.makeText(ProfileActivity.this, "Profile picture is not selected", Toast.LENGTH_SHORT).show();
           }
        }
    });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData() != null){
            progressDialog.show();
            sFile = data.getData();
            roundedimg.setImageURI(sFile);



            //Add profile pic
            final StorageReference reference = storage.getReference().child("ProfilePictures").child(FirebaseAuth.getInstance().getUid());
            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("profilePic").setValue(uri.toString());
                            Toast.makeText(ProfileActivity.this, "Profile picture Uploaded", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    });

                }
            });
        }
        else
        {

        }

    }

    public void editmode(View view) {
        editmodebool=!editmodebool;

        //preview work
        if(editmodebool)
        {
            plusimage.setVisibility(view.VISIBLE);

            editmode.setImageResource(R.drawable.save);

            username.setEnabled(true);
            useremail.setEnabled(true);
            userphone.setEnabled(true);
            usergender.setEnabled(true);
            userDOB.setEnabled(true);

        }
        else
        {
            editmode.setImageResource(R.drawable.edit);

            plusimage.setVisibility(INVISIBLE);

            username.setEnabled(false);
            useremail.setEnabled(false);
            userphone.setEnabled(false);
            usergender.setEnabled(false);
            userDOB.setEnabled(false);

            //get text
            String _name,_mail,_phone,_gender,_DOB;
            _name = username.getText().toString();
            _mail = useremail.getText().toString();
            _phone = userphone.getText().toString();
            _gender = usergender.getText().toString();
            _DOB = userDOB.getText().toString();

            //display changed data
            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("userName").setValue(_name);
            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("mail").setValue(_mail);
            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("userphone").setValue(_phone);
            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("usergender").setValue(_gender);
            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("userDOB").setValue(_DOB);


            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Users users = snapshot.getValue(Users.class);
                            Picasso.get().load(users.getProfilePic())
                                    .placeholder(R.drawable.rounded)
                                    .into(roundedimg);
//                            progressDialog.show();
                            roundedimg.setImageURI(sFile);
                            username.setText(users.getUserName());
                            useremail.setText(users.getMail());
                            userphone.setText(users.getUserphone());
                            usergender.setText(users.getUsergender());
                            userDOB.setText(users.getUserDOB());

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        }


//        username.setEnabled(true);
//        useremail.setEnabled(true);
//        userphone.setEnabled(true);
//        usergender.setEnabled(true);
//        userDOB.setEnabled(true);





    }
}





