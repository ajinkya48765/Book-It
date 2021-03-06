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

public class signup_auth extends AppCompatActivity {


    EditText email;
    EditText pass;
    Button submit,verify;

    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_auth);
        email=findViewById(R.id.auth_email);
        pass=findViewById(R.id.auth_pass);
        submit=findViewById(R.id.auth_submit);
        //verify=findViewById(R.id.verify);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
    }

    public void submit(View view) {

        registerUser();
    }

    private void registerUser() {

        String semail = email.getText().toString();
        String spass= pass.getText().toString();

        if(semail.isEmpty())
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

    public void verify(View view) {

        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Toast.makeText(getApplicationContext(),"Verification mail has been sent",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Failed, Please try again",Toast.LENGTH_LONG).show();

            }
        });
    }
}
