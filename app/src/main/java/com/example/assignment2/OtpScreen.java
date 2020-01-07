package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class OtpScreen extends AppCompatActivity {
    ImageView backbutton;
    Button resend,submit;
    EditText et_num1,et_num2,et_num3,et_num4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);
        backbutton=findViewById(R.id.otp_iv_back);
        resend=findViewById(R.id.otp_btn_resend);
        submit=findViewById(R.id.otp_btn_submit);
        et_num1=findViewById(R.id.et_num1);
        et_num2=findViewById(R.id.et_num2);
        et_num3=findViewById(R.id.et_num3);
        et_num4=findViewById(R.id.et_num4);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(OtpScreen.this,RegistrationScreen.class);
                startActivity(intent3);
                finish();
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar1 = Snackbar
                        .make(findViewById(android.R.id.content), "Otp is sent again", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar1.setActionTextColor(Color.parseColor("#082c3b"));
                snackbar1.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_num1.getText().toString().length()==1 && et_num2.getText().toString().length()==1 && et_num3.getText().toString().length()==1 &&
                        et_num4.getText().toString().length()==1) {
                    Intent intent5=new Intent(OtpScreen.this,LoginScreen.class);
                    startActivity(intent5);
                    finish();
                }

                else{
                    Snackbar snackbar2 = Snackbar
                            .make(findViewById(android.R.id.content), "Wrong OTP", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                    snackbar2.setActionTextColor(Color.parseColor("#082c3b"));
                    snackbar2.show();
                }
            }
        });

        et_num1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_num1.getText().toString().trim().length() > 0) {
                    et_num1.clearFocus();
                    et_num2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_num2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_num2.getText().toString().trim().length() > 0) {

                    et_num2.clearFocus();
                    et_num3.requestFocus();
                    et_num3.setCursorVisible(true);
                }
            }
        });

        et_num3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_num3.getText().toString().trim().length() > 0) {

                    et_num3.clearFocus();
                    et_num4.requestFocus();
                    et_num4.setCursorVisible(true);
                }
            }
        });
    }
}
