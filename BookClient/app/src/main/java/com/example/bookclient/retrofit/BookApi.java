package com.example.bookclient.retrofit;

import com.example.bookclient.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BookApi {

    @GET("/book/get-all")
    Call<List<Book>> getAllBooks();
    @POST("/book/save")
    Call<Book> save(@Body Book book);
}
