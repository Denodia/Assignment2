package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationScreen extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" + "(?=.*[a-zA-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{4,}" + "$");
    TextView sign_in_another_account;
    ImageView back_button;
    Button continue_btn;
    EditText et_full_name, et_email, et_password, et_gender, et_date_of_birth, et_user_type, et_occupation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        sign_in_another_account = findViewById(R.id.tv_different_account);
        back_button = findViewById(R.id.iv_back);
        continue_btn = findViewById(R.id.btn_continue);
        et_full_name = findViewById(R.id.et_full_name);
        et_email = findViewById(R.id.et_register_email);
        et_password = findViewById(R.id.et_register_password);
        et_gender = findViewById(R.id.et_gender);
        et_date_of_birth = findViewById(R.id.et_date_of_birth);
        et_user_type = findViewById(R.id.et_user_type);
        et_occupation = findViewById(R.id.et_occupation);


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(RegistrationScreen.this, LoginScreen.class);
                startActivity(intent1);
                finish();
            }
        });

        sign_in_another_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(RegistrationScreen.this, LoginScreen.class);
                startActivity(intent2);
                finish();
            }
        });

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validateUsername() | !validatePassword() | !validateGender() |!validateJavaDate() | !validateoccupation() | !validateusertype()) {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), R.string.wrong_input, Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                    snackbar.setActionTextColor(Color.parseColor("#082c3b"));
                    snackbar.show();
                    return;
                } else {
                    Intent intent7 = new Intent(RegistrationScreen.this, OtpScreen.class);
                    startActivity(intent7);
                    finish();
                }
            }
        });

    }

    private boolean validateEmail() {
        String textinput = et_email.getText().toString().trim();

        if (textinput.isEmpty()) {
            et_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(textinput).matches()) {
            et_email.setError("Please enter valid email address");
            return false;
        } else {
            et_email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = et_password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            et_password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            et_password.setError("Password too weak");
            return false;
        } else {
            et_password.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = et_full_name.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            et_full_name.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            et_full_name.setError("Username too long");
            return false;
        } else if (usernameInput.matches("[0-9]+")) {
            et_full_name.setError("Don't enter number in full name");
            return false;
        } else {
            et_full_name.setError(null);
            return true;
        }
    }

    private boolean validateGender() {
        String user_gender = et_gender.getText().toString().trim();
        String male = "Male";
        String female = "female";
        if (user_gender.equalsIgnoreCase(male) | user_gender.equalsIgnoreCase(female)) {
            return true;
        } else {
            et_gender.setError("Enter right gender name");
            return false;
        }
    }

    private boolean validateJavaDate() {
        String strDate=et_date_of_birth.getText().toString();
        if (strDate.trim().equals("")) {
            et_date_of_birth.setError("Don't give null value.");
            return false;
        } else {
            String regex ="^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$";
            String date=et_date_of_birth.getText().toString();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(date);

            if(matcher.matches()==false){
                et_date_of_birth.setError("Enter valid date format like 01/01/2020");
                return false;
            }
            return matcher.matches();

        }
    }

    private boolean validateusertype() {
        String usertype = et_user_type.getText().toString().trim();

        if (usertype.isEmpty()) {
            et_user_type.setError("Field can't be empty");
            return false;
        } else if (usertype.length() > 10) {
            et_user_type.setError("User-type too long");
            return false;
        } else if (usertype.matches("[0-9]+")) {
            et_user_type.setError("Don't enter number in user-type");
            return false;
        }  else {
            et_user_type.setError(null);
            return true;
        }
    }
    private boolean validateoccupation() {
        String useroccupation = et_occupation.getText().toString().trim();

        if (useroccupation.isEmpty()) {
            et_occupation.setError("Field can't be empty");
            return false;
        } else if (useroccupation.length() > 10) {
            et_occupation.setError("User-type too long");
            return false;
        } else if (useroccupation.matches("[0-9]+")) {
            et_occupation.setError("Don't enter number in user-type");
            return false;
        }
        else {
            et_occupation.setError(null);
            return true;
        }
    }
}

