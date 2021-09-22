package com.example.socialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabItem;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {


    private EditText UserEmail, Password, ConfirmPassword;
    private Button CreateAccountButton;
    private ProgressDialog loadingBar;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        UserEmail = (EditText) findViewById(R.id.register_email);
        Password = (EditText) findViewById(R.id.register_password);
        ConfirmPassword = (EditText) findViewById(R.id.register_password2);
        CreateAccountButton = (Button) findViewById(R.id.register_button);
        loadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });
}

    private void CreateNewAccount() {
        String email = UserEmail.getText().toString();
        String password = Password.getText().toString();
        String confirmPassword = ConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter your Email", Toast.LENGTH_SHORT).show();
        }
       else if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Enter your password", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(this,"Enter your password again", Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(confirmPassword)){
            Toast.makeText(this,"Your passwords do not match",Toast.LENGTH_LONG).show();
        }
        else {
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please wait while we are creating your account ");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);


            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                SendUserToSetupActivity();
                                Toast.makeText(RegisterActivity.this,"you have created an account", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else {
                                String message = task.getException().getMessage();
                                Toast.makeText(RegisterActivity.this, "Error occured: "+ message,Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }




    }

    private void SendUserToSetupActivity() {
        Intent setupIntent = new Intent(RegisterActivity.this, SetupActivity.class);
        setupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(setupIntent);
        finish();
    }


}