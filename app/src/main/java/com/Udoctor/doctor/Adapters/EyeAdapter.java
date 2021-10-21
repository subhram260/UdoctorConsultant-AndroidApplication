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

import java.io.Serializable;
import java.util.ArrayList;

public class EyeAdapter extends RecyclerView.Adapter<EyeAdapter.MyViewHolder>{

    Context context;
    ArrayList<EyeDoctor> list;

    public EyeAdapter(Context context, ArrayList<EyeDoctor> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public EyeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new EyeAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EyeAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        EyeDoctor Edoctor = list.get(position);
//        holder.imageHeart.setImageURI(Hdoctor.getImageHeart().toString());
     try{
        Picasso.get().load(Edoctor.getImageEye())
                .placeholder(R.drawable.rounded)
                .into(holder.imageEye);
    }
        catch (Exception e)
    {
        Toast.makeText(context.getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();

    }
        holder.nameEye.setText(Edoctor.getNameEye());
        holder.specialEye.setText(Edoctor.getSpecialEye());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(v.getContext(), Details_doctor.class);
                intent.putExtra("Edoctor",list.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageEye;
        TextView nameEye,specialEye;
        View v;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEye=itemView.findViewById(R.id.imageItem);
            nameEye=itemView.findViewById(R.id.nameItem);
            specialEye=itemView.findViewById(R.id.specialItem);

            v=itemView;
        }
    }
}
