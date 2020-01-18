package com.example.books;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup_auth extends AppCompatActivity {


    EditText email;
    EditText pass;
    EditText name,phone;
    Button submit;

    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_auth);
        email=findViewById(R.id.auth_email);
        pass=findViewById(R.id.auth_pass);
        name=findViewById(R.id.auth_name);
        phone=findViewById(R.id.auth_phone);

        submit=findViewById(R.id.auth_submit);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        databaseReference=FirebaseDatabase.getInstance().getReference("info");



    }

    public void submit(View view) {

        registerUser();
    }

    private void registerUser() {


        String semail = email.getText().toString();
        String spass= pass.getText().toString();
        final String uname=name.getText().toString();
        final String uphone=phone.getText().toString();
        final String uemail=email.getText().toString();

        if(uname.isEmpty())
        {
            name.setError("Enter your name");
            name.requestFocus();
            return;
        }if(semail.isEmpty())
        {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(semail).matches())
        {
            email.setError("Enter valid email");
            email.requestFocus();
            return;
        }
        if(uphone.length()<10)
        {
            phone.setError("Enter valid phone number");
            phone.requestFocus();
            return;
        }
        if(!Patterns.PHONE.matcher(uphone).matches())
        {
            phone.setError("Enter valid phone number");
            phone.requestFocus();
            return;
        }
        if(spass.length()<6)
        {
            pass.setError("Min length is 6");
            pass.requestFocus();
            return;
        }


        progressDialog.setMessage("Registering");
        progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(semail,spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful())
                    {
                        info uinfo= new info(
                               uname,
                                uemail,
                                uphone
                        );
                        FirebaseDatabase.getInstance().getReference("info").child(FirebaseAuth.getInstance().getCurrentUser()
                                .getUid()).child("personal_info").setValue(uinfo);

                        Toast.makeText(getApplicationContext(),"You have Registered Successfully",Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                        Intent intent=new Intent(signup_auth.this, MainActivity.class);
                        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Failed, Please try again",Toast.LENGTH_LONG).show();
                        progressDialog.cancel();
                    }
                }
            });



    }

}
