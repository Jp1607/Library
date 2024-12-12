package com.example.demo.Services;

import Enums.Activity;
import com.example.demo.Entities.Book;
import com.example.demo.Entities.User;
import com.example.demo.Repositories.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {

    @Autowired
    DateService dateService;

    @Autowired
    LogService logService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BookRepository bookRepository;

    public String getBookByTitle(String title) {
        try {
        List<Book> bookList = bookRepository.findByTitle(title);
        return objectMapper.writeValueAsString(bookList);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void addBook(Book book) {
        bookRepository.save(book);
        LocalDateTime date = dateService.getCurrentDate();
        User user = new User();
        logService.newLog(user, book, null, date, Activity.NEW);
    }

    public void editBook(Book book) {
        bookRepository.save(book);
    }

    public void excludeBook(Long id) {
        Book tempBook = bookRepository.findByPatrimonialId(id);
        tempBook.setExcluded(true);
        bookRepository.save(tempBook);
    }
}
