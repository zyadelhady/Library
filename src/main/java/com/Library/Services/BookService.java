package com.Library.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.Library.Exceptions.BookNotFoundException;
import com.Library.IServices.IBookService;
import com.Library.models.Book;
import com.Library.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService implements IBookService {
 
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Cacheable("getBook")
    public Book getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));

    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    
    @Transactional
    @Override
    @CacheEvict(value = "getBook", key = "#id")
    public Book updateBook(Long id, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            book.setId(id); // Ensure the provided ID matches the entity ID
            return bookRepository.save(book);
        }
        throw new BookNotFoundException("Book with ID " + id + " not found");

    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
