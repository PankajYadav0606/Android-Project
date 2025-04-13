package com.example.placementmanager.activities;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.placementmanager.R;
import com.example.placementmanager.database.AppDatabase;
import com.example.placementmanager.database.entities.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportActivity extends AppCompatActivity {

    Button btnGenerateReport;
    ListView reportListView;
    File pdfFile; // Save the file to reuse for sharing


    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        btnGenerateReport = findViewById(R.id.btnGenerateReport);
        reportListView = findViewById(R.id.reportListView);

        db = AppDatabase.getInstance(this);

        btnGenerateReport.setOnClickListener(v -> generateReport());
        Button btnSharePDF = findViewById(R.id.btnSharePDF);

        btnSharePDF.setOnClickListener(v -> {
            if (pdfFile != null && pdfFile.exists()) {
                sharePDF(pdfFile);
            } else {
                Toast.makeText(this, "Please generate the report first.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generateReport() {
        List<String> finalPlacements = new ArrayList<>();
        List<Enrollment> enrollments = db.enrollmentDao().getAllEnrollments();


        for (Enrollment e : enrollments) {
            int studentId = e.studentId;
            int companyId = e.companyId;

            Student student = db.studentDao().getAllStudents().stream()
                    .filter(s -> s.id == studentId).findFirst().orElse(null);

            Company company = db.companyDao().getAllCompanies().stream()
                    .filter(c -> c.id == companyId).findFirst().orElse(null);

            if (student != null && company != null) {
                List<ExamRound> rounds = db.examRoundDao().getRoundsForCompany(companyId);

                boolean allPassed = true;
                for (ExamRound round : rounds) {
                    Result result = db.resultDao().getResultsForStudent(studentId).stream()
                            .filter(r -> r.roundId == round.id && r.passed)
                            .findFirst().orElse(null);
                    if (result == null) {
                        allPassed = false;
                        break;
                    }
                }

                if (allPassed) {
                    // Mark as placed
                    student.placed = true;
                    db.studentDao().updatePlacedStatus(student.id, true);
                    finalPlacements.add("🎉 " + student.name + " placed in " + company.name);
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, finalPlacements);
        reportListView.setAdapter(adapter);

// Generate PDF file
        generatePDF(finalPlacements);
    }

    private void generatePDF(List<String> dataList) {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create(); // A4 size
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        int x = 40, y = 50;
        paint.setTextSize(16);
        canvas.drawText("Final Placement Report", x, y, paint);

        y += 30;
        paint.setTextSize(12);

        for (String line : dataList) {
            canvas.drawText(line, x, y, paint);
            y += 20;
        }

        pdfDocument.finishPage(page);

        pdfFile = new File(getExternalFilesDir(null), "PlacementReport.pdf");

        try {
            pdfDocument.writeTo(new FileOutputStream(pdfFile));
            Toast.makeText(this, "PDF saved to: " + pdfFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating PDF", Toast.LENGTH_SHORT).show();
        }

        pdfDocument.close();
    }

    private void sharePDF(File file) {
        Uri uri = FileProvider.getUriForFile(
                this,
                getApplicationContext().getPackageName() + ".provider",
                file
        );

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(Intent.createChooser(shareIntent, "Share Placement Report via"));
    }


}
