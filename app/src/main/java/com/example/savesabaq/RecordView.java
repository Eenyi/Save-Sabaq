package com.example.savesabaq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecordView extends AppCompatActivity {
    Intent intent;
    TextView recordHolder, rollnoHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_view);
        intent = getIntent();
        recordHolder = findViewById(R.id.recordHolder);
        recordHolder.setText(intent.getStringExtra("student"));
        rollnoHolder = findViewById(R.id.rollnoHolder);
        rollnoHolder.setText( " رول نمبر  :  " + intent.getStringExtra("rollNumber"));
    }
}