package com.example.loginauthenticator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText singUpEmailEditText, singUpPasswordEditText;

    private Button singUpButton, singinButton;

    private FirebaseAuth mAuth;

    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        mAuth = FirebaseAuth.getInstance();


        singUpEmailEditText = findViewById(R.id.singUpEmailEditTextId);
        singUpPasswordEditText = findViewById(R.id.singUpPasswordEditTextId);


        singUpButton =findViewById(R.id.singUpLoginInButtonId);
        singinButton =findViewById(R.id.singUpSingUpButtonId);

        progressBar = findViewById(R.id.progressId);

        singUpButton.setOnClickListener(this);
        singinButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.singUpSingUpButtonId){
            userRegistration();
        }
        if (view.getId()==R.id.singUpLoginInButtonId){
            Intent intent = new Intent(SingupActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void userRegistration() {

        String email = singUpEmailEditText.getText().toString().trim();
        String password = singUpPasswordEditText.getText().toString().trim();

        if (email.isEmpty()){
            singUpEmailEditText.setError("Enter an email address");
            singUpEmailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            singUpEmailEditText.setError("Enter an valid email address");
            singUpEmailEditText.requestFocus();
            return;
        }
        if (password.isEmpty()){
            singUpPasswordEditText.setError("Enter a password");
            singUpPasswordEditText.requestFocus();
            return;
        }
        if (password.length()<6){
            singUpPasswordEditText.setError("Minimum 6 debits used");
            singUpPasswordEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Registration successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}