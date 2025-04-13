package com.example.placementmanager.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.placementmanager.R;
import com.example.placementmanager.database.AppDatabase;
import com.example.placementmanager.adapters.CompanyAdapter;
import com.example.placementmanager.database.entities.Company;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyActivity extends AppCompatActivity {

    EditText etCompanyName, etJobRole, etEligibility, etDriveDate;
    Button btnAddCompany;
    ListView companyListView;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        etCompanyName = findViewById(R.id.etCompanyName);
        etJobRole = findViewById(R.id.etJobRole);
        etEligibility = findViewById(R.id.etEligibility);
        etDriveDate = findViewById(R.id.etDriveDate);
        btnAddCompany = findViewById(R.id.btnAddCompany);
        companyListView = findViewById(R.id.companyListView);

        db = AppDatabase.getInstance(this);

        btnAddCompany.setOnClickListener(v -> {
            Company c = new Company();
            c.name = etCompanyName.getText().toString();
            c.jobRole = etJobRole.getText().toString();
            c.eligibilityCriteria = etEligibility.getText().toString();
            c.driveDate = etDriveDate.getText().toString();

            db.companyDao().insertCompany(c);
            loadCompanies();
        });

        loadCompanies();
    }

    private void loadCompanies() {
        List<Company> companies = db.companyDao().getAllCompanies();
        List<String> names = companies.stream()
                .map(c -> c.name + " - " + c.jobRole + " (" + c.driveDate + ")")
                .collect(Collectors.toList());

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, names);

        CompanyAdapter adapter = new CompanyAdapter(this, companies);
        companyListView.setAdapter(adapter);
    }
}
