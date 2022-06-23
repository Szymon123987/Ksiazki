package com.example.bookclient.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookclient.R;
import com.example.bookclient.main.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFirebase extends AppCompatActivity {

    private Button register, login;
    private EditText email, password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_firebase);

        email=findViewById(R.id.emailLoginFirebase);
        password=findViewById(R.id.passwordLoginFirebase);
        login=findViewById(R.id.loginButtonLoginFirebase);



        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(view -> {
            String txt_email=email.getText().toString();
            String txt_password=password.getText().toString();
            loginUser(txt_email,txt_password);
        });
    }

    private void loginUser(String txt_email, String txt_password) {
        mAuth.signInWithEmailAndPassword(txt_email,txt_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginFirebase.this,"Success Login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginFirebase.this, MainActivity.class));
                finish();
            }
        });
    }
}