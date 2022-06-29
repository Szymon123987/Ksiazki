package com.example.bookclient.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.bookclient.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFirebase extends AppCompatActivity {
    private EditText email, password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_firebase);

        email=findViewById(R.id.emailRegisterFirebase);
        password=findViewById(R.id.passwordRegisterFirebase);
        Button register = findViewById(R.id.registerButtonRegisterFirebase);
        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(v -> {
            String txtEmail = email.getText().toString();
            String txtPassword = password.getText().toString();

            if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){
                Toast.makeText(RegisterFirebase.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
            } else if (txtPassword.length() < 6){
                Toast.makeText(RegisterFirebase.this, "Password too short!", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(txtEmail , txtPassword);
            }
        });
    }

    private void registerUser( final String email, String password) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(RegisterFirebase.this,"Register successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterFirebase.this,Firebase.class));
                finish();
            }
            else{
                Toast.makeText(RegisterFirebase.this,"Register no successful",Toast.LENGTH_SHORT).show();
            }
        });
    }
}