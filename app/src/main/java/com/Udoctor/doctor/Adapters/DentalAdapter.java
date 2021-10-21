package com.Udoctor.doctor.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Udoctor.doctor.Details_doctor;
import com.Udoctor.doctor.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class DentalAdapter extends RecyclerView.Adapter<DentalAdapter.MyViewHolder> {

    Context context;
    ArrayList<DentalDoctor> list;

    public DentalAdapter(Context context, ArrayList<DentalDoctor> list) {
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
    public void onBindViewHolder(@NonNull DentalAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DentalDoctor Ddoctor = list.get(position);
//        holder.imageHeart.setImageURI(Hdoctor.getImageHeart().toString());
        try{
            Picasso.get().load(Ddoctor.getImageDental())
                    .placeholder(R.drawable.rounded)
                    .into(holder.imageDental);
        }
        catch (Exception e)
        {
            Toast.makeText(context.getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();

        }
        holder.nameDental.setText(Ddoctor.getNameDental());
        holder.specialDental.setText(Ddoctor.getSpecialDental());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(v.getContext(), Details_doctor.class);
                intent.putExtra("Ddoctor", list.get(position));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageDental;
        TextView nameDental,specialDental;
        View v;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageDental=itemView.findViewById(R.id.imageItem);
            nameDental=itemView.findViewById(R.id.nameItem);
            specialDental=itemView.findViewById(R.id.specialItem);

            v=itemView;
        }
    }
}
