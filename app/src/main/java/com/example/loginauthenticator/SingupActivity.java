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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText, ageEditText, singUpEmailEditText, singUpPasswordEditText;

    private Button singUpButton, singinButton;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        mAuth = FirebaseAuth.getInstance();

        nameEditText = findViewById(R.id.nameEditTextId);
        ageEditText = findViewById(R.id.ageEditTextId);

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

        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();

        String key = databaseReference.push().getKey();

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

        Student student = new Student(name,age,email, password);

        databaseReference.child(key).setValue(student);
        Toast.makeText(getApplicationContext(),"Data is saved",Toast.LENGTH_SHORT).show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()){
                    finish();
                    Toast.makeText(getApplicationContext(),"Registration successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SingupActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
                else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "User is already Registered",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}