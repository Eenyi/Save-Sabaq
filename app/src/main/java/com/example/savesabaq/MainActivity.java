package com.example.savesabaq;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton addRecord, addStudent, shareApp, gitLink;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(MainActivity.this);
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
                intent = new Intent(MainActivity.this, StudentView.class);
                startActivity(intent);
                break;
            case R.id.addStudent:
                //add Student Functionality
                AlertDialog.Builder addStudent_dialogue = new AlertDialog.Builder(MainActivity.this);
                addStudent_dialogue.setMessage(R.string.add_student_title);
                LayoutInflater lf = this.getLayoutInflater();
                View layout = lf.inflate(R.layout.add_record, (ViewGroup) findViewById(R.id.addRecord_layout));
                addStudent_dialogue.setView(layout);
                final EditText input = (EditText) layout.findViewById(R.id.studentNameField);
                addStudent_dialogue.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!input.getText().toString().equals("")) {
                            if (db.addStudent(input.getText().toString())) {
                                Toast.makeText(MainActivity.this, "Student Enrolled!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Error occured in enrolling!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            dialog.cancel();
                            Toast.makeText(MainActivity.this, "Empty name not allowed!", Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();
                    }
                });
                addStudent_dialogue.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }
                });
                addStudent_dialogue.show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}