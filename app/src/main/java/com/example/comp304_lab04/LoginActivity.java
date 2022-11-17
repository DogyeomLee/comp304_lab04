package com.example.comp304_lab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = (Button) findViewById(R.id.login);
        Button btn2 = (Button) findViewById(R.id.cancer);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        EditText userfname = findViewById(R.id.user_fname);
        EditText userlname = findViewById(R.id.user_lname);
        EditText userdepartment = findViewById(R.id.userdepartment);
        EditText userid = findViewById(R.id.user_id);
        EditText password = findViewById(R.id.user_password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(userfname.getText().toString().length()!=0 && userlname.getText().toString().length()!=0 &&
                        userdepartment.getText().toString().length()!=0 && userid.getText().toString().length()!=0 &&
                        password.getText().toString().length()!=0)
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    intent.putExtra("nurseID", userid.getText().toString());
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Please ensure there are no null values", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}