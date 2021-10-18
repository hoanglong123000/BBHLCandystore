package com.example.bbhlcandystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    FloatingActionButton addbtn;
    CandyDBHelper candyDBHelper;
    ArrayList<String> candy_id, candy_name, candy_place, candy_address, candy_price, candy_date;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview1);
        addbtn = (FloatingActionButton) findViewById(R.id.addcandybtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toaddcandypage = new Intent(MainActivity.this, AddCandyActivity.class);
                startActivity(toaddcandypage);
            }
        });
        candyDBHelper = new CandyDBHelper(MainActivity.this);
        candy_id = new ArrayList<>();
        candy_name = new ArrayList<>();
        candy_place = new ArrayList<>();
        candy_address = new ArrayList<>();
        candy_price = new ArrayList<>();
        candy_date = new ArrayList<>();

        displayData();

        customAdapter = new CustomAdapter(MainActivity.this, candy_id, candy_name, candy_place, candy_address, candy_price, candy_date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    void displayData()
    {
        Cursor cursor = candyDBHelper.readallData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                candy_id.add(cursor.getString(0));
                candy_name.add(cursor.getString(1));
                candy_place.add(cursor.getString(2));
                candy_address.add(cursor.getString(3));
                candy_price.add(cursor.getString(4));
                candy_date.add(cursor.getString(5));
            }
        }
    }



}