package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.logingpage);

        loginBtn = findViewById(R.id.btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginPage.this, "Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginPage.this,AfterLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("Lifecycle", "onStart called");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("Lifecycle", "onResume called");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("Lifecycle", "onPause called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("Lifecycle", "onStop called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("Lifecycle", "onDestroy called");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("Lifecycle", "onRestart called");
    }
}