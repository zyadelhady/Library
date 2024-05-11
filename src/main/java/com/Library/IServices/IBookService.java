package com.Library.IServices;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Library.models.Book;

@Service
public interface IBookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book addBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
}
