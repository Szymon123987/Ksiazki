package com.example.bookclient.main;

import com.example.bookclient.model.Book;

import java.util.List;

public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Book> notes);
    void onErrorLoading(String message);
}
