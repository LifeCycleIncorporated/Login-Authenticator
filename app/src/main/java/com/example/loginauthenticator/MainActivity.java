package com.example.loginauthenticator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText loginEmainEditText, loginPasswordEditText;

    private Button mainLoginButton, mainSingUpButton;

    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        loginEmainEditText = findViewById(R.id.mainLoginInUsernameEditTextId);
        loginPasswordEditText = findViewById(R.id.mainLogInPasswordEditTextId);

        mainLoginButton =findViewById(R.id.mainLoginButtonId);
        mainSingUpButton =findViewById(R.id.mainSingUpButtonId);

        progressBar = findViewById(R.id.mainProgressId);

        mainLoginButton.setOnClickListener(this);
        mainSingUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.mainLoginButtonId){

            userLogin();

        }
        if (view.getId()==R.id.mainSingUpButtonId){
            Intent intent = new Intent(MainActivity.this, SingupActivity.class);
            startActivity(intent);
    }
}

    private void userLogin() {
        String email = loginEmainEditText.getText().toString().trim();
        String password = loginPasswordEditText.getText().toString().trim();

        if (email.isEmpty()){
            loginEmainEditText.setError("Enter an email address");
            loginEmainEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginEmainEditText.setError("Enter valid email address");
            loginEmainEditText.requestFocus();
            return;
        }
        if (password.isEmpty()){
            loginPasswordEditText.setError("Enter a password");
            loginPasswordEditText.requestFocus();
            return;
        }
        if (password.length()<4){
            loginPasswordEditText.setError("Minimum length of a password 4");
            loginPasswordEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    finish();
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

    }

}