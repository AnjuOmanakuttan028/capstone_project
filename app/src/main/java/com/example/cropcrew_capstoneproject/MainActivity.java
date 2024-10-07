package com.example.cropcrew_capstoneproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cropcrew_capstoneproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityMainBinding mainBinding;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        init();
    }
    public void init(){
        mainBinding.btnSignUp.setOnClickListener(this);
        mainBinding.btnLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==mainBinding.btnLogin.getId()){
            intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
        else if(v.getId()==mainBinding.btnSignUp.getId()){
            intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        }


    }
}