package com.example.comp304_lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btn1 = (Button) findViewById(R.id.patientbut);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), PatientActivity.class);
                startActivity(intent);
            }
        });

        Button btn2 = (Button) findViewById(R.id.testbut);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
            }
        });

        Button btn3 = (Button) findViewById(R.id.updatebut);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(intent);
            }
        });
    }
}