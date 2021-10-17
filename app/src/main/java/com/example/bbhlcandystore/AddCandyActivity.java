package com.example.bbhlcandystore;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddCandyActivity extends AppCompatActivity {
    private EditText nameofcandy;
    private EditText madeinplace;
    private EditText priceofcandy;
    private EditText addressofproduceplace;
    private EditText producedate;
    private ImageView candyimage;
    private Button addcandybtn;
    private CandyDBHelper candydb;

    private String namedb;
    private Bitmap candyimagebitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candy);

        nameofcandy = (EditText) findViewById(R.id.TitleofCandy);
        madeinplace = (EditText) findViewById(R.id.PlacetoProduce);
        priceofcandy = (EditText) findViewById(R.id.price);
        addressofproduceplace = (EditText) findViewById(R.id.addressinput);
        producedate = (EditText) findViewById(R.id.dateinput);
        candyimage = (ImageView) findViewById(R.id.imageofcandy);
        addcandybtn = (Button) findViewById(R.id.addcandyproductbtn);



        //Function of add candy to database button.
        addcandybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ghostpepperjellybean);
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] img = byteArray.toByteArray();
                candydb = new CandyDBHelper(AddCandyActivity.this);
                candydb.InsertData(nameofcandy.getText().toString().trim(), madeinplace.getText().toString().trim(), addressofproduceplace.getText().toString().trim(), priceofcandy.getText().toString().trim(), producedate.getText().toString().trim(), img);

            }
        });
    }
}