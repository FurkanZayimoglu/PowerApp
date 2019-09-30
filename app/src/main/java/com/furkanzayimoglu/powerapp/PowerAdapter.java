package com.furkanzayimoglu.powerapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PowerAdapter extends RecyclerView.Adapter<PowerAdapter.MyViewHolder> {

     private ArrayList<PowerModel> arrayList;
     private LayoutInflater layoutInflater;
     private OnItemClickListener listener;

     public PowerAdapter(Context context, ArrayList<PowerModel> arrayList){
         this.arrayList = arrayList;
         layoutInflater = LayoutInflater.from(context);
     }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         View view = layoutInflater.inflate(R.layout.itemdesign,parent,false);
         MyViewHolder viewHolder = new MyViewHolder(view);
         return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

         holder.nameText.setText(arrayList.get(position).getName());
         Picasso.get().load(arrayList.get(position).getLogo()).into(holder.logoImage);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameText;
        ImageView logoImage;

        public MyViewHolder(View itemView){
            super(itemView);

            nameText = itemView.findViewById(R.id.kanalNameText);
            logoImage = itemView.findViewById(R.id.imageLogo);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
           listener.onItemClick(view,getAdapterPosition());
        }
    }
    }

