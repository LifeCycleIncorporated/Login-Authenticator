package com.example.loginauthenticator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mainLoginInUsernameEditText, mainLogInPasswordEditText;

    private Button mainLoginButton, mainSingUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLoginInUsernameEditText = findViewById(R.id.mainLoginInUsernameEditTextId);
        mainLogInPasswordEditText = findViewById(R.id.mainLogInPasswordEditTextId);

        mainLoginButton =findViewById(R.id.mainLoginButtonId);
        mainSingUpButton =findViewById(R.id.mainSingUpButtonId);

        mainLoginButton.setOnClickListener(this);
        mainSingUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.mainLoginButtonId){


        }
        if (view.getId()==R.id.mainSingUpButtonId){
            Intent intent = new Intent(MainActivity.this, SingupActivity.class);
            startActivity(intent);
    }
}

}