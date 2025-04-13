package com.example.listviewapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listView);


        //data source
        String[] countries = {
                "Canada", "Brazil", "Japan", "Australia", "Germany", "India", "South Africa",
                "Mexico", "France", "Argentina", "Italy", "China", "United Kingdom", "Russia",
                "Egypt", "South Korea", "Turkey", "Saudi Arabia", "Indonesia", "Thailand"
        };


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner,
                countries
        );

        listview.setAdapter(adapter);


    }
}