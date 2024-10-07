package com.example.cropcrew_capstoneproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cropcrew_capstoneproject.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    ActivitySignUpBinding signUpBinding;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = signUpBinding.getRoot();
        setContentView(view);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        init();
    }
    public void init(){
        signUpBinding.btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email = signUpBinding.emailEdtTxt.getText().toString().trim();
        String password = signUpBinding.passwordEdtTxt.getText().toString().trim();
        if(validate()){
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            saveUserInfo();
                        } else {
                            Toast.makeText(SignUp.this, "Registration failed: " , Toast.LENGTH_SHORT).show();
                        }
                    });

        }


    }
    public void saveUserInfo() {
        String userId = firebaseAuth.getCurrentUser().getUid();
        String firstName = signUpBinding.firstNameEdtTxt.getText().toString().trim();
        String lastName = signUpBinding.lastNameEdtTxt.getText().toString().trim();
        String phoneNumber = signUpBinding.phoneNumberEdtTxt.getText().toString().trim();
        String email = signUpBinding.emailEdtTxt.getText().toString().trim();
        String password = signUpBinding.passwordEdtTxt.getText().toString().trim();

        User user = new User(firstName, lastName, phoneNumber, email, password);

        firestore.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(SignUp.this, "User information saved", Toast.LENGTH_SHORT).show();

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(SignUp.this, "Failed to save user information: " , Toast.LENGTH_SHORT).show();
                });
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
            signUpBinding.passwordEdtTxt.setError("Password is required and should be 6 characters long");
            return false;
        }
        if (signUpBinding.confirmPasswordEdtTxt.getText().toString().isEmpty()) {
            signUpBinding.confirmPasswordEdtTxt.setError("Please confirm your password");
            return false;
        }

        if (!signUpBinding.passwordEdtTxt.getText().toString().equals(signUpBinding.confirmPasswordEdtTxt.getText().toString())) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}