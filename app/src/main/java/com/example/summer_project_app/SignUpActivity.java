package com.example.summer_project_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private EditText username, email_id, password_id, confirm_pass;
    private Button signupBut;
    private ImageView return_back;
    private FirebaseAuth mAuth;
    private String usernameStr,email_idStr,password_idStr,confirm_passStr;
    private Dialog progressDialog;
    private TextView dialog_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.username);
        email_id = (EditText) findViewById(R.id.email_id);
        password_id = (EditText) findViewById(R.id.password_id);
        confirm_pass = (EditText) findViewById(R.id.confirm_pass);
        signupBut = (Button) findViewById(R.id.signupBut);
        return_back = (ImageView) findViewById(R.id.return_back);

        progressDialog = new Dialog(SignUpActivity.this);
        progressDialog.setContentView(R.layout.dialogue_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog_text = (TextView) findViewById(R.id.dialog_text);
        dialog_text.setText("Registering user...");

        mAuth = FirebaseAuth.getInstance();

        return_back.setOnClickListener((view) -> {
                finish();
        });

        signupBut.setOnClickListener((view) -> {
                if(validate()){
                    signupNewUser();
                }
        });
    }

    private boolean validate(){
        usernameStr = username.getText().toString().trim();
        email_idStr = email_id.getText().toString().trim();
        password_idStr = password_id.getText().toString().trim();
        confirm_passStr = confirm_pass.getText().toString().trim();

        if (usernameStr.isEmpty()){
            username.setError("Enter your username");
            return false;
        }
        if (email_idStr.isEmpty()){
            email_id.setError("Enter your Email");
            return false;
        }
        if (confirm_passStr.isEmpty()){
            confirm_pass.setError("Confirm your password");
            return false;
        }
        if (password_idStr.isEmpty()){
            password_id.setError("Enter your password");
            return false;
        }
        if (confirm_passStr.compareTo(password_idStr) != 0){
            Toast.makeText(SignUpActivity.this, "Password and confirm password should be the same", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void signupNewUser(){
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email_idStr, password_idStr)
                .addOnCompleteListener(this, (task) -> {
                        if (task.isSuccessful()) {

                            Toast.makeText(SignUpActivity.this, "Sign up successful !", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                            startActivity(intent);
                            SignUpActivity.this.finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                });
    }
}