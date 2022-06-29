package com.example.bookclient.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.bookclient.R;

public class Firebase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        Button register = findViewById(R.id.registerButtonFirebase);
        Button login = findViewById(R.id.loginButtonFirebase);
        register.setOnClickListener(view -> {
            startActivity(new Intent(Firebase.this,RegisterFirebase.class));
            finish();
        });

        login.setOnClickListener(view -> {
            startActivity(new Intent(Firebase.this,LoginFirebase.class));
            finish();
        });
    }
}