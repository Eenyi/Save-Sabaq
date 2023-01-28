package com.example.savesabaq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dumb.db";
    private static final String TABLE_STUDENT = "Students";
    private static final String TABLE_RECORD = "Records";

    private static final String S_ID = "s_id";
    private static final String S_NAME = "s_name";

    private static final String R_DATE = "r_date";
    private static final String R_SABAQ = "r_sabaq";
    private static final String R_SABQI = "r_sabqi";
    private static final String R_MANZIL = "r_manzil";

    public DBHelper(@Nullable Context context) {
        super(context, "name", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String studentTable = "CREATE TABLE " + TABLE_STUDENT + " (" +
                " s_id Integer PRIMARY KEY AUTOINCREMENT," +
                " s_name Text" +
                ");";
        String recordTable = "CREATE TABLE " + TABLE_RECORD + " (" +
                " s_id Integer," +
                " r_date Text," +
                " r_sabaq Integer," +
                " r_sabqi Intger," +
                " r_manzil Integer," +
                "CONSTRAINT fk_s_id FOREIGN KEY (s_id) REFERENCES Students(s_id)" +
                ");";
        db.execSQL(studentTable);
        db.execSQL(recordTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_STUDENT;
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS " + TABLE_RECORD;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean addStudent(String student) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(S_NAME, student);
            if (db.insert(TABLE_STUDENT, null, cv) == -1){
                db.close();
                return false;
            }
            else {
                db.close();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addRecord(Record record, Integer currentStudent) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(S_ID, currentStudent);
            cv.put(R_DATE, record.getDate());
            cv.put(R_SABAQ, record.getSabaq());
            cv.put(R_SABQI, record.getSabqi());
            cv.put(R_MANZIL, record.getManzil());
            if (db.insert(TABLE_RECORD, null, cv) == -1){
                db.close();
                return false;
            }
            else {
                db.close();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Student> getAllStudents() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cr = db.rawQuery("Select * from "+ TABLE_STUDENT,null);
            ArrayList<Student> studentsArrayList = new ArrayList<>();
            if (cr.moveToFirst()) {
                do {
                    studentsArrayList.add(new Student(cr.getInt(0), cr.getString(1)));
                } while (cr.moveToNext());
            }
            return studentsArrayList;
        }
        catch (Exception e) {
            return null;
        }
    }
}




