package com.example.comp304_lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if(userfname.getText().toString().isEmpty())
                {
                    Toast.makeText(getBaseContext(), "Write First Name", Toast.LENGTH_SHORT).show();
                }
                if(userlname.getText().toString().isEmpty())
                {
                    Toast.makeText(getBaseContext(), "Write Last Name", Toast.LENGTH_SHORT).show();
                }
                if(userdepartment.getText().toString().isEmpty())
                {
                    Toast.makeText(getBaseContext(), "Write User department", Toast.LENGTH_SHORT).show();
                }
                if(userid.getText().toString().isEmpty())
                {
                    Toast.makeText(getBaseContext(), "Write User ID", Toast.LENGTH_SHORT).show();
                }
                if(password.getText().toString().isEmpty())
                {
                    Toast.makeText(getBaseContext(), "Write Password", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    intent.putExtra("nurseID", userid.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }
}