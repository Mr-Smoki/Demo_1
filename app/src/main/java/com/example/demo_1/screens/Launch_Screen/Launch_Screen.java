package com.example.demo_1.screens.Launch_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.demo_1.R;
import com.example.demo_1.screens.SignIn_Screen.SignIn_Screen;
import com.example.demo_1.screens.SignUp_Screen.SignUp_Screen;

public class Launch_Screen extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        SharedPreferences sharedPreferences = getSharedPreferences("o", Context.MODE_PRIVATE);

        boolean firstLoaded = sharedPreferences.getBoolean("fl",true);


        //если первая загрузка
        if(firstLoaded)
        {
            //-> первая загрузка - false
            SharedPreferences.Editor e = sharedPreferences.edit();
            e.putBoolean("fl", false);
            e.apply();
            //->SignUpScreen
            intent = new Intent(Launch_Screen.this, SignUp_Screen.class);
        }
        else {
            //->SignInScreen
        intent = new Intent(Launch_Screen.this, SignIn_Screen.class);
        }

        new Handler().postAtTime(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },2000);
    }
}