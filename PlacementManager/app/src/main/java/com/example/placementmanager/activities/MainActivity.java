package com.example.placementmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placementmanager.R;

public class MainActivity extends AppCompatActivity {

    Button btnStudents, btnCompanies, btnEnrollments, btnRounds, btnResults, btnReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStudents = findViewById(R.id.btnStudents);
        btnCompanies = findViewById(R.id.btnCompanies);
        btnEnrollments = findViewById(R.id.btnEnrollments);
        btnRounds = findViewById(R.id.btnRounds);
        btnResults = findViewById(R.id.btnResults);
        btnReport = findViewById(R.id.btnReport);

        btnStudents.setOnClickListener(v -> startActivity(new Intent(this, StudentActivity.class)));
        btnCompanies.setOnClickListener(v -> startActivity(new Intent(this, CompanyActivity.class)));
        btnEnrollments.setOnClickListener(v -> startActivity(new Intent(this, EnrollmentActivity.class)));
        btnRounds.setOnClickListener(v -> startActivity(new Intent(this, ExamRoundActivity.class)));
        btnResults.setOnClickListener(v -> startActivity(new Intent(this, ResultActivity.class)));
        btnReport.setOnClickListener(v -> startActivity(new Intent(this, ReportActivity.class)));
    }
}
