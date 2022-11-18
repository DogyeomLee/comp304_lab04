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

    // creating a variables for our recycler view.
    private RecyclerView patientRV;
    private static final int ADD_PATIENT_REQUEST = 1;
    private static final int EDIT_PATIENT_REQUEST = 2;
    private PatientViewModel patientVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        // initializing our variable for our recycler view and fab.
        patientRV = findViewById(R.id.patientRecycleView);
        FloatingActionButton fab = findViewById(R.id.fabPatientAdd);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent intent = new Intent(PatientActivity.this, UpdateActivity.class);
                startActivityForResult(intent, ADD_PATIENT_REQUEST);
//                startActivity(intent);
            }

        });

        // setting layout manager to our adapter class.
        patientRV.setLayoutManager(new LinearLayoutManager(this));
        patientRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final PatientRVAdapter adapter = new PatientRVAdapter();

        // setting adapter class for recycler view.
        patientRV.setAdapter(adapter);

        // passing a data from view modal.
        patientVM = ViewModelProviders.of(this).get(PatientViewModel.class);

        // below line is use to get all the courses from view modal.
        patientVM.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> models) {
                // when the data is changed in our models we are
                // adding that list to our adapter class.
                adapter.submitList(models);
            }
        });
        // below method is use to add swipe to delete method for item of recycler view.
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
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(patientRV);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new PatientRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Patient model) {
                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent intent = new Intent(PatientActivity.this, UpdateActivity.class);
                intent.putExtra(UpdateActivity.EXTRA_PATIENTID, model.getPatientID());
                intent.putExtra(UpdateActivity.EXTRA_FIRSTNAME, model.getFirstName());
                intent.putExtra(UpdateActivity.EXTRA_LASTNAME, model.getLastName());
                intent.putExtra(UpdateActivity.EXTRA_DEPARTMENT, model.getDepartment());
                intent.putExtra(UpdateActivity.EXTRA_NURSEID, model.getNurseID());
                intent.putExtra(UpdateActivity.EXTRA_ROOM, model.getRoom());

                // below line is to start a new activity and
                // adding a edit course constant.
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
            patientVM.insert(model);
            Toast.makeText(this, "Patient updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Patient not saved", Toast.LENGTH_SHORT).show();
        }
    }
}








//public class PatientActivity extends AppCompatActivity {
//    private PatientViewModel patientViewModel;
//    private EditText firstName;
//    private EditText lastName;
//    private EditText department;
//    private EditText nurseID;
//    private EditText room;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_patient2);
//
//        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
//
//        Button add = (Button) findViewById(R.id.button2);
//        Button view = (Button) findViewById(R.id.button3);
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                firstName = (EditText) findViewById(R.id.firstname);
//                lastName = (EditText) findViewById(R.id.lastname);
//                department = (EditText) findViewById(R.id.department);
//                nurseID = (EditText) findViewById(R.id.nurseid);
//                room = (EditText) findViewById(R.id.room);
//                if (firstName.getText().toString().length() != 0 && lastName.getText().toString().length() != 0
//                        && department.getText().toString().length() != 0 &&
//                        nurseID.getText().toString().length() != 0 &&
//                        room.getText().toString().length() != 0) {
//                    String firstNameValue = firstName.getText().toString();
//                    String lastNameValue = lastName.getText().toString();
//                    String departmentValue = department.getText().toString();
//                    int nurseIDValue = Integer.parseInt(nurseID.getText().toString());
//                    String roomValue = room.getText().toString();
//
//                    patientViewModel.insert(new Patient(firstNameValue, lastNameValue, departmentValue, nurseIDValue, roomValue));
//
//                    Toast.makeText(PatientActivity.this, "Patient added in list", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(PatientActivity.this, "Please ensure there are no null values", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), PatientView.class);
//                startActivity(intent);
//            }
//        });
//    }
//}
