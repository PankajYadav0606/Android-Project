package com.example.luckynumberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Random_Number extends AppCompatActivity {

    TextView nameOutput, luckynumber;
    Button shareBtn;

    String nameOut;
    int randomNumOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_random_number);

        nameOutput=findViewById(R.id.name);
        luckynumber=findViewById(R.id.numberOutput);
        shareBtn=findViewById(R.id.sharebtn);

        nameOut= getIntent().getStringExtra("NAME_INPUT");
        nameOutput.setText(getFallbacktext(nameOut, "No input from User"));



        //out Random Number

        randomNumOut = generateRandom();
        luckynumber.setText("" + randomNumOut);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleShare();
            }
        });


    }


    private void handleShare(){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, nameOut +"got luckey out");
        intent.putExtra(Intent.EXTRA_TEXT, "His lucky Number Is: "+randomNumOut);
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    public int generateRandom(){
        Random random=new Random();
        int upper_limit=1000;

        return random.nextInt(upper_limit);
    }


    private String getFallbacktext(String input, String fallback){
        return input != null && !input.isEmpty() ? input : fallback;
    }
}


