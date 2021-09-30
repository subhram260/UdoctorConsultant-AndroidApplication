package com.Udoctor.doctor.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Udoctor.doctor.R;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(@NonNull EyeAdapter.MyViewHolder holder, int position) {
        EyeDoctor Edoctor = list.get(position);
//        holder.imageHeart.setImageURI(Hdoctor.getImageHeart().toString());
        Picasso.get().load(Edoctor.getImageEye())
                .placeholder(R.drawable.rounded)
                .into(holder.imageEye);
        holder.nameEye.setText(Edoctor.getNameEye());
        holder.specialEye.setText(Edoctor.getSpecialEye());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageEye;
        TextView nameEye,specialEye;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEye=itemView.findViewById(R.id.imageItem);
            nameEye=itemView.findViewById(R.id.nameItem);
            specialEye=itemView.findViewById(R.id.specialItem);

        }
    }
}
