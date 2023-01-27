package com.example.savesabaq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class StudentView extends AppCompatActivity {
    ListView studentView;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view);
        studentView = findViewById(R.id.studentView);
        db = new DBHelper(StudentView.this);
        List<Student> students = db.getAllStudents();
        ArrayAdapter arrayAdapter = new ArrayAdapter<Student>(StudentView.this, android.R.layout.simple_list_item_1,students);
        studentView.setAdapter(arrayAdapter);
    }
}