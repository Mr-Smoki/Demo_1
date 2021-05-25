package com.example.demo_1.screens.SignUp_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.request.RequestListener;
import com.example.demo_1.R;
import com.example.demo_1.common.AppData;
import com.example.demo_1.common.CheckData;
import com.example.demo_1.common.URLs;
import com.example.demo_1.databinding.ActivitySignUpScreenBinding;
import com.example.demo_1.screens.SignIn_Screen.SignIn_Screen;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class SignUp_Screen extends AppCompatActivity {
    ActivitySignUpScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void SignUp(View view){
        if(!binding.emailET.getText().toString().equals("")&&
                !binding.surnameET.getText().toString().equals("")&&
                !binding.reppassET.getText().toString().equals("")&&
                !binding.passET.getText().toString().equals("")&&
                !binding.nameET.getText().toString().equals(""))
        {
            if(CheckData.checkMail(binding.emailET.getText().toString()))
            {
                if(binding.passET.getText().toString().equals(binding.reppassET.getText().toString()))
                {
                    JSONObject user = new JSONObject();
                    try{
                        user.put("email",binding.emailET.getText().toString());
                        user.put("password",binding.passET.getText().toString());
                        user.put("firstName",binding.surnameET.getText().toString());
                        user.put("lastName",binding.nameET.getText().toString());
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://cinema.areas.su/auth/register", user,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(error.getMessage()!=null){
                                if(error.getMessage().contains("Успешная")){
                                    CheckData.authConfirmed(SignUp_Screen.this,binding.emailET.getText().toString(),binding.passET.getText().toString());
                                }
                                else{
                                    CheckData.makeMessage("Проблема с регистрацией",SignUp_Screen.this);
                                }
                            }
                            else{
                                CheckData.makeMessage("Проблема с регистрацией",SignUp_Screen.this);
                            }
                        }
                    });

                    JsonObjectRequest signUpRequest = new JsonObjectRequest(Request.Method.POST, URLs.REGISTER, user,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    CheckData.authConfirmed(SignUp_Screen.this, binding.emailET.getText().toString(), binding.passET.getText().toString());
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(Objects.requireNonNull(error.getMessage()).contains("Успешная"))
                            {

                                //будет вызывать авторизацию
                                CheckData.authConfirmed(SignUp_Screen.this,
                                        binding.emailET.getText().toString(),
                                        binding.passET.getText().toString());
                            }
                            else
                                CheckData.makeMessage("Проблема с регистрацией ошибка", SignUp_Screen.this);
                        }
                    });
                    AppData.getInstance(this).queue.add(signUpRequest);

                }
                else{
                    CheckData.makeMessage("Пароли не совпадают",SignUp_Screen.this);
                }
            }
            else{
                CheckData.makeMessage("Некорректный почта",SignUp_Screen.this);
            }
        }
        else{
            CheckData.makeMessage("Есть пустые поля", SignUp_Screen.this);
        }
    }
    public void CanSign(View view){
        Intent intent=new Intent(SignUp_Screen.this, SignIn_Screen.class);
        startActivity(intent);
        finish();
    }
}