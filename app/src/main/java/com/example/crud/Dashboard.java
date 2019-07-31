package com.example.crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TableLayout tableLayout;
    Button read,Update,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        databaseHelper=new DatabaseHelper(this);

        read=(Button)findViewById(R.id.read);
        Update=(Button)findViewById(R.id.update);
        delete=(Button)findViewById(R.id.delete);

        read();
        update();

    }

    public void read()
    {
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewalldata();
            }
        });
    }

    public void update()
    {
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,Update.class));
            }
        });
    }

     public void viewalldata()
    {
        Cursor result=databaseHelper.getdata();
        if(result.getCount() == 0)
        {
            showdata("Error","No Data Fount");
            return;
        }
         StringBuilder stringBuilder=new StringBuilder();
        while (result.moveToNext())
        {
            stringBuilder.append("Id: "+result.getString(0)+"\n");
            stringBuilder.append("Lead: "+result.getString(1)+"\n");
            stringBuilder.append("Name: "+result.getString(2)+"\n");
            stringBuilder.append("Company: "+result.getString(3)+"\n");
            stringBuilder.append("Mobile: "+result.getString(4)+"\n");
            stringBuilder.append("Address: "+result.getString(5)+"\n\n");

        }
        //show all data
        showdata("Records",stringBuilder.toString());
    }
     public void showdata(String title,String msg)
     {
         AlertDialog.Builder builder= new AlertDialog.Builder(this);
         builder.setCancelable(true);
         builder.setTitle(title);
         builder.setMessage(msg);
         builder.show();

     }
     private void gettabledata()
    {
        Cursor data=databaseHelper.getdata();
        data.moveToFirst();
        do {
            View tableRow = LayoutInflater.from(this).inflate(R.layout.table_layout_item,null,false);
            TextView srno  = (TextView) tableRow.findViewById(R.id.srno);
            TextView lead  = (TextView) tableRow.findViewById(R.id.lead);
            TextView name  = (TextView) tableRow.findViewById(R.id.name);
            TextView comany  = (TextView) tableRow.findViewById(R.id.company);
            TextView mobile = (TextView) tableRow.findViewById(R.id.mobile);
            TextView address  = (TextView) tableRow.findViewById(R.id.address);


            srno.setText(data.getString(0));
            lead.setText(data.getString(1));
            name.setText(data.getString(2));
            comany.setText(data.getString(3));
            mobile.setText(data.getString(4));
            address.setText(data.getString(5));


            tableLayout.addView(tableRow);

        } while (data.moveToNext());
        data.close();
    }
}

