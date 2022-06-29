package com.example.bookclient.model;

import androidx.annotation.NonNull;

public class Book {

    private int id;
    private String title;
    private String author;
    private String numberOfPages;
    private String rating;
    private String message;
    private Boolean success;

    public String getMessage() {
        return message;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public String getRating() {
        return rating;
    }

    @NonNull
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", rating=" + rating +
                '}';
    }
}
