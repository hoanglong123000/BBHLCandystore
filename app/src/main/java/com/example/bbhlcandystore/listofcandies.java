package com.example.bbhlcandystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class listofcandies extends AppCompatActivity {
    private RecyclerView recyclerView;
    CandyDBHelper candyDBHelper;
    ArrayList<String> candy_id, candy_name, candy_place, candy_address, candy_price, candy_date;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofcandies);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview2);
        candyDBHelper = new CandyDBHelper(listofcandies.this);
        candy_id = new ArrayList<>();
        candy_name = new ArrayList<>();
        candy_place = new ArrayList<>();
        candy_address = new ArrayList<>();
        candy_price = new ArrayList<>();
        candy_date = new ArrayList<>();
        displayData();
        customAdapter = new CustomAdapter(listofcandies.this, this, candy_id, candy_name, candy_place, candy_address, candy_price, candy_date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(listofcandies.this));



    }

    void displayData()
    {
        Cursor cursor = candyDBHelper.readallData();
        if(cursor.getCount() == 0)
        {

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