package com.example.comp304_lab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class UpdateActivity extends AppCompatActivity {

    private EditText firstName, lastName, department, nurseID, room;
    private TextView patientID;
    private Button saveBtn;

    public static final String EXTRA_PATIENTID = "PATIENT_ID";
    public static final String EXTRA_FIRSTNAME = "FIRSTNAME";
    public static final String EXTRA_LASTNAME = "LASTNAME";
    public static final String EXTRA_DEPARTMENT = "DEPARTMENT";
    public static final String EXTRA_NURSEID = "NURSE_ID";
    public static final String EXTRA_ROOM = "ROOM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        patientID = (TextView) findViewById(R.id.patientID);
        firstName = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);
        department = (EditText) findViewById(R.id.department);
        nurseID = (EditText) findViewById(R.id.nurseid);
        room = (EditText) findViewById(R.id.room);
        saveBtn = (Button) findViewById(R.id.btnPatientSave);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_PATIENTID)) {
            patientID.setText(String.valueOf(intent.getIntExtra(EXTRA_PATIENTID, -1)));
            firstName.setText(intent.getStringExtra(EXTRA_FIRSTNAME));
            lastName.setText(intent.getStringExtra(EXTRA_LASTNAME));
            department.setText(intent.getStringExtra(EXTRA_DEPARTMENT));
            nurseID.setText(intent.getStringExtra(EXTRA_NURSEID));
            room.setText(intent.getStringExtra(EXTRA_ROOM));
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = firstName.getText().toString();
                String last = lastName.getText().toString();
                String dept = department.getText().toString();
                String nurse_id = nurseID.getText().toString();
                String room_id = room.getText().toString();

                if (first.isEmpty() || last.isEmpty() || dept.isEmpty() || nurse_id.isEmpty() || room_id.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "Please enter the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                savePatientID(first, last, dept, nurse_id, room_id);
            }
        });
    }

    private void savePatientID(String first, String last, String dept, String nurse_id, String room_id){

        Intent intent = new Intent();

        intent.putExtra(EXTRA_FIRSTNAME, first);
        intent.putExtra(EXTRA_LASTNAME, last);
        intent.putExtra(EXTRA_DEPARTMENT, dept);
        intent.putExtra(EXTRA_NURSEID, nurse_id);
        intent.putExtra(EXTRA_ROOM, room_id);

        int id = getIntent().getIntExtra(EXTRA_PATIENTID, -1);
        if (id != -1){
            intent.putExtra(EXTRA_PATIENTID, id);
        }

        setResult(RESULT_OK, intent);

        Toast.makeText(this, "Patient has been saved.", Toast.LENGTH_SHORT).show();

    }
}


