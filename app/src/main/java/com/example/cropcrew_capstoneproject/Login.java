package com.example.cropcrew_capstoneproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cropcrew_capstoneproject.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{

    ActivityLoginBinding loginBinding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = loginBinding.getRoot();
        setContentView(view);
        firebaseAuth = FirebaseAuth.getInstance();
        init();
    }
    public void init(){
        loginBinding.btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == loginBinding.btnLogin.getId()){
            String email = loginBinding.usernameEdtTxt.getText().toString().trim();
            String password = loginBinding.passwordEdtTxt.getText().toString().trim();

            if (email.isEmpty()) {
                loginBinding.usernameEdtTxt.setError("Email is required");
                return;
            }
            if (password.isEmpty()) {
                loginBinding.passwordEdtTxt.setError("Password is required");
                return;
            }
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login.this, "Login failed: " , Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}