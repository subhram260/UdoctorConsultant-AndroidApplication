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

public class DentalAdapter extends RecyclerView.Adapter<DentalAdapter.MyViewHolder> {

    Context context;
    ArrayList<DentalDoctor> list;

    public DentalAdapter(Context context, ArrayList<DentalDoctor> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DentalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new DentalAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DentalAdapter.MyViewHolder holder, int position) {
        DentalDoctor Hdoctor = list.get(position);
//        holder.imageHeart.setImageURI(Hdoctor.getImageHeart().toString());
        Picasso.get().load(Hdoctor.getImageDental())
                .placeholder(R.drawable.rounded)
                .into(holder.imageDental);
        holder.nameDental.setText(Hdoctor.getNameDental());
        holder.specialDental.setText(Hdoctor.getSpecialDental());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageDental;
        TextView nameDental,specialDental;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageDental=itemView.findViewById(R.id.imageItem);
            nameDental=itemView.findViewById(R.id.nameItem);
            specialDental=itemView.findViewById(R.id.specialItem);

        }
    }
}
