package com.example.android.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    database db;
    EditText username1 ;
    EditText password1 ;
    Button logIn ;
    EditText username2;
    EditText password2;
    EditText email;
    EditText phone;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username1 = (EditText) findViewById(R.id.username1);
        password1 = (EditText) findViewById(R.id.password1);
        logIn = (Button) findViewById(R.id.login);
        username2 = (EditText) findViewById(R.id.username2);
        password2 = (EditText) findViewById(R.id.password2);
        signUp = (Button) findViewById(R.id.signUp);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        db=new database(this);


                    //login
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username1.getText().toString().equals("") || password1.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{

                        Boolean B = db.read(username1.getText().toString().trim(), password1.getText().toString().trim());
                    if(B) {
                        Intent intent = new Intent(MainActivity.this, Alarm.class);
                        startActivity(intent);
                    }
                   else{
                       Toast.makeText(MainActivity.this, "Invalid username/email or Password", Toast.LENGTH_SHORT).show();
                   }


                }
            }
        });
                   //register
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username2.getText().toString().equals("") || password2.getText().toString().equals("")||phone.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.insert(email.getText().toString().trim(), username2.getText().toString().trim(), phone.getText().toString().trim(),password2.getText().toString().trim());
                    email.getText().clear();
                    username2.getText().clear();
                    password2.getText().clear();
                    phone.getText().clear();
                    Toast.makeText(MainActivity.this, "you are registered successfully!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
