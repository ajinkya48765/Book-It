package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Spinner branch =(Spinner)findViewById(R.id.branch);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.list_branch,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(adapter);

        Spinner year =(Spinner)findViewById(R.id.year);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.list_year,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapter2);
    }


}
