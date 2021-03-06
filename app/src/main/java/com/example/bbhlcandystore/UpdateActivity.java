package com.example.bbhlcandystore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private EditText nameofcandy;
    private EditText madeinplace;
    private EditText priceofcandy;
    private EditText addressofproduceplace;
    private EditText producedate;
    private Button updatecandybtn;
    private Button deletecandybutton;


    protected String id, name, place, address, price, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameofcandy = (EditText) findViewById(R.id.UpdateTitleofCandy);
        madeinplace = (EditText) findViewById(R.id.UpdateplaceofProduct);
        priceofcandy = (EditText) findViewById(R.id.Updateprice);
        addressofproduceplace = (EditText) findViewById(R.id.Updateaddressinput);
        producedate = (EditText) findViewById(R.id.updatedateinput);
        updatecandybtn = (Button) findViewById(R.id.updatecandybtn);
        deletecandybutton = (Button) findViewById(R.id.deletecandybtn);

        getandsetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null)
        {
            ab.setTitle(name);
        }


        updatecandybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CandyDBHelper db = new CandyDBHelper(UpdateActivity.this);
                name = nameofcandy.getText().toString();
                place = madeinplace.getText().toString();
                address = addressofproduceplace.getText().toString();
                price = priceofcandy.getText().toString();
                date = producedate.getText().toString();
                db.UpdateData(id, name, place, address, price, date);
            }
        });
        deletecandybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });


    }


    void getandsetIntentData()
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
            Toast.makeText(this, "L???i d??? li???u", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("X??a " + name);
        builder.setMessage("B???n c?? mu???n x??a " + name + " kh??ng?");
        builder.setPositiveButton("C??", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CandyDBHelper db = new CandyDBHelper(UpdateActivity.this);
                db.DeleteOneItem(id);
                finish();
            }
        });
        builder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }

}