package com.example.Library.Book.Tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public String checkoutBook(Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow();
        if (book.getAvailableQuantity() > 0) {
            book.setAvailableQuantity(book.getAvailableQuantity() - 1);
            bookRepo.save(book);
            return "Book checked out!";
        }
        return "No copies available!";
    }
    // Similar for returnBook()
}
