package com.example.lab_02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView tvUserEmail;
    private Button btnBack;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        Log.d("Lifecycle", "onCreate called");

        // Initialize UI elements
        tvUserEmail = findViewById(R.id.tvEmail);
        btnBack = this.<Button>findViewById(R.id.etEmail);

        // Retrieve the email from the Intent
        Intent intent = getIntent();
        if (intent.hasExtra("USER_EMAIL")) {
            String email = intent.getStringExtra("USER_EMAIL");
            tvUserEmail.setText("Welcome, " + email);
        }

        // Button to go back to MainActivity
        btnBack.setOnClickListener(v -> {
            Intent backIntent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(backIntent);
            finish(); // Finish current activity to prevent stacking
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "onDestroy called");
    }
}
