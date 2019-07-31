package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    EditText lead,name,company,mobile,address;
    Button update;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        databaseHelper=new DatabaseHelper(this);

        lead=(EditText)findViewById(R.id.lead);
        name=(EditText)findViewById(R.id.updatename);
        company=(EditText)findViewById(R.id.updatecompany_name);
        address=(EditText)findViewById(R.id.updateaddress);
        mobile=(EditText)findViewById(R.id.updatemobile);
        update=(Button)findViewById(R.id.update_button);

        update();
    }

    public void update()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean updated = databaseHelper.update(lead.getText().toString(),name.getText().toString(),
                      company.getText().toString(),mobile.getText().toString(),address.getText().toString());
                if(updated)
                    Toast.makeText(Update.this, "Data Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Update.this, "Failed to Update", Toast.LENGTH_SHORT).show();

            }
        });



    }
}
