package com.example.placementmanager.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placementmanager.R;
import com.example.placementmanager.database.AppDatabase;
import com.example.placementmanager.database.entities.Result;
import com.example.placementmanager.database.entities.ExamRound;
import com.example.placementmanager.database.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    EditText etStudentId, etRoundId, etPassed;
    Button btnAddResult;
    ListView resultListView;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        etStudentId = findViewById(R.id.etStudentId);
        etRoundId = findViewById(R.id.etRoundId);
        etPassed = findViewById(R.id.etPassed);
        btnAddResult = findViewById(R.id.btnAddResult);
        resultListView = findViewById(R.id.resultListView);

        db = AppDatabase.getInstance(this);

        btnAddResult.setOnClickListener(v -> {
            Result r = new Result();
            r.studentId = Integer.parseInt(etStudentId.getText().toString());
            r.roundId = Integer.parseInt(etRoundId.getText().toString());
            r.passed = Boolean.parseBoolean(etPassed.getText().toString());

            db.resultDao().insertResult(r);
            loadResults();
        });

        loadResults();
    }

    private void loadResults() {
        List<Result> results = db.resultDao().getAllResults();
        List<String> displayList = new ArrayList<>();

        for (Result r : results) {
            Student s = db.studentDao().getAllStudents().stream()
                    .filter(x -> x.id == r.studentId).findFirst().orElse(null);
            ExamRound round = db.examRoundDao().getAllRounds().stream()
                    .filter(x -> x.id == r.roundId).findFirst().orElse(null);

            if (s != null && round != null) {
                displayList.add("Student: " + s.name + " → " +
                        round.roundType + " - " + (r.passed ? "Passed" : "Failed"));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, displayList);
        resultListView.setAdapter(adapter);
    }
}
