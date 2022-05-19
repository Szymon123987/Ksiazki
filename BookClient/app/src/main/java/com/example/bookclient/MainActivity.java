package com.example.bookclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.bookclient.model.Book;
import com.example.bookclient.retrofit.BookApi;
import com.example.bookclient.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditTitle=findViewById(R.id.form_textFieldTitle);
        TextInputEditText inputEditAuthor=findViewById(R.id.form_textFieldAuthor);
        TextInputEditText inputEditNumberOfPages=findViewById(R.id.form_textFieldPages);
        TextInputEditText inputEditRating=findViewById(R.id.form_textFieldRating);
        MaterialButton buttonSave=findViewById(R.id.form_buttonSave);

        RetrofitService retrofitService=new RetrofitService();
        BookApi bookApi=retrofitService.getRetrofit().create(BookApi.class);

        buttonSave.setOnClickListener(view -> {
            String title=String.valueOf(inputEditTitle.getText());
            String author=String.valueOf(inputEditAuthor.getText());
            String numberOfPages=String.valueOf(inputEditNumberOfPages.getText());
            String rating=String.valueOf(inputEditRating.getText());

            Book book=new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setNumberOfPages(Integer.parseInt(numberOfPages));
            book.setRating(Integer.parseInt(rating));


            bookApi.save(book)
                    .enqueue(new Callback<Book>() {
                        @Override
                        public void onResponse(Call<Book> call, Response<Book> response) {
                            Toast.makeText(MainActivity.this,"udało się",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Book> call, Throwable t) {
                            Toast.makeText(MainActivity.this,"nie udało się",Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error");
                        }
                    });


        });
    }
}