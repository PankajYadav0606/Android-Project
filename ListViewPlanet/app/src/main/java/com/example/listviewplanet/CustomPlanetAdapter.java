package com.example.listviewplanet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomPlanetAdapter extends ArrayAdapter<planet> {

    private ArrayList<planet> planetArrayList;
    Context context;

    public CustomPlanetAdapter(@NonNull Context context, ArrayList<planet> planetArrayList){
        super(context, R.layout.planetlist,planetArrayList);
        this.planetArrayList=planetArrayList;
        this.context=context;
    }

    private static class MyViewHolder{
        TextView planetName;
        TextView moonCount;
        ImageView planetImage;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MyViewHolder myViewHolder;
        planet planet=getItem(position);
        final View result;

        if (convertView==null){
            myViewHolder=new MyViewHolder();
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.planetlist, parent, false);

            myViewHolder.planetName = convertView.findViewById(R.id.title);
            myViewHolder.moonCount = convertView.findViewById(R.id.mooncount);
            myViewHolder.planetImage= convertView.findViewById(R.id.planetImg);

            result = convertView;

            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
            result = convertView;
        }

        myViewHolder.planetName.setText(planet.getTitle());
        myViewHolder.moonCount.setText(planet.getMooncount());
        myViewHolder.planetImage.setImageResource(planet.getPlanetImage());

        return result;
    }
}
