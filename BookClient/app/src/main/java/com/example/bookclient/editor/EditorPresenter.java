package com.example.bookclient.editor;

import com.example.bookclient.model.Book;
import com.example.bookclient.retrofit.BookApi;
import com.example.bookclient.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {
    private EditorView view;

    public EditorPresenter(EditorView view){this.view=view;}

    protected void saveBook(Book book)
    {
        view.showProgress();

        RetrofitService retrofitService=new RetrofitService();
        BookApi bookApi=retrofitService.getRetrofit().create(BookApi.class);

        Call<Book> call=bookApi.save(book);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                view.hideProgress();
                if(response.isSuccessful()&&response.body()!=null)
                {
                    Boolean success=response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else
                    {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });


    }
    void updateBook(Book book){
        view.showProgress();

        RetrofitService retrofitService=new RetrofitService();
        BookApi bookApi=retrofitService.getRetrofit().create(BookApi.class);

        System.out.println(book.getAuthor());

        Call<Book> call=bookApi.updateBook(book);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse( Call<Book> call,  Response<Book> response) {
                view.hideProgress();
                if(response.isSuccessful()&&response.body()!=null){


                    Boolean success=response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }else{
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure( Call<Book> call,  Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deleteBook(int id){
        view.showProgress();
        RetrofitService retrofitService=new RetrofitService();
        BookApi bookApi=retrofitService.getRetrofit().create(BookApi.class);
        Call<Book> call=bookApi.delete(id);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                view.hideProgress();
                if(response.isSuccessful()&&response.body()!=null){


                    Boolean success=response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }else{
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }
}