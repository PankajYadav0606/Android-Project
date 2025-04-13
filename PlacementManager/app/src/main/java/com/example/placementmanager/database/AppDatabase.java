package com.example.placementmanager.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.placementmanager.database.dao.CompanyDao;
import com.example.placementmanager.database.dao.EnrollmentDao;
import com.example.placementmanager.database.dao.ExamRoundDao;
import com.example.placementmanager.database.dao.ResultDao;
import com.example.placementmanager.database.dao.StudentDao;
import com.example.placementmanager.database.entities.Company;
import com.example.placementmanager.database.entities.Enrollment;
import com.example.placementmanager.database.entities.ExamRound;
import com.example.placementmanager.database.entities.Result;
import com.example.placementmanager.database.entities.Student;

@Database(entities = {
        Student.class,
        Company.class,
        Enrollment.class,
        ExamRound.class,
        Result.class
}, version = 5)

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract StudentDao studentDao();
    public abstract CompanyDao companyDao();
    public abstract EnrollmentDao enrollmentDao();
    public abstract ExamRoundDao examRoundDao();
    public abstract ResultDao resultDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "placement-db")
                    .fallbackToDestructiveMigration() // important when version changes
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
