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

public class purchase extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button submit;
    EditText Price;
    String strbranch1, stryear1, strsubject1;
    Spinner branch1, year1, subject1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Intent intent1 = getIntent();

        branch1=(Spinner)findViewById(R.id.branch1);
        subject1=(Spinner)findViewById(R.id.subject1);
        year1=(Spinner) findViewById(R.id.year1);

        //branch1
        ArrayAdapter<CharSequence> adapter5= ArrayAdapter.createFromResource(this,R.array.list_Branch,android.R.layout.simple_selectable_list_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch1.setAdapter(adapter5);
        branch1.setOnItemSelectedListener(this);

        //year1
        ArrayAdapter<CharSequence> adapter6=ArrayAdapter.createFromResource(this,R.array.list_year,android.R.layout.simple_selectable_list_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year1.setAdapter(adapter6);
        year1.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapter3= ArrayAdapter.createFromResource(this,R.array.TE_Comp,android.R.layout.simple_selectable_list_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject1.setAdapter(adapter3);


    }

    public void pubfun(String i,String j)
    {

        if(strbranch1.equals("Computer")&&stryear1.equals("SE"))
        {

            ArrayAdapter<CharSequence> adapter3= ArrayAdapter.createFromResource(this,R.array.SE_Comp,android.R.layout.simple_selectable_list_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subject1.setAdapter(adapter3);
        }
        if(strbranch1.equals("ENTC")&&stryear1.equals("SE"))
        {
            ArrayAdapter<CharSequence> adapter3= ArrayAdapter.createFromResource(this,R.array.SE_ENTC,android.R.layout.simple_selectable_list_item);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subject1.setAdapter(adapter3);
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        strbranch1=branch1.getSelectedItem().toString();
        stryear1=year1.getSelectedItem().toString();

        pubfun(strbranch1,stryear1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
