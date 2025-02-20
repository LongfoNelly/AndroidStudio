package com.example.lab_02;

import static com.example.lab_02.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private ImageView imgView;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch sw = findViewById(id.spin_switch);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w("MainActivity", "In onCreate() - Loading Widgets");

        // Initialize UI elements
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        ///
        Button btnOpenActivity = findViewById(R.id.btnOpenActivity);
        imgView = findViewById(R.id.imageView2);

        // Login button click listener
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("USER_EMAIL", email);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Please enter both fields", Toast.LENGTH_SHORT).show();
            }
        });

        // Open second activity button
        btnOpenActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
        btnLogin.setOnClickListener(v -> {
            Intent nextPage = new Intent(MainActivity.this, ThirdActivity.class);
            String email = etEmail.getText().toString();
            nextPage.putExtra("EmailAddress", email);
            startActivity(nextPage);
        });
        // Switch for image rotation
        sw.setOnCheckedChangeListener((btn, isChecked) -> {
            if (isChecked) {
                RotateAnimation rotate = new RotateAnimation(
                        0, 360,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f
                );
                rotate.setDuration(5000);
                rotate.setRepeatCount(Animation.INFINITE);
                rotate.setInterpolator(new LinearInterpolator());
                imgView.startAnimation(rotate);
            } else {
                imgView.clearAnimation(); // Stop animation when switch is off
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MainActivity", "In onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("MainActivity", "In onDestroy()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w("MainActivity", "In onStart()");
    }
}
