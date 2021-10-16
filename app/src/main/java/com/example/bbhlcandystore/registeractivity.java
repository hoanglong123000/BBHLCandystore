package com.example.bbhlcandystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registeractivity extends AppCompatActivity {
    private Button sigupbtn;
    private Button returnloginpage;
    private DBHelper db;
    private EditText regusername;
    private EditText regpassword;
    private EditText confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);
        sigupbtn = (Button) findViewById(R.id.signupbtn);
        returnloginpage = (Button) findViewById(R.id.backtologinpagebtn);
        regusername = (EditText) findViewById(R.id.registerusernametxt);
        regpassword = (EditText) findViewById(R.id.registerpasswordtxt);
        confirmpassword = (EditText) findViewById(R.id.confirmregpasstxt);
        db = new DBHelper(this);


        // Function of sign up button.
        sigupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reguser = regusername.getText().toString();
                String regpass = regpassword.getText().toString();
                String confirmregpass = confirmpassword.getText().toString();

                // Check whether on the user side already fills all information or not.
                if (reguser.equals("") || regpass.equals("") || confirmregpass.equals("")) {
                    Toast.makeText(MainActivity.this, "Kiểm tra lại thông tin và điền lại nếu có sự cố", Toast.LENGTH_SHORT).show();
                }
                // If the client side already fills all information then transfer information from UI to logindatabase database.
                else {
                    if (regpass.equals(confirmregpass)) {
                        // Check whether the user side already fills information in logindatabase database or not. In order to avoid duplicated information in logindatabase database.
                        Boolean checkuser = db.checkusername(reguser);
                        // If user side's information didnt exist in logindatabase database then insert information from UI to logindatabase database.
                        if (checkuser == false) {
                            Boolean insertinfotodatabase = db.insertData(reguser, regpass);
                            // If there are no conflict or errors in logindatabase database then create a broadcast let user side knows result.
                            if (insertinfotodatabase == true) {
                                Toast.makeText(MainActivity.this, "Bạn đã đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                regbtn();
                            }
                            // If there are conflicts or errors in logindatabase database.
                            else {
                                Toast.makeText(MainActivity.this, "Xin lỗi vì hệ thống gặp sự cố xin bạn hãy bỏ ra thời gian nhập lại thông tin ! ", Toast.LENGTH_SHORT).show();
                            }

                        }
                        //If user side's information already exist in logindatabase database.
                        else {
                            Toast.makeText(MainActivity.this, "Bạn có sẵn thông tin trong hệ thống rồi! Xin hãy cho biết tên của bạn", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Bạn nhập sai mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        // Function of return button.
        returnloginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rebtn();
            }
        });

    }


    // Function of sign up button.
    public void regbtn()
    {
        Intent reintent = new Intent(getApplicationContext(), loginActivity.class);
        startActivity(reintent);
    }

    // Function of return button.
    public void rebtn()
    {
        Intent returnintent = new Intent(getApplicationContext(), loginActivity.class);
        startActivity(returnintent);
    }
    }
}