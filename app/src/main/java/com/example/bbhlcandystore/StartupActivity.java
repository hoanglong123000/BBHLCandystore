package com.example.bbhlcandystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class StartupActivity extends AppCompatActivity {

    private TextView signuplnk;
    private Button signinbtn;
    private RadioGroup switchsides;
    private RadioButton adminside;
    private RadioButton clientside;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        signuplnk = (TextView) findViewById(R.id.signuplink1);
        signinbtn = (Button) findViewById(R.id.signinbysidesbtn);
        adminside = (RadioButton) findViewById(R.id.adminchckbox1);
        clientside = (RadioButton) findViewById(R.id.clientchckbox1);

        // Sign up link.
        signuplnk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signuplinkf();
            }
        });

        // Sign in between sides button.
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinbtwnsidesbtn();
            }
        });
    }


    // Link to sign up in pages.
    public void signuplinkf()
    {
        Intent switchtosignuppage = new Intent(this, registeractivity.class);
        startActivity(switchtosignuppage);
    }

    // Sign in button.
    public void signinbtwnsidesbtn()
    {
        // Cases to switch between login pages.
        if(adminside.isChecked())
        {
            Intent switchtosigninadminpage = new Intent(this, loginActivity.class);
            startActivity(switchtosigninadminpage);
        }
        else
        {
            Intent switchtosigninclientpage = new Intent(this, ClientLoginActivity.class);
            startActivity(switchtosigninclientpage);
        }
    }

}