package com.example.cropcrew_capstoneproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cropcrew_capstoneproject.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    ActivitySignUpBinding signUpBinding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = signUpBinding.getRoot();
        setContentView(view);
        firebaseAuth = FirebaseAuth.getInstance();
        init();
    }
    public void init(){
        signUpBinding.btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(validate()){

        }


    }
    public Boolean validate(){
        if (signUpBinding.firstNameEdtTxt.getText().toString().isEmpty()) {
            signUpBinding.firstNameEdtTxt.setError("First Name is required");
            return false;
        }
        if (signUpBinding.lastNameEdtTxt.getText().toString().isEmpty()) {
            signUpBinding.lastNameEdtTxt.setError("Last Name is required");
            return false;
        }
        if (signUpBinding.phoneNumberEdtTxt.getText().toString().isEmpty() || !signUpBinding.phoneNumberEdtTxt.getText().toString().matches("\\d{10}")) {
            signUpBinding.phoneNumberEdtTxt.setError("Phone Number is required or invalid phone number (XXXXXXXXXX)");
            return false;
        }


        if (signUpBinding.emailEdtTxt.getText().toString().isEmpty()) {
            signUpBinding.emailEdtTxt.setError("Email is required");
            return false;
        }

        if (signUpBinding.passwordEdtTxt.getText().toString().isEmpty()) {
            signUpBinding.passwordEdtTxt.setError("Password is required");
            return false;
        }
        if (signUpBinding.confirmPasswordEdtTxt.getText().toString().isEmpty()) {
            signUpBinding.confirmPasswordEdtTxt.setError("Please confirm your password");
            return false;
        }

        if (!signUpBinding.passwordEdtTxt.toString().equals(signUpBinding.confirmPasswordEdtTxt.toString())) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}