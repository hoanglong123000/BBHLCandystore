package com.example.bbhlcandystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class loginActivity extends AppCompatActivity {
    private EditText USERNAME;
    private EditText PASSWORD;
    private TextView reglink;
    private Button sibtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        USERNAME = (EditText) findViewById(R.id.loginusernametxt);
        PASSWORD = (EditText) findViewById(R.id.loginpasswordtxt);
        sibtn = (Button) findViewById(R.id.signinbtn);
        reglink = (TextView) findViewById(R.id.registerlink);

        // Function of sign up here link.
        reglink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openregpage();
            }
        });
    }

    // Function of sign up here link.
    public void openregpage()
    {
        Intent openregisterpage = new Intent(this, MainActivity.class);
        startActivity(openregisterpage);
    }

}