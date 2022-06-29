package com.example.bookclient.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService(){ initializerRetrofit();}

    private void initializerRetrofit() {
        retrofit=new Retrofit.Builder()
                .baseUrl("http:/192.168.56.1:9000")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit(){return retrofit;}
}
