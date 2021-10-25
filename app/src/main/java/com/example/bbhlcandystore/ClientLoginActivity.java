package com.example.bbhlcandystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ClientLoginActivity extends AppCompatActivity {
    private EditText USERNAME;
    private EditText PASSWORD;
    private TextView reglink;
    private Button sibtn;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login);

        // Variables's Initialization.
        USERNAME = (EditText) findViewById(R.id.loginadminusernametxt);
        PASSWORD = (EditText) findViewById(R.id.loginadminpasswordtxt);
        sibtn = (Button) findViewById(R.id.signinbtn);
        reglink = (TextView) findViewById(R.id.registerlink);
        db = new DBHelper(this);

        // Function of sign up here link.
        reglink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openregpage();
            }
        });

        //Function of Sign in button.
        sibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginusername = USERNAME.getText().toString();
                String loginpassword = PASSWORD.getText().toString();

                // Check whether on the user side already fills all information or not.
                if (loginusername.equals("") || loginpassword.equals(""))
                {
                    Toast.makeText(ClientLoginActivity.this, "Kiểm tra lại thông tin và điền lại nếu có sự cố", Toast.LENGTH_SHORT).show();
                }

                // If the client side already fills all information then transfer information from UI to logindatabase database.
                else
                {
                    Boolean checkuser = db.checkusernamepassword(loginusername, loginpassword);
                    // If user side's information didnt exist in logindatabase database then insert information from UI to logindatabase database.
                    if (checkuser == true) {
                        Toast.makeText(ClientLoginActivity.this, "Bạn đã đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        openmainpage();

                    }
                    else {
                        // If there are conflicts or errors in logindatabase database.
                        Toast.makeText(ClientLoginActivity.this, "Xin lỗi vì hệ thống gặp sự cố xin bạn hãy bỏ ra thời gian nhập lại thông tin ! ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    // Function of sign up here link.
    public void openregpage()
    {
        Intent openregisterpage = new Intent(this, registeractivity.class);

        startActivity(openregisterpage);
    }

    // Function of sign in button.
    public void openmainpage()
    {
        Intent openmainpage = new Intent(getApplicationContext(), listofcandies.class);
        startActivity(openmainpage);
    }

}