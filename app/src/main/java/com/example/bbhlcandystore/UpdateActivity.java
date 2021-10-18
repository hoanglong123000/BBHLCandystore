package com.example.bbhlcandystore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private EditText nameofcandy;
    private EditText madeinplace;
    private EditText priceofcandy;
    private EditText addressofproduceplace;
    private EditText producedate;
    private Button updatecandybtn;
    private Button deletecandybutton;

    private String id, name, place, address, price, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameofcandy = (EditText) findViewById(R.id.UpdateTitleofCandy);
        madeinplace = (EditText) findViewById(R.id.UpdateplaceofProduct);
        priceofcandy = (EditText) findViewById(R.id.Updateprice);
        addressofproduceplace = (EditText) findViewById(R.id.Updateaddressinput);
        producedate = (EditText) findViewById(R.id.updatedateinput);
        updatecandybtn = (Button) findViewById(R.id.updatecandyproductbtn);
        deletecandybutton = (Button) findViewById(R.id.deletecandybtn);

        updatecandybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        getIntentData();
    }


    void getIntentData()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("place") && getIntent().hasExtra("address") && getIntent().hasExtra("price") && getIntent().hasExtra("date"))
        {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            place = getIntent().getStringExtra("place");
            address = getIntent().getStringExtra("address");
            price = getIntent().getStringExtra("price");
            date = getIntent().getStringExtra("date");

            nameofcandy.setText(name);
            madeinplace.setText(place);
            addressofproduceplace.setText(address);
            priceofcandy.setText(price);
            producedate.setText(date);

        }
        else
        {
            Toast.makeText(this, "Lỗi dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }
}