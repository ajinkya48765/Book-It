package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText pass;
    Button login;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.et_name);
        pass = findViewById(R.id.et_pass);
        login = findViewById(R.id.b_login);



    }

    public void login(View view) {

        int check = validate(name.getText().toString(), pass.getText().toString());
        if (check == 1) {
            Intent main_to_sellpurchase = new Intent(MainActivity.this, sell_purchase.class);
            startActivity(main_to_sellpurchase);
        } else {
            Toast.makeText(getApplicationContext(),"Invalid Username or password",Toast.LENGTH_LONG).show();
        }
    }

    int validate(String username, String userpass) {
        if (username.equals("admin") && userpass.equals("1234")) {
            return 1;
        } else
            return 0;
    }

    public void signup(View view) {

        Intent main_to_signup=new Intent(MainActivity.this,signup_auth.class);
        startActivity(main_to_signup);
    }
}


