package com.example.demo_1.screens.SignIn_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.demo_1.common.CheckData;
import com.example.demo_1.databinding.ActivitySignInScreenBinding;
import com.example.demo_1.screens.SignUp_Screen.SignUp_Screen;

public class SignIn_Screen extends AppCompatActivity {
    ActivitySignInScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void SignIn(View view){
        if(!binding.emailET.getText().toString().equals("")&&!binding.passET.getText().toString().equals("")){
            if(CheckData.checkMail(binding.emailET.getText().toString()))
            {
                CheckData.authConfirmed(SignIn_Screen.this, binding.emailET.getText().toString(),binding.passET.getText().toString());
            }
            else{CheckData.makeMessage("Некорректный email",this);}
        }
        else{CheckData.makeMessage("Пустые поля",this);}

    }
    public void GoSignUp(View view){
        Intent signUp = new Intent(SignIn_Screen.this, SignUp_Screen.class);
        startActivity(signUp);
        finish();

    }
}