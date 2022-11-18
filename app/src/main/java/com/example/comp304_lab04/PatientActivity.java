package com.example.comp304_lab04;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PatientActivity extends AppCompatActivity {

    private RecyclerView patientRV;
    private static final int ADD_PATIENT_REQUEST = 1;
    private static final int EDIT_PATIENT_REQUEST = 2;
    private PatientViewModel patientVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        patientRV = findViewById(R.id.patientRecycleView);
        FloatingActionButton fab = findViewById(R.id.fabPatientAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientActivity.this, UpdateActivity.class);
                startActivityForResult(intent, ADD_PATIENT_REQUEST);
            }

        });

        patientRV.setLayoutManager(new LinearLayoutManager(this));
        patientRV.setHasFixedSize(true);

        final PatientRVAdapter adapter = new PatientRVAdapter();

        patientRV.setAdapter(adapter);

        patientVM = ViewModelProviders.of(this).get(PatientViewModel.class);

        patientVM.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> models) {
                adapter.submitList(models);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                patientVM.delete(adapter.getPatientAt(viewHolder.getAdapterPosition()));
                Toast.makeText(PatientActivity.this, "Patient deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(patientRV);

        adapter.setOnItemClickListener(new PatientRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Patient model) {

                Intent intent = new Intent(PatientActivity.this, UpdateActivity.class);
                intent.putExtra(UpdateActivity.EXTRA_PATIENTID, model.getPatientID());
                intent.putExtra(UpdateActivity.EXTRA_FIRSTNAME, model.getFirstName());
                intent.putExtra(UpdateActivity.EXTRA_LASTNAME, model.getLastName());
                intent.putExtra(UpdateActivity.EXTRA_DEPARTMENT, model.getDepartment());
                intent.putExtra(UpdateActivity.EXTRA_NURSEID, model.getNurseID());
                intent.putExtra(UpdateActivity.EXTRA_ROOM, model.getRoom());

                startActivityForResult(intent, EDIT_PATIENT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PATIENT_REQUEST && resultCode == RESULT_OK) {
            String fName = data.getStringExtra(UpdateActivity.EXTRA_FIRSTNAME);
            String lName = data.getStringExtra(UpdateActivity.EXTRA_LASTNAME);
            String department = data.getStringExtra(UpdateActivity.EXTRA_DEPARTMENT);
            String nurseId = data.getStringExtra(UpdateActivity.EXTRA_NURSEID);
            String room = data.getStringExtra(UpdateActivity.EXTRA_ROOM);
            Patient model = new Patient(fName, lName, department, nurseId, room);
            patientVM.insert(model);
            Toast.makeText(this, "Patient saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_PATIENT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(UpdateActivity.EXTRA_PATIENTID, -1);
            if (id == -1) {
                Toast.makeText(this, "Patient can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String fName = data.getStringExtra(UpdateActivity.EXTRA_FIRSTNAME);
            String lName = data.getStringExtra(UpdateActivity.EXTRA_LASTNAME);
            String department = data.getStringExtra(UpdateActivity.EXTRA_DEPARTMENT);
            String nurseId = data.getStringExtra(UpdateActivity.EXTRA_NURSEID);
            String room = data.getStringExtra(UpdateActivity.EXTRA_ROOM);
            Patient model = new Patient(fName, lName, department, nurseId, room);
            patientVM.update(model);
            Toast.makeText(this, "Patient updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Patient not saved", Toast.LENGTH_SHORT).show();
        }
    }
}