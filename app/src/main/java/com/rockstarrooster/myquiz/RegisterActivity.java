package com.rockstarrooster.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, password2;
    Button register, cancel;

    Database databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        password2 = findViewById(R.id.editText3);

        register = findViewById(R.id.button);
        cancel = findViewById(R.id.button2);

        databaseHelper = new Database(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameVal = username.getText().toString();
                String passwordVal = password.getText().toString();
                String password2Val = password2.getText().toString();

                if (usernameVal.length() > 1 && passwordVal.length() > 1){
                    if (passwordVal.equals(password2Val)){
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("username", usernameVal);
                        contentValues.put("password", passwordVal);

                        databaseHelper.Insert(contentValues);
                        Toast.makeText(RegisterActivity.this,"USER REGISTERED",Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(RegisterActivity.this,"PASSWORDS DO NOT MATCH",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this,"EMPTY CREDENTIALS",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
