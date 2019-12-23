package com.example.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class sell_purchase_window extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_purchase_window);


    }

    public  void FSell(View view){
        Intent intent =new Intent(this,sell.class);
        startActivity(intent);

    }
    public void FPurchase(View view)
    {
        Intent intent=new Intent(this,purchase.class);
        startActivity(intent);
    }
}
