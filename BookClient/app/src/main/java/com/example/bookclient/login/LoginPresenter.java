package com.example.bookclient.login;

import androidx.annotation.NonNull;

import com.example.bookclient.model.User;
import com.example.bookclient.retrofit.BookApi;
import com.example.bookclient.retrofit.RetrofitService;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private final LoginView view;

    public LoginPresenter(LoginView view){this.view=view;}

    protected void loginUser(User user)
    {

        System.out.println("login start");
        view.showProgress();


            Call<ResponseBody> call = RetrofitService
                    .getInstance()
                    .getAPI()
                    .checkUser(user);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    /*String s = "";
                    try {
                        s = response.body().string();
                        view.navigateToMain();
                        System.out.println("3");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    *//*if (s.equals(username)) {
                        //Toast.makeText(LoginActivity.this, "User logged in!", Toast.LENGTH_LONG).show();
                        //startActivity(new Intent(LoginActivity.this, DashboardActivity.class).putExtra("username", userName));
                    } else {
                        //Toast.makeText(LoginActivity.this, "Incorrect Credentials! Try again!", Toast.LENGTH_LONG).show();
                    }*/
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    //Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        protected User findByUsername(String username){
            User newUser=new User();
            System.out.println(username);
            RetrofitService retrofitService=new RetrofitService();
            BookApi bookApi=retrofitService.getRetrofit().create(BookApi.class);

            Call<User> call=bookApi.findUserByUsername(username);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if(response.isSuccessful()&&response.body()!=null){
                        newUser.setId(response.body().getId());
                        newUser.setUsername(response.body().getUsername());
                        newUser.setPassword(response.body().getPassword());
                        newUser.setLoggedIn(response.body().isLoggedIn());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    //view.showToast(t.getLocalizedMessage());
                }
            });
            return newUser;
        }






    }

