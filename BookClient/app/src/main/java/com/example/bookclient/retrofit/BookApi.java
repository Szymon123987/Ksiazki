package com.example.bookclient.retrofit;

import com.example.bookclient.model.Book;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookApi {

    @GET("/book/get-all/")
    Call<List<Book>> getAllBooks();
    @POST("/book/save/")
    Call<Book> save(@Body Book book);
    @DELETE("/book/delete/{id}")
    Call<Book> delete(@Path("id") int id);
    @PUT("/book/update/")
    Call<Book> updateBook(@Body Book book);

}