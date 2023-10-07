package com.example.loginauthenticator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SingupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText singUpEmailEditText, singUpPasswordEditText;
    private Button singUpButton, singinButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        singUpEmailEditText = findViewById(R.id.singUpEmailEditTextId);
        singUpPasswordEditText = findViewById(R.id.singUpPasswordEditTextId);


        singUpButton =findViewById(R.id.singUpLoginInButtonId);
        singinButton =findViewById(R.id.singUpSingUpButtonId);

        singUpButton.setOnClickListener(this);
        singinButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.singUpLoginInButtonId){



        }
        if (view.getId()==R.id.singUpSingUpButtonId){
            Intent intent = new Intent(SingupActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}