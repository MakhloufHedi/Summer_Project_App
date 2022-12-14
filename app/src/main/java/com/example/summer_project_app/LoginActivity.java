package com.example.summer_project_app;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button loginBut ;
    private TextView forg_Pass, signup;
    private FirebaseAuth mAuth;
    private Dialog progressDialog;
    private TextView dialog_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBut = findViewById(R.id.loginBut);
        forg_Pass = findViewById(R.id.forg_pass);
        signup = findViewById(R.id.signup);

        progressDialog = new Dialog(LoginActivity.this);
        progressDialog.setContentView(R.layout.dialogue_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog_text = (TextView) findViewById(R.id.dialog_text);
        dialog_text.setText("Signing in...");

        mAuth = FirebaseAuth.getInstance();

        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateData()) {
                    login();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this , SignUpActivity.class);
                startActivity(intent);
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

        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Toast.makeText(LoginActivity.this, "Login success !", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}

