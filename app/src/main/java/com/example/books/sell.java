package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class sell extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button submit;
    EditText Price;
    String strbranch,stryear,strsubject,strpub;
    Spinner branch,year,subject,publications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        Intent intent=getIntent();

        subject=(Spinner) findViewById(R.id.Subject);
        branch = (Spinner) findViewById(R.id.Branch);
        publications=(Spinner) findViewById(R.id.Publications);
        year= findViewById(R.id.Year);

        ArrayAdapter<CharSequence> adapter1 =ArrayAdapter.createFromResource(this,R.array.list_Branch,android.R.layout.simple_selectable_list_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(adapter1);
        branch.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.list_year,android.R.layout.simple_selectable_list_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapter2);
        year.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter3= ArrayAdapter.createFromResource(this,R.array.TE_Comp,android.R.layout.simple_selectable_list_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 =ArrayAdapter.createFromResource(this,R.array.list_publications,android.R.layout.simple_selectable_list_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        publications.setAdapter(adapter4);
        publications.setOnItemSelectedListener(this);

    }
    private void subfun(String i,String j,String k)
    {
        if(strbranch.equals("Computer")&&stryear.equals("SE"))
        {
            ArrayAdapter<CharSequence> adapter3= ArrayAdapter.createFromResource(this,R.array.SE_Comp,android.R.layout.simple_selectable_list_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subject.setAdapter(adapter3);
        }
        if(strbranch.equals("ENTC")&&stryear.equals("SE"))
        {
            ArrayAdapter<CharSequence> adapter3= ArrayAdapter.createFromResource(this,R.array.SE_ENTC,android.R.layout.simple_selectable_list_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subject.setAdapter(adapter3);
        }
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        subject=(Spinner) findViewById(R.id.Subject);
        strbranch = branch.getSelectedItem().toString();
        stryear = year.getSelectedItem().toString();
        strpub =publications.getSelectedItem().toString();
        subfun(strbranch,stryear,strpub);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
