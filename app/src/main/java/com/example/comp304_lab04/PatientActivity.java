package com.example.comp304_lab04;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class PatientActivity extends AppCompatActivity {
    private PatientViewModel patientViewModel;
    private EditText firstName;
    private EditText lastName;
    private EditText department;
    private EditText nurseID;
    private EditText room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient2);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        Button add = (Button) findViewById(R.id.button2);
        Button view = (Button) findViewById(R.id.button3);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                firstName = (EditText) findViewById(R.id.firstname);
                lastName = (EditText) findViewById(R.id.lastname);
                department = (EditText) findViewById(R.id.department);
                nurseID = (EditText) findViewById(R.id.nurseid);
                room = (EditText) findViewById(R.id.room);
                if (firstName.getText().toString().length() != 0 && lastName.getText().toString().length() != 0
                        && department.getText().toString().length() != 0 &&
                        nurseID.getText().toString().length() != 0 &&
                        room.getText().toString().length() != 0) {
                    String firstNameValue = firstName.getText().toString();
                    String lastNameValue = lastName.getText().toString();
                    String departmentValue = department.getText().toString();
                    int nurseIDValue = Integer.parseInt(nurseID.getText().toString());
                    String roomValue = room.getText().toString();

                    patientViewModel.insert(new Patient(firstNameValue, lastNameValue, departmentValue, nurseIDValue, roomValue));

                    Toast.makeText(PatientActivity.this, "Patient added in list", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PatientActivity.this, "Please ensure there are no null values", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PatientView.class);
                startActivity(intent);
            }
        });
    }
}
