package com.example.comp304_lab04;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_table")
public class Test
{
    @PrimaryKey(autoGenerate = true)
    private int testID;

    @NonNull
    private int patientID;

    @NonNull
    private String nurseID;

    @NonNull
    private String bpl;

    @NonNull
    private String bph;

    @NonNull
    private int temperature;

    public Test(@NonNull int patientID, @NonNull String nurseID, @NonNull String bpl, @NonNull String bph, @NonNull int temperature) {
        this.patientID = patientID;
        this.nurseID = nurseID;
        this.bpl = bpl;
        this.bph = bph;
        this.temperature = temperature;
    }

    public int getTestID() {
        return testID;
    }
    public int getPatientID() {
        return patientID;
    }
    public String getNurseID() {
        return nurseID;
    }
    public String getBpl() {
        return bpl;
    }
    public String getBph() {
        return bph;
    }
    public int getTemperature() {
        return temperature;
    }

    public void setTestID(int newTestID){this.testID = newTestID;}
    public void setPatientID(int newPatientID){this.patientID = newPatientID;}
    public void setNurseID(String newNurseID){this.nurseID = newNurseID;}
    public void setBpl(String newBpl){this.bpl = newBpl;}
    public void setBph(String newBph){this.bph = newBph;}
    public void setTemperature(int newTemperature){this.temperature = newTemperature;}
}
