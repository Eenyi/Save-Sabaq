package com.example.savesabaq;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecordView extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    TextView recordHolder, rollnoHolder;
    ImageButton addPerRecord, homeNavigation;
    DBHelper db;
    Integer currentStudentRollNumber;
    List<Record> currentRecords;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_view);
        intent = getIntent();
        db = new DBHelper(RecordView.this);
        recordHolder = findViewById(R.id.recordHolder);
        recordHolder.setText(intent.getStringExtra("student"));
        rollnoHolder = findViewById(R.id.rollnoHolder);
        rollnoHolder.setText( " رول نمبر  :  " + intent.getStringExtra("rollNumber"));
        currentStudentRollNumber = Integer.parseInt(intent.getStringExtra("rollNumber"));
        addPerRecord = findViewById(R.id.addPerRecord);
        addPerRecord.setOnClickListener(this);
        homeNavigation = findViewById(R.id.homeNavigation);
        homeNavigation.setOnClickListener(this);
        currentRecords = db.getPerStudentRecords(currentStudentRollNumber);
        recyclerView = findViewById(R.id.currentRecordView);
        recyclerView.setHasFixedSize(true);
        //LinearLayoutManager GridLayoutManager
        layoutManager = new LinearLayoutManager(RecordView.this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new myRecyclerViewAdapter(currentRecords) ;
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.addPerRecord:
                //add Record Functionality
                AlertDialog.Builder addStudent_dialogue = new AlertDialog.Builder(RecordView.this);
                LayoutInflater lf = this.getLayoutInflater();
                View layout = lf.inflate(R.layout.add_sabaq, (ViewGroup) findViewById(R.id.addSabaq_layout));
                addStudent_dialogue.setView(layout);
                final EditText sabaq = (EditText) layout.findViewById(R.id.sabaqField);
                final EditText manzil = (EditText) layout.findViewById(R.id.manzilField);
                addStudent_dialogue.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!sabaq.getText().toString().equals("") && !manzil.getText().toString().equals("")) {
                            int sabaq_val = 0, manzil_val = 0;
                            try {
                                sabaq_val = Integer.parseInt(sabaq.getText().toString());
                                manzil_val = Integer.parseInt(manzil.getText().toString());
                                if (sabaq_val <= 0 || manzil_val <= 0) {
                                    dialog.cancel();
                                    Toast.makeText(RecordView.this, "Fields should be greater than Zero!", Toast.LENGTH_SHORT).show();
                                }
                                else if (sabaq_val > 30 || manzil_val > 7) {
                                    dialog.cancel();
                                    Toast.makeText(RecordView.this, "Fields should not be out of range!", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    //code here
                                    Record record = new Record(sabaq_val, sabaq_val - 1, manzil_val);
                                    if (db.addRecord(record, currentStudentRollNumber)) {
                                        Toast.makeText(RecordView.this, recordHolder.getText()+"'s Sabaq recorded!", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(RecordView.this, "Error occured in enrolling!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } catch (Exception e){
                                dialog.cancel();
                                Toast.makeText(RecordView.this, "Non Numeric fields are not allowed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            dialog.cancel();
                            Toast.makeText(RecordView.this, "Empty fields are not allowed!", Toast.LENGTH_SHORT).show();
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
            case R.id.homeNavigation:
                intent = new Intent(RecordView.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}