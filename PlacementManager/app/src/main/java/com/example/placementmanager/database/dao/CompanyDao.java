package com.example.placementmanager.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.placementmanager.database.entities.Company;

import java.util.List;

@Dao
public interface CompanyDao {
    @Insert
    void insertCompany(Company company);

    @Query("SELECT * FROM Company")
    List<Company> getAllCompanies();

    @Query("DELETE FROM Company WHERE id = :id")
    void deleteCompany(int id);
}
