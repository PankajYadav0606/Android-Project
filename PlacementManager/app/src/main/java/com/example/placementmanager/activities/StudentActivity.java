package com.example.placementmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placementmanager.R;
import com.example.placementmanager.adapters.StudentAdapter;
import com.example.placementmanager.database.AppDatabase;
import com.example.placementmanager.database.entities.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentActivity extends AppCompatActivity {

    EditText etName, etEmail, etDept, etCgpa;
    Button btnAddStudent, btnViewCompanies;
    ListView studentListView;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etDept = findViewById(R.id.etDept);
        etCgpa = findViewById(R.id.etCgpa);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        studentListView = findViewById(R.id.studentListView);
        btnViewCompanies = findViewById(R.id.btnViewCompanies);

        db = AppDatabase.getInstance(this);

        btnAddStudent.setOnClickListener(v -> {
            Student s = new Student();
            s.name = etName.getText().toString();
            s.email = etEmail.getText().toString();
            s.department = etDept.getText().toString();
            s.cgpa = Double.parseDouble(etCgpa.getText().toString());
            s.placed = false;

            db.studentDao().insertStudent(s);
            loadStudents();
        });

        // Navigate to CompanyActivity
        btnViewCompanies.setOnClickListener(v -> {
            Intent intent = new Intent(StudentActivity.this, CompanyActivity.class);
            startActivity(intent);
        });

        loadStudents();
    }

    private void loadStudents() {
        List<Student> students = db.studentDao().getAllStudents();
        List<String> names = students.stream()
                .map(s -> s.name + " (" + s.department + ")")
                .collect(Collectors.toList());

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, names);
        StudentAdapter adapter = new StudentAdapter(this, students);
        studentListView.setAdapter(adapter);
    }
}
