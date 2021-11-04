package com.Udoctor.doctor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Udoctor.doctor.Details_doctor;
import com.Udoctor.doctor.R;
import com.Udoctor.doctor.doctorside.AppointmentRequestProfileActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ApointmentRequestAdapter extends RecyclerView.Adapter<ApointmentRequestAdapter.MyViewHolder>{

        Context context;
        ArrayList<ApointmentRequestClass> list;

    public ApointmentRequestAdapter(Context context, ArrayList<ApointmentRequestClass> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ApointmentRequestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ApointmentRequestAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ApointmentRequestAdapter.MyViewHolder holder, int position) {
        ApointmentRequestClass Appointment = list.get(position);
//        holder.imageHeart.setImageURI(Hdoctor.getImageHeart().toString());
        try{
            Picasso.get().load(Appointment.getPatientImage())
                    .placeholder(R.drawable.rounded)
                    .into(holder.image);
        }
        catch (Exception e)
        {
            Picasso.get().load(R.drawable.rounded)
                    .placeholder(R.drawable.rounded)
                    .into(holder.image);

        }
        holder.name.setText(Appointment.getPatientName());
        holder.email.setText(Appointment.getPatientEmail());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(v.getContext(), AppointmentRequestProfileActivity.class);
                intent.putExtra("AppointmentpatientDetails", list.get(position));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name,email;
        View v;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.imageItem);
            name=itemView.findViewById(R.id.nameItem);
            email=itemView.findViewById(R.id.specialItem);

            v=itemView;

        }
    }
}
