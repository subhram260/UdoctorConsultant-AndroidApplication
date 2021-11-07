package com.Udoctor.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Udoctor.doctor.Adapters.ApointmentRequestClass;
import com.Udoctor.doctor.Adapters.DentalDoctor;
import com.Udoctor.doctor.Adapters.EyeDoctor;
import com.Udoctor.doctor.Adapters.HeartDoctor;
import com.Udoctor.doctor.Adapters.chatAdapter;
import com.Udoctor.doctor.Models.MessageModel;
import com.Udoctor.doctor.doctorside.AppointmentRequestProfileActivity;
import com.Udoctor.doctor.doctorside.DashboardActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class chatDetailActivity extends AppCompatActivity {

    ImageView roundedProfileImg,arrowBack,send,chattocall;
    TextView textViewName;
    EditText etMessage;
    RecyclerView chatRecyclarView;


    FirebaseAuth mAuth;
    FirebaseDatabase database;
    private String receiverId;
    private String senderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);

        //statusbar color
        getWindow().setStatusBarColor(ContextCompat.getColor(chatDetailActivity.this, R.color.deepgreen));


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        roundedProfileImg = findViewById(R.id.roundedprofileimg);
        textViewName = findViewById(R.id.textViewname);
        arrowBack = findViewById(R.id.arrowBack);
        send = findViewById(R.id.send);
        chattocall = findViewById(R.id.chattocall);

        chatRecyclarView = findViewById(R.id.chatRecyclarView);
        etMessage = findViewById(R.id.etMessage);




        try {
            ApointmentRequestClass receiver = getIntent().getParcelableExtra("profile");
            receiverId = receiver.getUid();
            textViewName.setText(receiver.getPatientName());
            try {
                Picasso.get().load(receiver.getPatientImage())
                        .placeholder(R.drawable.rounded)
                        .into(roundedProfileImg);
            } catch (Exception p) {
                Picasso.get().load(R.drawable.rounded)
                        .placeholder(R.drawable.rounded)
                        .into(roundedProfileImg);
//               Toast.makeText(getApplicationContext(), p.toString(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {


            try {
                HeartDoctor receiver = getIntent().getParcelableExtra("UserProfille");
                receiverId = receiver.getDid();
                textViewName.setText(receiver.getNameHeart());
                try {
                    Picasso.get().load(receiver.getImageHeart())
                            .placeholder(R.drawable.rounded)
                            .into(roundedProfileImg);
                } catch (Exception p) {
                    Picasso.get().load(R.drawable.rounded)
                            .placeholder(R.drawable.rounded)
                            .into(roundedProfileImg);
//               Toast.makeText(getApplicationContext(), p.toString(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ee) {
                try {
                    EyeDoctor receiver = getIntent().getParcelableExtra("UserProfille");
                    receiverId = receiver.getDid();
                    textViewName.setText(receiver.getNameEye());
                    try {
                        Picasso.get().load(receiver.getImageEye())
                                .placeholder(R.drawable.rounded)
                                .into(roundedProfileImg);
                    } catch (Exception p) {
                        Picasso.get().load(R.drawable.rounded)
                                .placeholder(R.drawable.rounded)
                                .into(roundedProfileImg);
//               Toast.makeText(getApplicationContext(), p.toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception eee) {
                    try {
                        DentalDoctor receiver = getIntent().getParcelableExtra("UserProfille");
                        receiverId = receiver.getDid();
                        textViewName.setText(receiver.getNameDental());
                        try {
                            Picasso.get().load(receiver.getImageDental())
                                    .placeholder(R.drawable.rounded)
                                    .into(roundedProfileImg);
                        } catch (Exception p) {
                            Picasso.get().load(R.drawable.rounded)
                                    .placeholder(R.drawable.rounded)
                                    .into(roundedProfileImg);
//               Toast.makeText(getApplicationContext(), p.toString(), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception eeee) {
                    }
                }
            }
        }

            arrowBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            final ArrayList<MessageModel> messageModels = new ArrayList<>();

            final chatAdapter chatAdapter = new chatAdapter(messageModels, this);

            chatRecyclarView.setAdapter(chatAdapter);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            chatRecyclarView.setLayoutManager(layoutManager);

            Toast.makeText(getApplicationContext(), mAuth.getCurrentUser().getUid().toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), receiverId, Toast.LENGTH_SHORT).show();
            final String senderRoom = mAuth.getCurrentUser().getUid() + receiverId;
            final String receiverRoom = receiverId + mAuth.getCurrentUser().getUid();


            database.getReference().child("chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    messageModels.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        MessageModel model = snapshot1.getValue(MessageModel.class);
                        messageModels.add(model);
                    }
                    chatAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            chattocall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                startActivity(new Intent(chatDetailActivity.this, DashboardActivity.class));
                }
            });

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message = etMessage.getText().toString();
                    final MessageModel model = new MessageModel(mAuth.getCurrentUser().getUid(), message);
                    model.setTimestamp(new Date().getTime());
                    etMessage.setText("");

                    database.getReference().child("chats").child(senderRoom).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            database.getReference().child("chats").child(receiverRoom).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                }
                            });
                        }
                    });
                }
            });
        }


    }