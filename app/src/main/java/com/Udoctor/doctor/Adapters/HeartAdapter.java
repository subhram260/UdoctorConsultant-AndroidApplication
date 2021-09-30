package com.Udoctor.doctor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.Udoctor.doctor.Details_doctor;
import com.Udoctor.doctor.DoctorHome;
import com.Udoctor.doctor.HeartActivity;
import com.Udoctor.doctor.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HeartAdapter extends RecyclerView.Adapter<HeartAdapter.MyViewHolder>{

    Context context;
    ArrayList<HeartDoctor> list;
//     public Intent intent = new Intent(HeartAdapter.this,demo.class);

    public HeartAdapter(Context context, ArrayList<HeartDoctor> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HeartAdapter.MyViewHolder holder, int position) {
        HeartDoctor Hdoctor = list.get(position);
//        holder.imageHeart.setImageURI(Hdoctor.getImageHeart().toString());
        Picasso.get().load(Hdoctor.getImageHeart())
                .placeholder(R.drawable.rounded)
                .into(holder.imageHeart);
        holder.nameHeart.setText(Hdoctor.getNameHeart());
        holder.specialHeart.setText(Hdoctor.getSpecialHeart());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


    Intent intent=new Intent(v.getContext(),Details_doctor.class);
    context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageHeart;
        TextView nameHeart,specialHeart;
        View v;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageHeart=itemView.findViewById(R.id.imageItem);
            nameHeart=itemView.findViewById(R.id.nameItem);
            specialHeart=itemView.findViewById(R.id.specialItem);

            v=itemView;

        }
    }



}