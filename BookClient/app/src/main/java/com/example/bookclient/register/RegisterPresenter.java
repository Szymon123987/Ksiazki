package com.example.bookclient.register;

import androidx.annotation.NonNull;

import com.example.bookclient.model.User;

import com.example.bookclient.retrofit.RetrofitService;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterPresenter {
    private final RegisterView view;



    public RegisterPresenter(RegisterView view){this.view=view;}

    protected void registerUser(User user)
    {
        view.showProgress();

        String username= user.getUsername();
        System.out.println(username);
        String password= user.getPassword();

        if (username.isEmpty()) {
            System.out.println("pustoUUU");
            //etUsername.setError("Username is required");
            //etUsername.requestFocus();
            return;
        } else if (password.isEmpty()) {
            System.out.println("pustoPPP");
            //etPassword.setError("Password is required");
            //etPassword.requestFocus();
            return;
        }

        Call<ResponseBody> call = RetrofitService
                .getInstance()
                .getAPI()
                .createUser(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String s = "";
                try {
                    assert response.body() != null;
                    s = response.body().string();
                    System.out.println(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (s.equals("\"SUCCESS\"")) {
                    view.navigateToLoginScreen();
                    view.end();

                    //Toast.makeText(RegisterActivity.this, "Successfully registered. Please login", Toast.LENGTH_LONG).show();
                    //startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    view.showToast("User already exists!");


                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                //Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });





    }
}
