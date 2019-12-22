package com.example.books;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class signup_auth extends AppCompatActivity {


    EditText email;
    EditText pass;
    Button submit;

    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_auth);
        email=findViewById(R.id.auth_email);
        pass=findViewById(R.id.auth_pass);
        submit=findViewById(R.id.auth_submit);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
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
