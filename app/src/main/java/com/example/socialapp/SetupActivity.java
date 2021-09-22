package com.example.socialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {

    private EditText UserName, City, Age;
    private Button SaveProfileButton;
    private CircleImageView ProfileImage;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        UserName = (EditText) findViewById(R.id.setup_username);
        City = (EditText) findViewById(R.id.setup_city);
        Age = (EditText) findViewById(R.id.setup_age);
        SaveProfileButton = (Button) findViewById(R.id.setup_create_pofile_button);
        ProfileImage = (CircleImageView) findViewById(R.id.setup_profile_image);


    }
}