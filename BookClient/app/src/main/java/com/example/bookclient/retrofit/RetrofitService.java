package com.example.bookclient.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;
    private static RetrofitService mInstance;

    public RetrofitService(){ initializerRetrofit();}

    private void initializerRetrofit() {
        retrofit=new Retrofit.Builder()
                .baseUrl("http:/192.168.0.67:9000")
                //.baseUrl("http:/192.168.157.135:9000")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }
    public static synchronized RetrofitService getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitService();
        }
        return mInstance;
    }


    public BookApi getAPI () {
        return retrofit.create(BookApi.class);
    }
    public Retrofit getRetrofit(){return retrofit;}
}
