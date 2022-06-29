package com.example.bookclient.editor;

import static android.content.ContentValues.TAG;
import android.util.Log;
import androidx.annotation.NonNull;
import com.example.bookclient.model.Book;
import com.example.bookclient.retrofit.BookApi;
import com.example.bookclient.retrofit.RetrofitService;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final EditorView view;
    public EditorPresenter(EditorView view){this.view=view;}

    protected void saveBook(Book book)
    {
        view.showProgress();
        RetrofitService retrofitService=new RetrofitService();
        BookApi bookApi=retrofitService.getRetrofit().create(BookApi.class);
        Call<Book> call=bookApi.save(book);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(@NonNull Call<Book> call, @NonNull Response<Book> response) {
                view.hideProgress();
                if(response.isSuccessful()&&response.body()!=null)
                {
                    view.onRequestSuccess(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Book> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
    void saveBookFirebase(String title){
        final String TITLE_KEY="Title";
        Map<String,Object> dataToSave= new HashMap<>();
        dataToSave.put(TITLE_KEY,title);
        db.collection("books")
                .add(dataToSave)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }
    void updateBook(Book book){
        view.showProgress();
        RetrofitService retrofitService=new RetrofitService();
        BookApi bookApi=retrofitService.getRetrofit().create(BookApi.class);
        Call<Book> call=bookApi.updateBook(book);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(@NonNull Call<Book> call, @NonNull Response<Book> response) {
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
            public void onFailure(@NonNull Call<Book> call, @NonNull Throwable t) {
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
            public void onResponse(@NonNull Call<Book> call, @NonNull Response<Book> response) {
                view.hideProgress();
            }

            @Override
            public void onFailure(@NonNull Call<Book> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError("DELETED!");
            }
        });
    }
}