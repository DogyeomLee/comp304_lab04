package com.example.comp304_lab04;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PatientRepository {

    public PatientDao patientDao;
    private PatientRepository repository;
    private LiveData<List<Patient>> allPatients;

    public PatientRepository(Application application)
    {
        PatientDatabase db = PatientDatabase.getDatabase(application);
        patientDao = db.patientDao();
        allPatients = patientDao.getAllPatients();
    }

    public LiveData<List<Patient>> getAllPatients() {return allPatients; }

    public void insert(Patient patient) {
        PatientDatabase.databaseWriteExecutor.execute(() -> {
            patientDao.insert(patient);
        });
    }
    public void update(Patient patient)
    {
        PatientDatabase.databaseWriteExecutor.execute(() -> {
            patientDao.update(patient);

        });
    }

    public LiveData<Patient> findbyPatientID(int patientID) {return patientDao.getByPatientID(patientID); }
}
