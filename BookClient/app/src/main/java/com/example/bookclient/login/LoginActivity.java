package com.example.bookclient.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookclient.R;
import com.example.bookclient.main.MainActivity;
import com.example.bookclient.model.User;
import com.example.bookclient.register.RegisterActivity;


public class LoginActivity extends AppCompatActivity implements  LoginView{
    EditText et_username, et_password;
    Button loginButton, registerButton;


    LoginPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username=findViewById(R.id.username);
        et_password=findViewById(R.id.password);
        loginButton=findViewById(R.id.loginButton);
        registerButton=findViewById(R.id.registerButton);

        registerButton.setOnClickListener(view -> {
            Intent intentMain=new Intent(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(intentMain);

        });

        loginButton.setOnClickListener(view -> {



            String username=et_username.getText().toString();
            String password=et_password.getText().toString();

            if (username.isEmpty()) {
                et_username.setError("Username is required");
                et_username.requestFocus();
                //return;
            } else if (password.isEmpty()) {
                et_password.setError("Password is required");
                et_password.requestFocus();
                //return;
            }
            else
            {
                System.out.println(username);
                User newUser=new User();
                newUser.setId(1L);
                newUser.setUsername("1");
                newUser.setPassword("1");
                newUser.setLoggedIn(false);
                presenter.loginUser(newUser);
            }
        });



    }

    @Override
    public void showProgress() {

    }

/*    @Override
    public void hideProgress() {

    }

    @Override
    public void navigateToMain() {
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(intent);

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetResult(User user) {


    }*/


}
