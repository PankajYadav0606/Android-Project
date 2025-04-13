package com.example.listviewplanet;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<planet> planetArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        planetArrayList= new ArrayList<>();

        planetArrayList.add(new planet(R.drawable.earth, "Earth", "1 Moon"));
        planetArrayList.add(new planet(R.drawable.sun, "Sun", "0 Moon"));
        planetArrayList.add(new planet(R.drawable.venus, "Venus", "3 Moon"));
        planetArrayList.add(new planet(R.drawable.mars, "Mars", "4 Moon"));
        planetArrayList.add(new planet(R.drawable.jupiter, "Jupiter", "5 Moon"));
        planetArrayList.add(new planet(R.drawable.saturn, "Saturn", "6 Moon"));
        planetArrayList.add(new planet(R.drawable.uranus, "Uranus", "28 Moon"));
        planetArrayList.add(new planet(R.drawable.mercury, "Mercury", "7 Moon"));
        planetArrayList.add(new planet(R.drawable.planet, "Planet", "8 Moon"));

        CustomPlanetAdapter adapter=new CustomPlanetAdapter(this, planetArrayList);
        listView.setAdapter(adapter);


    }

}