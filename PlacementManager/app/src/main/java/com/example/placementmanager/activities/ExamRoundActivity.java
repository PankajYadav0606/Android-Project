package com.example.placementmanager.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placementmanager.R;
import com.example.placementmanager.database.AppDatabase;
import com.example.placementmanager.database.entities.Company;
import com.example.placementmanager.database.entities.ExamRound;

import java.util.ArrayList;
import java.util.List;

public class ExamRoundActivity extends AppCompatActivity {

    EditText etCompanyId, etRoundType, etSchedule;
    Button btnAddRound;
    ListView roundListView;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_round);

        etCompanyId = findViewById(R.id.etCompanyId);
        etRoundType = findViewById(R.id.etRoundType);
        etSchedule = findViewById(R.id.etSchedule);
        btnAddRound = findViewById(R.id.btnAddRound);
        roundListView = findViewById(R.id.roundListView);

        db = AppDatabase.getInstance(this);

        btnAddRound.setOnClickListener(v -> {
            ExamRound round = new ExamRound();
            round.companyId = Integer.parseInt(etCompanyId.getText().toString());
            round.roundType = etRoundType.getText().toString();
            round.schedule = etSchedule.getText().toString();

            db.examRoundDao().insertRound(round);
            loadRounds();
        });

        loadRounds();
    }

    private void loadRounds() {
        List<ExamRound> rounds = db.examRoundDao().getAllRounds();
        List<String> displayList = new ArrayList<>();

        for (ExamRound round : rounds) {
            Company company = db.companyDao().getAllCompanies().stream()
                    .filter(c -> c.id == round.companyId).findFirst().orElse(null);

            if (company != null) {
                displayList.add(company.name + " - " + round.roundType + " (" + round.schedule + ")");
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, displayList);
        roundListView.setAdapter(adapter);
    }
}
