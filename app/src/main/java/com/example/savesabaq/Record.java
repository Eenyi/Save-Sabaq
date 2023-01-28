package com.example.savesabaq;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Record {
    private Integer rollNumber = 0;
    private String date;
    private Integer sabaq;
    private Integer sabqi;
    private Integer manzil;

    public Record(Integer sabaq, Integer sabqi, Integer manzil) {
        this.date = getDateToString();
        this.sabaq = sabaq;
        this.sabqi = sabqi;
        this.manzil = manzil;
    }

    public Record(Integer rollNumber, String date, Integer sabaq, Integer sabqi, Integer manzil) {
        this.rollNumber = rollNumber;
        this.date = date;
        this.sabaq = sabaq;
        this.sabqi = sabqi;
        this.manzil = manzil;
    }

    public String getDate() {
        return date;
    }

    public Integer getSabaq() {
        return sabaq;
    }

    public void setSabaq(Integer sabaq) {
        this.sabaq = sabaq;
    }

    public Integer getSabqi() {
        return sabqi;
    }

    public void setSabqi(Integer sabqi) {
        this.sabqi = sabqi;
    }

    public Integer getManzil() {
        return manzil;
    }

    public void setManzil(Integer manzil) {
        this.manzil = manzil;
    }

    public static String getDateToString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        String dateToString = df.format(today);
        return dateToString;
    }
}
