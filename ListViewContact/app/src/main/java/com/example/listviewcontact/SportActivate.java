package com.example.listviewcontact;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listviewcontact.Adapter.SportsAdapter;
import com.example.listviewcontact.ModelClass.Sports;

import java.util.ArrayList;
import java.util.List;

public class SportActivate extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Sports> sportsList;
    SportsAdapter sportsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sport_activate);

        recyclerView=findViewById(R.id.cardViewList);
        sportsList = new ArrayList<>();

        sportsList.add(new Sports(R.drawable.swim, "Swiming"));
        sportsList.add(new Sports(R.drawable.cricket1, "cricket"));
        sportsList.add(new Sports(R.drawable.hockey, "Ice Hockey"));
        sportsList.add(new Sports(R.drawable.footbll, "Foot Ball"));
        sportsList.add(new Sports(R.drawable.khokho, "Kho Kho"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sportsAdapter = new SportsAdapter(sportsList);
        recyclerView.setAdapter(sportsAdapter);
    }
}