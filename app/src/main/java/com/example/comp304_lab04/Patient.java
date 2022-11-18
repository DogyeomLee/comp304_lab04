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
    private String nurseID;

    @NonNull
    private String room;

    public Patient(@NonNull String firstName, @NonNull String lastName, @NonNull String department, @NonNull String nurseID, @NonNull String room) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.nurseID = nurseID;
        this.room = room;
    }

    public int getPatientID() {
        return patientID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getDepartment() {
        return department;
    }
    public String getNurseID() {
        return nurseID;
    }
    public String getRoom() {
        return room;
    }

    public void setPatientID(int newPatientID){this.patientID = newPatientID;}
    public void setFirstName(String newFirstName){this.firstName = newFirstName;}
    public void setLastName(String newLastName){this.lastName = newLastName;}
    public void setDepartment(String newDepartment){this.department = newDepartment;}
    public void setNurseID(String newNurseID){this.nurseID = newNurseID;}
    public void setRoom(String newRoom){this.room = newRoom;}
}
