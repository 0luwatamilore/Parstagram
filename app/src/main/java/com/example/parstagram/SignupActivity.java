package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etpassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = findViewById(R.id.etName);
        etpassword = findViewById(R.id.etpassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String password = etpassword.getText().toString();
            // Configure Query
            ParseUser user = new ParseUser();
            // Store an object
            user.setPassword(password);
            user.setUsername(name);
//              // Saving object
            user.signUpInBackground(e -> {
                if (e == null) {
                    System.out.println(e);
                    Intent i = new Intent(SignupActivity.this,MainActivity.class);
                    startActivity(i);
                }
            });

        });
    }
}