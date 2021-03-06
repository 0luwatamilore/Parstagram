package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            goMainActivty();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            Log.i(TAG,"onClick Login Button");
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            loginUser(username,password);
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginUser(String username, String password){
        Log.i(TAG, "Attempting to login User " + username);

        ParseUser.logInInBackground(username, password, (user, e) -> {
            if (e != null){
                Log.e(TAG,"Issue with login", e);
                Toast.makeText(LoginActivity.this,"Issue with Login",Toast.LENGTH_SHORT).show();
                return;
            }
            goMainActivty();
            Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
        });
    }

    private void goMainActivty() {
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}