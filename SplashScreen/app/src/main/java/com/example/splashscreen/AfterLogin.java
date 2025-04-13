package com.example.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AfterLogin extends AppCompatActivity {

    TextView input1, input2, courses, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_after_login);


        input1= findViewById(R.id.usernameOut);
        input2= findViewById(R.id.passwordOut);
        courses= findViewById(R.id.coursesOut);
        gender= findViewById(R.id.genderOut);

        String usernameValue= getIntent().getStringExtra("User_Input");
        String passwordValue= getIntent().getStringExtra("Pass_Input");
        String coursesValue= getIntent().getStringExtra("Courses_Input");
        String genderValue= getIntent().getStringExtra("Gender_Input");

        input1.setText(getFallbackText(usernameValue, "No Username"));
        input2.setText(getFallbackText(passwordValue, "No Password"));
        courses.setText(getFallbackText(coursesValue, "No Courses Selected"));
        gender.setText(getFallbackText(genderValue, "No Gender Selected"));

    }

    private String getFallbackText(String input, String fallback) {
        return input != null && !input.isEmpty() ? input : fallback;
    }



//        Intent intent = getIntent();
//
//        If(intent !=null && intent.getExtras()!=null){
//            String email= intent.getStringExtra("email");
//            String password= intent.getStringExtra("password");
//            TextView emailTextView = findViewById(R.id.address);
//            TextView passwordTextView = findViewById(R.id.Password);
//        }
//
//
//
//        private String getFallbackText(String input, String fallback){
//            return input != null && !input.isEmpty() ? input : fallback;
//        }

//        new android.os.Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(AfterLogin.this, SecondPage.class));
//                finish();
//            }
//        },3000);

}