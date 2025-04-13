package com.example.placementmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.placementmanager.database.entities.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student student);

    @Query("SELECT * FROM Student")
    List<Student> getAllStudents();

    @Query("DELETE FROM Student WHERE id = :id")
    void deleteStudent(int id);

    @Query("UPDATE Student SET placed = :placed WHERE id = :studentId")
    void updatePlacedStatus(int studentId, boolean placed);
}
