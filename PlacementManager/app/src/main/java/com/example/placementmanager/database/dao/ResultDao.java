package com.example.placementmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.placementmanager.database.entities.Result;

import java.util.List;

@Dao
public interface ResultDao {

    @Insert
    void insertResult(Result result);

    @Query("SELECT * FROM Result")
    List<Result> getAllResults();

    @Query("SELECT * FROM Result WHERE roundId = :roundId")
    List<Result> getResultsForRound(int roundId);

    @Query("SELECT * FROM Result WHERE studentId = :studentId")
    List<Result> getResultsForStudent(int studentId);
}
