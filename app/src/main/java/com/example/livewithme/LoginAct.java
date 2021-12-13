package com.example.livewithme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void clickLogin5(View view){

        EditText myTextField = (EditText) findViewById(R.id.editTextTextPersonName2);
        String user = myTextField.getText().toString();

        EditText myTextField2 = (EditText) findViewById(R.id.editTextTextPassword);
        String pass = myTextField2.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.livewithme", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", user).apply();
        sharedPreferences.edit().putString("password", pass).apply();

        finish();
    }

}