package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText name,companyname,address,mobile;
    Button registration;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        databaseHelper= new DatabaseHelper(this);

        name=(EditText)findViewById(R.id.name);
        companyname=(EditText)findViewById(R.id.company_name);
        address=(EditText)findViewById(R.id.address);
        mobile=(EditText)findViewById(R.id.mobile);
        registration=(Button)findViewById(R.id.registration_button);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getregistraiondata();
                Toast.makeText(getApplicationContext(),"Successfully Registration Done",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getregistraiondata() {


        String Name=name.getText().toString().trim();
        if(Name.matches(""))
            Toast.makeText(this, "Please Fill Name", Toast.LENGTH_SHORT).show();

        String Company=companyname.getText().toString().trim();
        if(Company.matches(""))
            Toast.makeText(this, "Please Fill Company", Toast.LENGTH_SHORT).show();

        String Postaladdress=address.getText().toString().trim();
        if(Postaladdress.matches(""))
            Toast.makeText(this, "Please Fill Address", Toast.LENGTH_SHORT).show();

        String Mobile=mobile.getText().toString().trim();
        if(Mobile.matches(""))
            Toast.makeText(this, "Please Fill Mobile", Toast.LENGTH_SHORT).show();

       boolean inserted= databaseHelper.insertdata(Name,Company,Postaladdress,Mobile);
       if(inserted)
           Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
       else
           Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
        name.getText().clear();
        companyname.getText().clear();
        address.getText().clear();
        mobile.getText().clear();
    }

    public void readdata()
    {

    }
}
