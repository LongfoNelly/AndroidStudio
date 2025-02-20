package com.example.lab_02;

import static com.example.lab_02.R.id.etPhone;
import static com.example.lab_02.R.id.tvWelcome;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ThirdActivity extends AppCompatActivity {

    private EditText etPhone;
    private ImageView imgProfile;
    private ActivityResultLauncher<Intent> cameraLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Retrieve email from Intent and update UI
        TextView tvWelcome;
        tvWelcome = findViewById(R.id.tvWelcome);
        String email = getIntent().getStringExtra("EmailAddress");
        tvWelcome.setText("Welcome back " + email);

        // Initialize views
        etPhone = findViewById(R.id.etPhone);
        imgProfile = findViewById(R.id.imgProfile);
        Button btnCall = findViewById(R.id.btnCall);
        Button btnChangePicture = findViewById(R.id.btnChangePicture);

        // Setup call button click listener
        btnCall.setOnClickListener(v -> makePhoneCall());

        // Register camera launcher
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                Bitmap photo = (Bitmap) data.getExtras().get("data");
                                imgProfile.setImageBitmap(photo);
                            }
                        }
                    }
                });

        // Setup change picture button click listener
        btnChangePicture.setOnClickListener(v -> openCamera());
    }

    // Method to initiate phone call
    private void makePhoneCall() {
        String phoneNumber = etPhone.getText().toString();
        if (!phoneNumber.isEmpty()) {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        }
    }

    // Method to open the camera
    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraLauncher.launch(cameraIntent);
    }
}
