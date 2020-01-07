package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class LoginScreen extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView textView_register;
    EditText email,password;
    ImageView passwordvisible;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        textView_register=findViewById(R.id.tv_bt_register);
        login=findViewById(R.id.bt_login);
        email=findViewById(R.id.et_email);
        password=findViewById(R.id.et_password);
        passwordvisible=findViewById(R.id.iv_password);

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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }

    public void ShowHidePass(View view){

        if(view.getId()==R.id.iv_password){

            if(password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_show_pass);

                //Show Password
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_hide_pass);

                //Hide Password
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
}