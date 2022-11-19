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

public class TestUpdateActivity extends AppCompatActivity {

    private EditText patientID, nurseID, bpl, bph, temperature;
    private TextView testID;
    private Button saveBtn;

    public static final String EXTRA_TESTID = "TESTID";
    public static final String EXTRA_PATIENTID = "PATIENTID";
    public static final String EXTRA_NURSEID = "NURSEID";
    public static final String EXTRA_BPL = "BPL";
    public static final String EXTRA_BPH = "BPH";
    public static final String EXTRA_TEMPERATURE = "TEMPERATURE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_update);

        testID = (TextView) findViewById(R.id.TestID);
        patientID = (EditText) findViewById(R.id.PatientIDET);
        nurseID = (EditText) findViewById(R.id.NurseIDET);
        bpl = (EditText) findViewById(R.id.BplET);
        bph = (EditText) findViewById(R.id.BphET);
        temperature = (EditText) findViewById(R.id.TemperatureET);
        saveBtn = (Button) findViewById(R.id.btnTestSave);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_TESTID)) {
            testID.setText(String.valueOf(intent.getIntExtra(EXTRA_TESTID, -1)));
            patientID.setText(String.valueOf(intent.getIntExtra(EXTRA_PATIENTID, -1)));
            nurseID.setText(intent.getStringExtra(EXTRA_NURSEID));
            bpl.setText(intent.getStringExtra(EXTRA_BPL));
            bph.setText(intent.getStringExtra(EXTRA_BPH));
            temperature.setText(String.valueOf(intent.getIntExtra(EXTRA_TEMPERATURE, -1)));
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientid = patientID.getText().toString();
                String nurseid = nurseID.getText().toString();
                String BPL = bpl.getText().toString();
                String BPH = bph.getText().toString();
                String temp = temperature.getText().toString();

                if (patientid.isEmpty() || nurseid.isEmpty() || BPL.isEmpty() || BPH.isEmpty() || temp.isEmpty()) {
                    Toast.makeText(TestUpdateActivity.this, "Please enter the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Integer.parseInt(patientid);
                    Integer.parseInt(temp);
                }
                catch (NumberFormatException ex) {
                    Toast.makeText(TestUpdateActivity.this, "Please enter a number for Patient ID and Temperature", Toast.LENGTH_SHORT).show();
                    return;
                }

                savePatientID(patientid, nurseid, BPL, BPH, temp);
            }
        });
    }

    private void savePatientID(String patientid, String nurseid, String BPL, String BPH, String temp){

        Intent intent = new Intent();

        intent.putExtra(EXTRA_PATIENTID, patientid);
        intent.putExtra(EXTRA_NURSEID, nurseid);
        intent.putExtra(EXTRA_BPL, BPL);
        intent.putExtra(EXTRA_BPH, BPH);
        intent.putExtra(EXTRA_TEMPERATURE, temp);

        int id = getIntent().getIntExtra(EXTRA_TESTID, -1);
        if (id != -1){
            intent.putExtra(EXTRA_TESTID, id);
        }

        setResult(RESULT_OK, intent);

        Toast.makeText(this, "Test has been saved.", Toast.LENGTH_SHORT).show();

    }
}


