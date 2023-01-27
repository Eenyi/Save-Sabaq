package com.example.savesabaq;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton addRecord, addStudent, shareApp, gitLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addRecord = findViewById(R.id.addRecord);
        addRecord.setOnClickListener(this);
        addStudent = findViewById(R.id.addStudent);
        addStudent.setOnClickListener(this);
        shareApp = findViewById(R.id.shareApp);
        shareApp.setOnClickListener(this);
        gitLink = findViewById(R.id.gitLink);
        gitLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Uri uri;
        switch (v.getId()) {
            case R.id.gitLink:
                uri = Uri.parse("https://github.com/Eenyi/Save-Sabaq.git");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.shareApp:
                break;
            case R.id.addRecord:
                break;
            case R.id.addStudent:
                //add Student Functionality
                LayoutInflater lf = getLayoutInflater();
                View layout = lf.inflate(R.layout.add_record, (ViewGroup) findViewById(R.id.addRecord_layout));
                AlertDialog.Builder addStudent_dialogue = new AlertDialog.Builder(MainActivity.this);
                addStudent_dialogue.setMessage(R.string.add_student_title);
                addStudent_dialogue.setView(layout);
                addStudent_dialogue.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                addStudent_dialogue.show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}