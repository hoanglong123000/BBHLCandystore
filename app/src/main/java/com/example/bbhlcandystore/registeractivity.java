package com.example.bbhlcandystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class registeractivity extends AppCompatActivity {
    private Button sigupbtn;
    private DBHelper db;
    private EditText regusername;
    private EditText regpassword;
    private EditText confirmpassword;
    private RadioGroup switchsides;
    private RadioButton adminside;
    private RadioButton clientside;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);

        //Variables Initialization.
        sigupbtn = (Button) findViewById(R.id.signupbtn);
        regusername = (EditText) findViewById(R.id.registerusernametxt);
        regpassword = (EditText) findViewById(R.id.registerpasswordtxt);
        confirmpassword = (EditText) findViewById(R.id.confirmregpasstxt);
        switchsides = (RadioGroup) findViewById(R.id.chckboxgroup);
        adminside = (RadioButton) findViewById(R.id.Adminchckbox);
        clientside = (RadioButton) findViewById(R.id.clientchckbox);
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
                    Toast.makeText(registeractivity.this, "Kiểm tra lại thông tin và điền lại nếu có sự cố", Toast.LENGTH_SHORT).show();
                }
                // If the client side already fills all information then transfer information from UI to logindatabase database.
                else {
                    if (regpass.equals(confirmregpass)) {
                        // Check whether the user side already fills information in logindatabase database or not. In order to avoid duplicated information in logindatabase database.
                        Boolean checkuser = db.checkusername(reguser);
                        // If user side's information didnt exist in logindatabase database then insert information from UI to logindatabase database.
                        if (checkuser == false) {
                            Boolean insertinfotodatabase = db.insertData(reguser, regpass, adminside.isChecked()?true:false);
                            // If there are no conflict or errors in logindatabase database then create a broadcast let user side knows result.
                            if (insertinfotodatabase == true) {
                                Toast.makeText(registeractivity.this, "Bạn đã đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                regbtn();
                            }
                            // If there are conflicts or errors in logindatabase database.
                            else {
                                Toast.makeText(registeractivity.this, "Xin lỗi vì hệ thống gặp sự cố xin bạn hãy bỏ ra thời gian nhập lại thông tin ! ", Toast.LENGTH_SHORT).show();
                            }

                        }
                        //If user side's information already exist in logindatabase database.
                        else {
                            Toast.makeText(registeractivity.this, "Bạn có sẵn thông tin trong hệ thống rồi! Xin hãy cho biết tên của bạn", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(registeractivity.this, "Bạn nhập sai mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



    }


    // Function of sign up button.
    public void regbtn()
    {
        Intent adminintent = new Intent(getApplicationContext(), loginActivity.class);
        Intent clientintent = new Intent(getApplicationContext(), ClientLoginActivity.class);

        if(adminside.isChecked() == true)
        {
            startActivity(adminintent);
        }
        else if(clientside.isChecked() == true)
        {
            startActivity(clientintent);
        }

    }


}

