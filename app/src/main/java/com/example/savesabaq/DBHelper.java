package com.example.savesabaq;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Sabaq.db";
    private static final String TABLE_STUDENT = "Students";
    private static final String TABLE_RECORD = "Records";

    private static final String S_ID = "s_id";
    private static final String S_NAME = "s_name";

    private static final String SR_ID = "sr_id";
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
        db.execSQL(studentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_STUDENT;
        db.execSQL(sql);
        onCreate(db);
    }
}
