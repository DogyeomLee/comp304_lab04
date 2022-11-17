package com.example.comp304_lab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class UpdateActivity extends AppCompatActivity {
    private PatientViewModel patientViewModel;
    private EditText firstName;
    private EditText lastName;
    private EditText department;
    private EditText nurseID;
    private EditText room;
    private EditText patientid;
    private int patientID;
    private Patient patient;
    private PatientDao patientDao;
    private PatientDatabase patientDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        Intent intent2 = getIntent();
        int PID = intent2.getIntExtra("PID", patientID);

        Button update = (Button) findViewById(R.id.button2);
        Button view = (Button) findViewById(R.id.button3);
        firstName = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);
        department = (EditText) findViewById(R.id.department);
        nurseID = (EditText) findViewById(R.id.nurseid);
        room = (EditText) findViewById(R.id.room);
        patientid = (EditText) findViewById(R.id.patientid);


        patientViewModel.findByPatientID(PID).observe(this, new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient)
            {
                firstName.setText(patient.getFirstName());
                lastName.setText(patient.getLastName());
                department.setText(patient.getDepartment());
                nurseID.setText(String.valueOf(patient.getNurseID()));
                room.setText(patient.getRoom());

                String firstNameValue = firstName.getText().toString();
                String lastNameValue = lastName.getText().toString();
                String departmentValue = department.getText().toString();
                int nurseIDValue = Integer.parseInt(nurseID.getText().toString());
                String roomValue = room.getText().toString();

                patientViewModel.update(new Patient(firstNameValue, lastNameValue, departmentValue, nurseIDValue, roomValue));
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PatientView.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                patient.setPatientID(PID);

                if (firstName.getText().toString().length() != 0 && lastName.getText().toString().length() != 0
                        && department.getText().toString().length() != 0 &&
                        nurseID.getText().toString().length() != 0 &&
                        room.getText().toString().length() != 0)
                {
                    EditText firstName1 = (EditText) findViewById(R.id.firstname);
                    EditText lastName1 = (EditText) findViewById(R.id.lastname);
                    EditText department1 = (EditText) findViewById(R.id.department);
                    EditText nurseID1 = (EditText) findViewById(R.id.nurseid);
                    EditText room1 = (EditText) findViewById(R.id.room);

                    String firstNameValue = firstName1.getText().toString();
                    String lastNameValue = lastName1.getText().toString();
                    String departmentValue = department1.getText().toString();
                    int nurseIDValue = Integer.parseInt(nurseID1.getText().toString());
                    String roomValue = room1.getText().toString();

                    patientViewModel.update(new Patient(firstNameValue, lastNameValue, departmentValue, nurseIDValue, roomValue));

                    Toast.makeText(UpdateActivity.this, "Patient updated in list", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UpdateActivity.this, "Please ensure there are no null values or Check patient ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
