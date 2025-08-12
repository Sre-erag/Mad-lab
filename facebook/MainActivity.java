package com.example.facebook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Make sure this matches your XML filename

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        forgotPassword = findViewById(R.id.forgotPassword);

        // Handle login button click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    // Normally authenticate here
                    Toast.makeText(MainActivity.this, "Logging in...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle forgot password click
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Forgot Password clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
