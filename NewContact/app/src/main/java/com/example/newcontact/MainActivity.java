package com.example.newcontact;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Contact> planetArrayList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        planetArrayList= new ArrayList<>();

        planetArrayList.add(new Contact(R.drawable.img,"Pankaj", "98045382"));
        planetArrayList.add(new Contact(R.drawable.img1,"Piyush", "98003482"));
        planetArrayList.add(new Contact(R.drawable.img2,"Harshal", "980012382"));
        planetArrayList.add(new Contact(R.drawable.img3,"Harshad", "98023382"));
        planetArrayList.add(new Contact(R.drawable.img4,"piya", "98000231"));
        planetArrayList.add(new Contact(R.drawable.img,"yoga", "98006692"));
        planetArrayList.add(new Contact(R.drawable.img,"Chaitya", "97700382"));
        planetArrayList.add(new Contact(R.drawable.img,"Darshya", "98800382"));
        planetArrayList.add(new Contact(R.drawable.img,"Shubya", "980990382"));

        CustomContactAdapter adapter=new CustomContactAdapter(this, planetArrayList);
        listView.setAdapter(adapter);


    }

}