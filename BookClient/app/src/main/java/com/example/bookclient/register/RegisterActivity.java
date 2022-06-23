package com.example.bookclient.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookclient.R;
import com.example.bookclient.login.LoginActivity;
import com.example.bookclient.model.User;

public class RegisterActivity extends AppCompatActivity implements RegisterView {


    EditText et_username,et_password;
    Button registerButton, loginButton;

    RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_username=findViewById(R.id.username);
        et_password=findViewById(R.id.password);
        registerButton=findViewById(R.id.registerButton);
        loginButton=findViewById(R.id.loginButton);

        presenter=new RegisterPresenter(this);

        registerButton.setOnClickListener(view -> {
            User user=new User();
            user.setUsername(et_username.getText().toString());
            user.setPassword(et_password.getText().toString());
            presenter.registerUser(user);
        });
        loginButton.setOnClickListener(view -> navigateToLoginScreen());

    }



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestSuccess(String message) {

    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

   @Override
    public void navigateToLoginScreen() {
        Intent intentMain = new Intent(RegisterActivity.this ,
                LoginActivity.class);
        RegisterActivity.this.startActivity(intentMain);
        finish();
    }

    @Override
    public void stay() {

    }

    @Override
    public void end() {
        finish();
    }


}
