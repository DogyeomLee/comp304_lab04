package com.example.comp304_lab04;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
    private LiveData<List<Patient>> allPatients;

    public PatientViewModel(Application application) {
        super((application));
        patientRepository = new PatientRepository(application);
        allPatients = patientRepository.getAllPatients();
    }

    public  LiveData<Patient> findByPatientID(int patientID) {return patientRepository.findbyPatientID(patientID); }

    public void insert(Patient patient) { patientRepository.insert(patient); }

    public LiveData<List<Patient>> getAllPatients() { return allPatients; }
}
