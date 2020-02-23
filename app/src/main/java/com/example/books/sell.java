package com.example.books;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Year;

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



        subject=findViewById(R.id.Subject);
        branch = findViewById(R.id.Branch);
        publications=findViewById(R.id.Publications);
        year= findViewById(R.id.Year);
        submit=findViewById(R.id.submit);
        Price=findViewById(R.id.price);

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
        subject.setOnItemSelectedListener(this);

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
        strbranch = branch.getSelectedItem().toString();
        stryear = year.getSelectedItem().toString();
        strpub =publications.getSelectedItem().toString();
        strsubject=subject.getSelectedItem().toString();
        subfun(strbranch,stryear,strpub);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void submit_sell(View view) {

            String strprice=Price.getText().toString();

            sell_details sell_details;
            sell_details = new sell_details(

                strbranch,
                stryear,
                strpub,
                strsubject,
                strprice
        );

                    // Setting data in database

           String childno= FirebaseDatabase.getInstance().getReference("info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("sell_info").child("publication").getParent().toString();
       // String ids = Long.toString(childno);
        Toast.makeText(getApplicationContext(),childno,Toast.LENGTH_LONG).show();
        FirebaseDatabase.getInstance().getReference("info").child(FirebaseAuth.getInstance().getCurrentUser()
                .getUid()).child("sell_info").push().setValue(sell_details).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Information added successfully",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
