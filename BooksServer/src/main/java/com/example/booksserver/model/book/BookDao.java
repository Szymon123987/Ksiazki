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
        Book bookDao=new Book();
        bookDao.setTitle(book.getTitle());
        bookDao.setAuthor(book.getAuthor());
        bookDao.setRating(book.getRating());
        bookDao.setNumberOfPages(book.getNumberOfPages());

        return repository.save(bookDao);
    }

    public void  delete(int id){
        repository.deleteById(id);
    }
}
