package com.example.placementmanager.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placementmanager.R;
import com.example.placementmanager.database.AppDatabase;
import com.example.placementmanager.database.entities.Enrollment;
import com.example.placementmanager.database.entities.Student;
import com.example.placementmanager.database.entities.Company;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentActivity extends AppCompatActivity {

    EditText etStudentId, etCompanyId;
    Button btnEnroll;
    ListView enrollmentListView;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        etStudentId = findViewById(R.id.etStudentId);
        etCompanyId = findViewById(R.id.etCompanyId);
        btnEnroll = findViewById(R.id.btnEnroll);
        enrollmentListView = findViewById(R.id.enrollmentListView);

        db = AppDatabase.getInstance(this);

        btnEnroll.setOnClickListener(v -> {
            int studentId = Integer.parseInt(etStudentId.getText().toString());
            int companyId = Integer.parseInt(etCompanyId.getText().toString());

            Enrollment e = new Enrollment();
            e.studentId = studentId;
            e.companyId = companyId;
            e.isSelected = false;

            db.enrollmentDao().enrollStudent(e);
            loadEnrollments();
        });

        loadEnrollments();
    }

    private void loadEnrollments() {
        List<Enrollment> enrollments = db.enrollmentDao().getAllEnrollments();
        List<String> displayList = new ArrayList<>();

        for (Enrollment e : enrollments) {
            Student s = db.studentDao().getAllStudents().stream()
                    .filter(x -> x.id == e.studentId).findFirst().orElse(null);
            Company c = db.companyDao().getAllCompanies().stream()
                    .filter(x -> x.id == e.companyId).findFirst().orElse(null);

            if (s != null && c != null) {
                displayList.add("Student: " + s.name + " → " + c.name);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, displayList);
        enrollmentListView.setAdapter(adapter);
    }
}
