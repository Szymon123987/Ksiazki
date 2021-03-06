package com.example.bookclient.main;

import androidx.annotation.NonNull;

import com.example.bookclient.model.Book;
import com.example.bookclient.retrofit.BookApi;
import com.example.bookclient.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    MainView view;

    public MainPresenter(MainView view) {
        this.view=view;
    }

    void getData(){
        view.showLoading();
        RetrofitService retrofitService=new RetrofitService();
        BookApi bookApi=retrofitService.getRetrofit().create(BookApi.class);
        Call<List<Book>> call=bookApi.getAllBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(@NonNull Call<List<Book>> call, @NonNull Response<List<Book>> response) {
                view.hideLoading();
                if(response.isSuccessful()&&response.body()!=null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Book>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}