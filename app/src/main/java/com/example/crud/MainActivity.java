package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id,password;
    Button login;
    TextView registration;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        registration=(TextView)findViewById(R.id.registration);
        databaseHelper= new DatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminlogin();

            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(MainActivity.this,Registration.class));
            }
        });
    }
    private void adminlogin() {
         String uid=id.getText().toString();
         String pass=password.getText().toString();
         if(uid.equals("tarun") && pass.equals("12345"))
         {
             Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
             startActivity(new Intent(MainActivity.this,Dashboard.class));
         }
        else
             Toast.makeText(MainActivity.this, "Only admin can login", Toast.LENGTH_SHORT).show();
    }
}