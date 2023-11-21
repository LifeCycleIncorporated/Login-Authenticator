package com.example.loginauthenticator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private ListView listView;
    private Button singOutButton;
    private FirebaseAuth mAuth;

    DatabaseReference databaseReference;
    private List<Student> studentList;
    private CusAdp cusAdp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");
        studentList = new ArrayList<>();
        cusAdp = new CusAdp(WelcomeActivity.this,studentList);
        mAuth = FirebaseAuth.getInstance();

        listView = findViewById(R.id.listViewId);

        singOutButton = findViewById(R.id.singOutButtonId);

        singOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    Student student = dataSnapshot1.getValue(Student.class);
                    studentList.add(student);
                }
                listView.setAdapter(cusAdp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}