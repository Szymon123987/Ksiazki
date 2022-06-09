package com.example.booksserver.controller;

import com.example.booksserver.model.book.Book;
import com.example.booksserver.model.book.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;

    @GetMapping("book/get-all/")
    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    @PostMapping("book/save/")
    public Book save(@RequestBody Book book){
        return bookDao.save(book);
    }

    @PutMapping("book/update/")
    public Book update(@RequestBody Book book){return bookDao.save(book);}

    @DeleteMapping("book/delete/{id}")
    public void delete(@PathVariable("id") int id){
        bookDao.delete(id);
    }
}
