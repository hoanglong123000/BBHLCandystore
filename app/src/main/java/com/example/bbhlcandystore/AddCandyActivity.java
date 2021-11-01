package com.example.bbhlcandystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCandyActivity extends AppCompatActivity {
    private EditText nameofcandy;
    private EditText madeinplace;
    private EditText priceofcandy;
    private EditText addressofproduceplace;
    private EditText producedate;

    private Button addcandybtn;

    private CandyDBHelper candydb;
    private int REQUEST_CODE_GALLERY = 999;

    private String namedb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candy);

        nameofcandy = (EditText) findViewById(R.id.TitleofCandy);
        madeinplace = (EditText) findViewById(R.id.PlacetoProduce);
        priceofcandy = (EditText) findViewById(R.id.price);
        addressofproduceplace = (EditText) findViewById(R.id.addressinput);
        producedate = (EditText) findViewById(R.id.dateinput);
        addcandybtn = (Button) findViewById(R.id.insertcandytodatabasebtn);



        //Function of add candy to database button.
        addcandybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                candydb = new CandyDBHelper(AddCandyActivity.this);
                candydb.InsertData(nameofcandy.getText().toString(), madeinplace.getText().toString(), addressofproduceplace.getText().toString(), priceofcandy.getText().toString().trim(), producedate.getText().toString().trim());
                Intent intent = new Intent(AddCandyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}