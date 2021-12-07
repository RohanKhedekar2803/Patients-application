package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DashBoard extends AppCompatActivity {
   TextView textView;
    TextView textView_id;
    TextView textView_call;
    TextView textView_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        textView=findViewById(R.id.textview);
        textView_id=findViewById(R.id.textview1);
        textView_call=findViewById(R.id.textview2);
        textView_mail=findViewById(R.id.textview3);
        Intent intent =getIntent();
        String name=intent.getStringExtra("key1");
        String username=intent.getStringExtra("key2");
        String email=intent.getStringExtra("key3");
        String phone=intent.getStringExtra("key4");
        String password=intent.getStringExtra("key5");

        textView.setText(name);

        textView_id.setText(username);
//
        textView_call.setText(email);
//
        textView_mail.setText(phone);
//
//        textView.setText("password:"+password+"\n ");


    }
}