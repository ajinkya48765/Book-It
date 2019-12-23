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

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText pass;
    Button login;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.et_name);
        pass = findViewById(R.id.et_pass);
        login = findViewById(R.id.b_login);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

    }

    public void login(View view) {
        String sname=name.getText().toString();
        String spass=pass.getText().toString();
        if(sname.isEmpty())
        {
            name.setError("Email is required");
            name.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(sname).matches())
        {
            name.setError("Enter valid email");
            name.requestFocus();
            return;
        }
        if(spass.isEmpty())
        {
            name.setError("Email is required");
            name.requestFocus();
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(sname,spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                    Intent main_to_sellpurchase = new Intent(MainActivity.this, sell_purchase_window.class);
                    main_to_sellpurchase.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(main_to_sellpurchase);
                    progressDialog.cancel();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();

                    progressDialog.cancel();
                }
            }
        });
    }



    public void signup(View view) {

        Intent main_to_signup=new Intent(MainActivity.this,signup_auth.class);
        startActivity(main_to_signup);
    }
}


