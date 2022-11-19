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

public class TestActivity extends AppCompatActivity {

    private RecyclerView testRV;
    private static final int ADD_TEST_REQUEST = 1;
    private static final int EDIT_TEST_REQUEST = 2;
    private TestViewModel testVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testRV = findViewById(R.id.testRecycleView);
        FloatingActionButton fab = findViewById(R.id.fabTestAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, TestUpdateActivity.class);
                startActivityForResult(intent, ADD_TEST_REQUEST);
            }

        });

        testRV.setLayoutManager(new LinearLayoutManager(this));
        testRV.setHasFixedSize(true);

        final TestRVAdapter adapter = new TestRVAdapter();

        testRV.setAdapter(adapter);

        testVM = ViewModelProviders.of(this).get(TestViewModel.class);

        testVM.getAllTests().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> models) {
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
                testVM.delete(adapter.getTestAt(viewHolder.getAdapterPosition()));
                Toast.makeText(TestActivity.this, "Test deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(testRV);

        adapter.setOnItemClickListener(new TestRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Test model) {

                Intent intent = new Intent(TestActivity.this, TestUpdateActivity.class);
                intent.putExtra(TestUpdateActivity.EXTRA_TESTID, model.getTestID());
                intent.putExtra(TestUpdateActivity.EXTRA_PATIENTID, model.getPatientID());
                intent.putExtra(TestUpdateActivity.EXTRA_NURSEID, model.getNurseID());
                intent.putExtra(TestUpdateActivity.EXTRA_BPL, model.getBpl());
                intent.putExtra(TestUpdateActivity.EXTRA_BPH, model.getBph());
                intent.putExtra(TestUpdateActivity.EXTRA_TEMPERATURE, model.getTemperature());

                startActivityForResult(intent, EDIT_TEST_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TEST_REQUEST && resultCode == RESULT_OK) {
            int patientID = Integer.parseInt(data.getStringExtra(TestUpdateActivity.EXTRA_PATIENTID));
            String nurseID = data.getStringExtra(TestUpdateActivity.EXTRA_NURSEID);
            String bpl = data.getStringExtra(TestUpdateActivity.EXTRA_BPL);
            String bph = data.getStringExtra(TestUpdateActivity.EXTRA_BPH);
            int temperature = Integer.parseInt(data.getStringExtra(TestUpdateActivity.EXTRA_TEMPERATURE));
            Test model = new Test(patientID, nurseID, bpl, bph, temperature);
            testVM.insert(model);
            Toast.makeText(this, "Test saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_TEST_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(TestUpdateActivity.EXTRA_TESTID, -1);
            if (id == -1) {
                Toast.makeText(this, "Test can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            int patientID = Integer.parseInt(data.getStringExtra(TestUpdateActivity.EXTRA_PATIENTID));
            String nurseID = data.getStringExtra(UpdateActivity.EXTRA_LASTNAME);
            String bpl = data.getStringExtra(UpdateActivity.EXTRA_DEPARTMENT);
            String bph = data.getStringExtra(UpdateActivity.EXTRA_NURSEID);
            int temperature = Integer.parseInt(data.getStringExtra(TestUpdateActivity.EXTRA_TEMPERATURE));
            Test model = new Test(patientID, nurseID, bpl, bph, temperature);
            testVM.update(model);
            Toast.makeText(this, "Test updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Test not saved", Toast.LENGTH_SHORT).show();
        }
    }
}