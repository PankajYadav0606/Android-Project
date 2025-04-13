package com.example.newspotify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class implicit_intent extends AppCompatActivity {


    Button webBtn, shareBtn, phoneBtn, mapsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_implicit_intent);

        webBtn=findViewById(R.id.web);
        shareBtn=findViewById(R.id.share);
        phoneBtn=findViewById(R.id.phone);
        mapsBtn=findViewById(R.id.map);

        webBtn.setOnClickListener(V ->{
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.google.co.in/"));
            startActivity(intent);
        });

        shareBtn.setOnClickListener(V ->{
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "HELLO! .");
            startActivity(Intent.createChooser(intent, "Share via"));
        });

        phoneBtn.setOnClickListener(V ->{
            Intent intent=new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel: 9999999999"));
            startActivity(intent);
        });

        mapsBtn.setOnClickListener(V ->{
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://maps.app.goo.gl/C5CuSgZpyHH4V6vV7"));
            startActivity(intent);
        });
    }
}