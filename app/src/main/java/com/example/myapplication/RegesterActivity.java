package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegesterActivity extends AppCompatActivity {
    EditText editText_full_name;
    EditText editText_username;
    EditText editText_email;
    EditText editText_phone_no;
    EditText editText_password;
    Button button_regester;
    Button button_login;
    //....................................
    FirebaseDatabase rootNode;
    DatabaseReference refrence;
    //...................
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);
        editText_full_name=findViewById(R.id.FULL_NAME);
        editText_username=findViewById(R.id.USERNAME);
        editText_password=findViewById(R.id.PASSWORD);
        editText_email=findViewById(R.id.EMAIL);
        editText_phone_no=findViewById(R.id.PHONE_NO);
        button_regester=findViewById(R.id.regester_btn);
        button_login=findViewById(R.id.login_btn);





        button_regester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode= FirebaseDatabase.getInstance();
                refrence=rootNode.getReference("STUDENTS");

//                geting values
                String fullname=editText_full_name.getText().toString();
                if(fullname.isEmpty()){ editText_full_name.setError("Enter full name"); editText_username.requestFocus();}
                String username=editText_username.getText().toString();
                if(username.isEmpty()){ editText_username.setError("Feild cannot be empty"); } else if(username.length()>15){
                    editText_username.setError("Username too Long");
//
                }else{ }
                String email=editText_email.getText().toString();
                if(email.isEmpty()){ editText_email.setError("Enter email"); editText_email.requestFocus();}
                String phone_no=editText_phone_no.getText().toString();
                if(phone_no.isEmpty()){
                    editText_phone_no.setError("Enter Mobile Number");
                    editText_phone_no.requestFocus();
                }else if(phone_no.length()!=10){ editText_phone_no.setError("Invalid Mobile Number");  editText_phone_no.requestFocus();}
                String password=editText_password.getText().toString();
                if(password.isEmpty()){ editText_password.setError("Enter Password");
                editText_password.requestFocus();}





                //
                if(fullname.isEmpty() || username.isEmpty() || email.isEmpty() || phone_no.isEmpty() || password.isEmpty()){
                    Toast.makeText(RegesterActivity.this, "Enter All Information", Toast.LENGTH_SHORT).show();
                }else {
                    UserHelperClass userHelperClass = new UserHelperClass(fullname, username, email, phone_no, password);
                    refrence.child(username)
                            .setValue(userHelperClass);
                    Toast.makeText(RegesterActivity.this, "Regestered Succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegesterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RegesterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}