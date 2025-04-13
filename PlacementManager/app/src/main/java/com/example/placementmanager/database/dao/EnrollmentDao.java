package com.example.placementmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.placementmanager.database.entities.Enrollment;

import java.util.List;

@Dao
public interface EnrollmentDao {

    @Insert
    void enrollStudent(Enrollment enrollment);

    @Query("SELECT * FROM Enrollment")
    List<Enrollment> getAllEnrollments();

    @Query("SELECT * FROM Enrollment WHERE companyId = :companyId")
    List<Enrollment> getEnrollmentsForCompany(int companyId);

    @Query("SELECT * FROM Enrollment WHERE studentId = :studentId")
    List<Enrollment> getEnrollmentsForStudent(int studentId);
}
