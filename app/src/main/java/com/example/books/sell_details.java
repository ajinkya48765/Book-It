package com.example.books;

import android.widget.EditText;
import android.widget.Spinner;

public class sell_details {

    public String department;
    public String year;
    public String publication;
    public String subject;
    public String price;

    public sell_details(String department, String year, String publication, String subject, String price) {
        this.department = department;
        this.year = year;
        this.publication = publication;
        this.subject = subject;
        this.price = price;
    }
}
