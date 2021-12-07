package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText editText_username_login;
    EditText editText_password_login;
    Button button_login;
    static final String key1="";
    static final String key2="";
    static final String key3="";
    static final String key4="";
    static final String key5="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_password_login=findViewById(R.id.login_password);
        editText_username_login=findViewById(R.id.login_username);
        button_login=findViewById(R.id.login_btn);


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEnteredUsername=editText_username_login.getText().toString();
                String userEnteredPassword=editText_password_login.getText().toString();


                DatabaseReference reference= FirebaseDatabase.getInstance().getReference("STUDENTS");
                Query checkUser =reference.orderByChild("username").equalTo(userEnteredUsername);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {



                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String value = dataSnapshot.getValue(String.class);
                        if (dataSnapshot.exists()) {

                            String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                            String fullnameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                            String mailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                            String phonenoFromDB = dataSnapshot.child(userEnteredUsername).child("phone").getValue(String.class);
                            String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                            if (passwordFromDB.equals(userEnteredPassword) ){

                                Intent intent = new Intent(LoginActivity.this, DashBoard.class);
                                intent.putExtra("key1",fullnameFromDB);
                                intent.putExtra("key2",usernameFromDB);
                                intent.putExtra("key3",mailFromDB);
                                intent.putExtra("key4",phonenoFromDB);
                                intent.putExtra("key5",passwordFromDB);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                              editText_password_login.requestFocus();
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Wrong username", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(login.this, "inside first else", Toast.LENGTH_SHORT).show();
                            editText_username_login.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}