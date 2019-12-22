package com.example.books;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {


    EditText name,password,phone,email;
    Spinner branch,year;
    String sname,spass,sphone,semail,sbranch,syear;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name=findViewById(R.id.et_name_signup);
        password=findViewById(R.id.et_pass_signup);
        phone=findViewById(R.id.et_phone);
        email=findViewById(R.id.et_email);
        branch=findViewById(R.id.branch);
        year=findViewById(R.id.year);



        Spinner branch =(Spinner)findViewById(R.id.branch);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.list_branch,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(adapter);

        Spinner year =(Spinner)findViewById(R.id.year);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.list_year,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapter2);


    }


    public void submit(View view) {

        sname=name.getText().toString();
        spass=password.getText().toString();
        sphone=phone.getText().toString();
        semail=email.getText().toString();
        sbranch=branch.getSelectedItem().toString();
        syear=year.getSelectedItem().toString();

        if(sname.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Enter Valid name",Toast.LENGTH_LONG).show();
        }
        else if(spass.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Enter Valid password",Toast.LENGTH_LONG).show();
        }
        else if(sphone.equals("")||sphone.length()!=10)
        {
            Toast.makeText(getApplicationContext(),"Enter Valid Phone number",Toast.LENGTH_LONG).show();
        }
        else if(semail.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Enter Valid E-mail ID",Toast.LENGTH_LONG).show();
        }
        else if (sbranch.equals("Department"))
        {
            Toast.makeText(getApplicationContext(),"Select Department",Toast.LENGTH_LONG).show();
        }
        else if(syear.equals("Year"))
        {
            Toast.makeText(getApplicationContext(),"Select Year",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Account Created Successfully",Toast.LENGTH_LONG).show();

        }


    }
}

