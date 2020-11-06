package com.example.a2009mysspsb1androidapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class settingacticity extends AppCompatActivity {

    private CircleImageView profileImageView;
    private EditText fullNameEditText, userPhoneEditText, addressEditText, passwordEditText;
    private TextView profileChangeTextBtn, closeTextBtn, saveTextButton;

    private Uri imageUri;
    private String myUrl = "";
    private StorageTask uploadTask;
    private StorageReference storageProfilePictureRef;
    private String checker = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        storageProfilePictureRef = FirebaseStorage.getInstance().getReference().child("Profile Pictures");

        profileImageView = findViewById(R.id.settings_profile_image);
        fullNameEditText = findViewById(R.id.settings_full_name);
        userPhoneEditText = findViewById(R.id.settings_phone_number);
        passwordEditText = findViewById(R.id.settings_password);
        addressEditText = findViewById(R.id.settings_address);
        profileChangeTextBtn = findViewById(R.id.profile_image_change_btn);
        closeTextBtn = findViewById(R.id.close_settings_btn);
        saveTextButton = findViewById(R.id.update_account_settings_btn);

        userInfoDisplay(profileImageView, fullNameEditText, userPhoneEditText, addressEditText ,passwordEditText);

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (checker.equals("clicked"))
                {
                    userInfoSaved();
                }
                else
                {
                    updateOnlyUserInfo();
                }
            }
        });