package com.example.newcontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class CustomContactAdapter extends ArrayAdapter<Contact> {

    private ArrayList<Contact> planetArrayList;
    Context context;

    public CustomContactAdapter( Context context, ArrayList<Contact> planetArrayList){
        super(context, R.layout.contactlist,planetArrayList);


        this.context=context;
    }

    private static class MyViewHolder{
        TextView CName;
        TextView CNo;

        ImageView Cimage;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;
        Contact Contact=getItem(position);
        final View result;

        if (convertView==null){
            myViewHolder=new MyViewHolder();
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.contactlist, parent, false);

            myViewHolder.CName = convertView.findViewById(R.id.Name);
            myViewHolder.CNo = convertView.findViewById(R.id.No_);
            myViewHolder.Cimage= convertView.findViewById(R.id.img);

            result = convertView;

            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
            result = convertView;
        }

        myViewHolder.CName.setText(Contact.getName());
        myViewHolder.CNo.setText(Contact.getNo());
        myViewHolder.Cimage.setImageResource(Contact.getCImage());

        return result;
    }
}
