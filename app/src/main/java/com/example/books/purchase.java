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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class purchase extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button purchase_submit;
    EditText Price;
    String strbranch1, stryear1, strsubject1;
    Spinner branch1, year1, subject1;
    final ArrayList<String> emailarry = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Intent intent1 = getIntent();

        FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();
        final DatabaseReference mdatareff = mdatabase.getReference("info");

        branch1=(Spinner)findViewById(R.id.branch1);
        subject1=(Spinner)findViewById(R.id.subject1);
        year1=(Spinner) findViewById(R.id.year1);
        purchase_submit=(Button) findViewById(R.id.purchase_submit);

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

        //subjects
        ArrayAdapter<CharSequence> adapter3= ArrayAdapter.createFromResource(this,R.array.TE_Comp,android.R.layout.simple_selectable_list_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject1.setAdapter(adapter3);


        purchase_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!strbranch1.isEmpty()&&!strsubject1.isEmpty()&&!stryear1.isEmpty())
                {
                    //Toast.makeText(getApplicationContext(),"yuioio",Toast.LENGTH_LONG).show();
                    mdatareff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String key;
                            String key1 =  FirebaseDatabase.getInstance().getReference("info").child(FirebaseAuth.getInstance().getCurrentUser()
                                    .getUid()).child("sell_info").push().getKey();

                            for(DataSnapshot keynode : dataSnapshot.child("sell_info").getChildren()){

                                Toast.makeText(getApplicationContext(),"heyy",Toast.LENGTH_LONG).show();
                               // String dept = keynode.child("sell_info").child("department").getValue().toString();
                              //  String subject = keynode.child("sell_info").child("subject").getValue().toString();
                               // String year = keynode.child("sell_info").child("year").getValue().toString();
                               // Long id=keynode.child("sell_info").getChildrenCount()   ;
                               // String ids = Long.toString(id);
                               // key = keynode.child("sell_info").getKey();
                               // Toast.makeText(getApplicationContext(),key,Toast.LENGTH_LONG).show();

 //                               if(strbranch1==dept&&strsubject1==subject&&stryear1==year){

                                    String name=keynode.child("department").getValue().toString();
                                Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();


   //                            }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });

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
        strsubject1= subject1.getSelectedItem().toString();

        pubfun(strbranch1,stryear1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
