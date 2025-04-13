package com.example.splashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class LoginPage extends AppCompatActivity {

    Button loginBtn;

    EditText input1, input2;

    CheckBox cCb, cplusCb, html, java, css, ruby, python, js, php;

    RadioButton maleRb, femaleRb, otherRb;

    Spinner citySpinner;

    EditText dob;

    RadioGroup radioGroup;








    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.loginpage);

        loginBtn= findViewById(R.id.btn);
        input1 = findViewById(R.id.inputUsername);
        input2 = findViewById(R.id.inputPassword);
        cCb = findViewById(R.id.Cb);
        cplusCb = findViewById(R.id.cppCb);
        html = findViewById(R.id.html);
        java = findViewById(R.id.java);
        css = findViewById(R.id.css);
        ruby = findViewById(R.id.ruby);
        python = findViewById(R.id.python);
        js = findViewById(R.id.js);
        php = findViewById(R.id.php);
        maleRb = findViewById(R.id.maleRb);
        femaleRb = findViewById(R.id.femaleRb);
        otherRb = findViewById(R.id.otherRb);
        citySpinner = findViewById(R.id.email);

        setUpSpinner();

        loginBtn.setOnClickListener(v -> handleLogin());


    }

    private void setUpSpinner(){
        String[] cities = {
                "Select City","Pune", "mumbai", "Delhi", "Banglore", "Chennai", "Hyderabad", "Ahmedabad", "Kolkata","Jaipur",
                "Lucknow", "Kanpur","Thane","Bhopal","Vadodara","Agra"
        };

        ArrayAdapter<String>adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(R.layout.spinner);
        citySpinner.setAdapter(adapter);
    }

    private void handleLogin(){
        String username = input1.getText().toString().trim();
        String password = input2.getText().toString().trim();
        int selectedId = radioGroup.getCheckedRadioButtonId();
        String selectedCity =citySpinner.getSelectedItem().toString();

        ArrayList<String> selectedCourses = new ArrayList<>();
        if (cCb.isChecked()) selectedCourses.add(cCb.getText().toString());
        if (cplusCb.isChecked()) selectedCourses.add(cplusCb.getText().toString());
        if (html.isChecked()) selectedCourses.add(html.getText().toString());
        if (java.isChecked()) selectedCourses.add(java.getText().toString());
        if (css.isChecked()) selectedCourses.add(css.getText().toString());
        if (ruby.isChecked()) selectedCourses.add(ruby.getText().toString());
        if (python.isChecked()) selectedCourses.add(python.getText().toString());
        if (js.isChecked()) selectedCourses.add(js.getText().toString());
        if (php.isChecked()) selectedCourses.add(php.getText().toString());

        if(username.isEmpty()|| password.isEmpty() || selectedCourses.isEmpty()){
            Toast.makeText(LoginPage.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedOptionString = String.join(",", selectedCourses);
        RadioButton selectedRadioButton = findViewById(selectedId);
        String selectedOption = selectedRadioButton.getText().toString();

        Intent intent = new Intent(LoginPage.this, AfterLogin.class);
        intent.putExtra("User_Input", username);
        intent.putExtra("Pass_Input", password);
        intent.putExtra("Course_Input", selectedOptionString);
        intent.putExtra("Gender_Input", selectedOption);
        intent.putExtra("City_Input", selectedCity);
        startActivity(intent);
    }
}
