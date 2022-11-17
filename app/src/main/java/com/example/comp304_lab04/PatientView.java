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

import java.util.List;

public class PatientView extends AppCompatActivity {

    private Object TextView;

    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        TextView view = (TextView) findViewById(R.id.patienview);

        EditText patientid = (EditText) findViewById(R.id.id);

        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients)
            {
                String output="";
                for(Patient patient : patients)
                {
                    output+= patient.getPatientID()+",";
                    output+= patient.getFirstName()+",";
                    output+= patient.getLastName() +",";
                    output+= patient.getDepartment() +",";
                    output+= patient.getNurseID() +", ";
                    output+= patient.getRoom() +"\n";
                }
                view.setText(output);
                patientViewModel.getAllPatients();
            }
        });

        Button add = (Button) findViewById(R.id.Add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), PatientActivity.class);
                startActivity(intent);
            }
        });
        Button update = (Button) findViewById(R.id.Update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(patientid.getText().toString().length() != 0)
                {
                    Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                    intent.putExtra("PID", Integer.parseInt(patientid.getText().toString()));
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PatientView.this, "Please enter the Patient ID", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}