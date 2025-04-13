package com.example.placementmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.placementmanager.database.entities.ExamRound;

import java.util.List;

@Dao
public interface ExamRoundDao {

    @Insert
    void insertRound(ExamRound round);

    @Query("SELECT * FROM ExamRound")
    List<ExamRound> getAllRounds();

    @Query("SELECT * FROM ExamRound WHERE companyId = :companyId")
    List<ExamRound> getRoundsForCompany(int companyId);
}
