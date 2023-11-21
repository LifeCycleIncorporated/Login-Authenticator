package com.example.loginauthenticator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CusAdp extends ArrayAdapter<Student> {

    private Activity context;
    private List<Student> studentList;

    public CusAdp(@NonNull Activity context, List<Student> studentList) {
        super(context, R.layout.simple_layout, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.simple_layout, null, true);

        Student student = studentList.get(position);

        TextView t1 = view.findViewById(R.id.nameTextViewId);
        TextView t2 = view.findViewById(R.id.ageTextViewId);
        TextView t3 = view.findViewById(R.id.emailTextViewId);
        TextView t4 = view.findViewById(R.id.passwordTextViewId);

        t1.setText("Name : "+student.getName());
        t2.setText("Age : "+student.getAge());
        t1.setText("Email : "+student.getEmail());
        t1.setText("Password : "+student.getPassword());

        return view;
    }
}
