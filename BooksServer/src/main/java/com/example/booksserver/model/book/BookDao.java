package com.example.booksserver.model.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookDao {
    @Autowired BookRepository repository;

    public Book save(Book book){
        return repository.save(book);
    }

    public List<Book> getAllBooks(){
        List<Book> books=new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(books::add);
        return books;
    }

    public Book update(Book book){
        Book book1=new Book();
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setRating(book.getRating());
        book1.setNumberOfPages(book.getNumberOfPages());

        return repository.save(book);
    }

    public void delete(Book book){
        repository.delete(book);
    }
}
