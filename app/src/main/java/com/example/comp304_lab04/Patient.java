package com.example.comp304_lab04;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_table")
public class Patient
{
    @PrimaryKey(autoGenerate = true)
    private int patientID;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String department;

    @NonNull
    private int nurseID;

    @NonNull
    private String room;

    public Patient(@NonNull String firstName, @NonNull String lastName, @NonNull String department, @NonNull int nurseID, @NonNull String room) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.nurseID = nurseID;
        this.room = room;
    }

    public void setPatientID(int patientID) {this.patientID = patientID;}

    public int getPatientID() {
        return patientID;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @NonNull
    public String getDepartment() {
        return department;
    }

    public int getNurseID() {
        return nurseID;
    }

    @NonNull
    public String getRoom() {
        return room;
    }
}
