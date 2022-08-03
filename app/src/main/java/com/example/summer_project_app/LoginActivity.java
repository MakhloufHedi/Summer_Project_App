package com.example.summer_project_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button loginBut ;
    private TextView forg_Pass, signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBut = findViewById(R.id.loginBut);
        forg_Pass = findViewById(R.id.forg_pass);
        signup = findViewById(R.id.signup);

        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    login();
                }
            }
        });

    }
    private boolean validateData(){

        if (email.getText().toString().isEmpty()){
            email.setError(("Enter Email ID"));
            return  false;
        }
        if (password.getText().toString().isEmpty()){
            password.setError(("Enter Password"));
            return  false;
        }

        return true;
    }

    private void login(){

    }


}

