package com.example.comp304_lab04;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao
{

    @Insert
    void insert(Patient patient);

    @Update
    void update(Patient patient);

    @Delete
    void delete(Patient patient);

    @Query("DELETE FROM patient_table")
    void deleteAll();

    @Query("Select * FROM patient_table where patientID = :patientID")
    LiveData<Patient> getByPatientID(int patientID);

    @Query("Select * FROM patient_table")
    LiveData<List<Patient>> getAllPatients();
}
