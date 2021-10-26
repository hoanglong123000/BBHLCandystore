package com.example.bbhlcandystore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    FloatingActionButton addbtn;
    CandyDBHelper candyDBHelper;
    ArrayList<String> candy_id, candy_name, candy_place, candy_address, candy_price, candy_date;
    CustomAdapter customAdapter;
    private ImageView nocandyimg;
    private TextView nocandytxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview1);
        addbtn = (FloatingActionButton) findViewById(R.id.addcandybtn);
        nocandyimg = (ImageView) findViewById(R.id.nocandyimage);
        nocandytxt = (TextView) findViewById(R.id.Nocandydata);
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

        customAdapter = new CustomAdapter(MainActivity.this, this, candy_id, candy_name, candy_place, candy_address, candy_price, candy_date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            recreate();
        }
    }

    void displayData()
    {
        Cursor cursor = candyDBHelper.readallData();
        if(cursor.getCount() == 0)
        {
            nocandyimg.setVisibility(View.VISIBLE);
            nocandytxt.setVisibility(View.VISIBLE);
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
            nocandyimg.setVisibility(View.GONE);
            nocandytxt.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.candy_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all)
        {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa tất cả số kẹo này" + " ?");
        builder.setMessage("Bạn có muốn xóa hết kẹo này không ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                CandyDBHelper db = new CandyDBHelper(MainActivity.this);
                db.DeleteAllItems();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }

}