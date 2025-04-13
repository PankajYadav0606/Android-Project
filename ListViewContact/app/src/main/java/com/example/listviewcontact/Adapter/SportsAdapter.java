package com.example.listviewcontact.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listviewcontact.ModelClass.Sports;
import com.example.listviewcontact.R;

import java.util.List;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.MyViewHolder> {

    List<Sports> sportsList;

    public SportsAdapter(List<Sports> sportsList) {
        this.sportsList = sportsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_templet, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Sports sports = sportsList.get(position);
        holder.imageView.setBackgroundResource(sports.getSportImg());
        holder.textView.setText(sports.getSportName());
    }

    @Override
    public int getItemCount() {
        return sportsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.cardImg);
            textView=itemView.findViewById(R.id.cardtext);




        }
    }
}
