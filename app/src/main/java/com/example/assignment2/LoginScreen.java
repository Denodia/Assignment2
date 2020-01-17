package com.example.assignment2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class LoginScreen extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView textView_register;
    EditText email,password;
    ImageView password_visible;
    Boolean flag=true;
    Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        textView_register=findViewById(R.id.tv_bt_register);
        login=findViewById(R.id.bt_login);
        email=findViewById(R.id.et_email);
        password=findViewById(R.id.et_password);
        password_visible= findViewById(R.id.iv_password);

        String text="Dont have an account?REGISTER";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent i=new Intent(LoginScreen.this,RegistrationScreen.class);
                startActivity(i);
                finish();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#082c3b"));
                ds.setUnderlineText(false);
            }
        };



        ss.setSpan(clickableSpan1, 21, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        textView_register.setText(ss);
        textView_register.setMovementMethod(LinkMovementMethod.getInstance());

        password.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                   password.clearFocus();
                   login.requestFocus();
                   loginAction();
                  return true;
                }
                return false;
            }
        });

        password_visible.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if(flag==true){
                    password_visible.setBackground(getResources().getDrawable(R.drawable.ic_visible_pass));

                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    int position = password.length();
                    Editable etext = password.getText();
                    Selection.setSelection(etext, position);
                    flag=false;
                }
                else{

                    password_visible.setBackground(getResources().getDrawable(R.drawable.ic_hide_pass));

                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    int position = password.length();
                    Editable etext = password.getText();
                    Selection.setSelection(etext, position);
                    flag=true;

                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loginAction();
            }
        });
    }

    public void loginAction(){

        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), R.string.username_password_error_msg, Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(Color.parseColor("#082c3b"));
            snackbar.show();
        } else {
            if (email.getText().toString().trim().matches(emailPattern)) {
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), R.string.logged_in_msg, Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.setActionTextColor(Color.parseColor("#082c3b"));
                snackbar.show();
            } else {
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), R.string.invalid_email, Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.setActionTextColor(Color.parseColor("#082c3b"));
                snackbar.show();
            }
        }
    }

}
