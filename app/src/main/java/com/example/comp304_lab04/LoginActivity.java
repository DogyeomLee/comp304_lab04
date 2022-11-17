package com.example.comp304_lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        EditText username = findViewById(R.id.user_name);
        EditText userid = findViewById(R.id.user_id);
        EditText password = findViewById(R.id.user_password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(username.getText().toString().isEmpty())
                {
                    Toast.makeText(getBaseContext(), "Write User Name", Toast.LENGTH_SHORT).show();
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
                    startActivity(intent);
                }

            }
        });
    }
}