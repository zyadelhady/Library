package com.Library;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Library.Services.BookService;
import com.Library.controllers.BookController;
import com.Library.models.Book;

@SpringBootTest
public class BookTestsController {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
      MockitoAnnotations.openMocks(this);
    }
    
    
 @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book 2");

        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books);

        ResponseEntity<List<Book>> response = bookController.getAllBooks();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody()),
                () -> assertEquals(2, response.getBody().size()),
                () -> verify(bookService, times(1)).getAllBooks()
        );
    }

    @Test
    public void testGetBookById() throws Exception {
        Long id = 1L;
        Book book = new Book();
        book.setId(id);
        book.setTitle("Book Title");

        when(bookService.getBookById(id)).thenReturn(book);

        ResponseEntity<Book> response = bookController.getBookById(id);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody()),
                () -> assertEquals(id, response.getBody().getId()),
                () -> verify(bookService, times(1)).getBookById(id));
    }
    
@Test
public void testAddBook() throws Exception {
    // Create a new Book object to add
    Book bookToAdd = new Book();
    bookToAdd.setTitle("Test Book");
    bookToAdd.setAuthor("Test Author");
    bookToAdd.setIsbn("1234567890");
    bookToAdd.setPublicationYear(Year.now());

    // Stub the behavior of the addBook method in the bookService mock
    when(bookService.addBook(bookToAdd)).thenReturn(bookToAdd);

    // Call the addBook method in the controller
    ResponseEntity<?> response = bookController.addBook(bookToAdd,null);

    // Verify the response
    assertAll(
            () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
            () -> assertNotNull(response.getBody())
    );
}

@Test
public void testDeleteBook() throws Exception {
    Long bookId = 1L;

    // Mock the behavior of the deleteBook method in the bookService
    doNothing().when(bookService).deleteBook(bookId);

    // Call the deleteBook method in the controller
    ResponseEntity<Void> response = bookController.deleteBook(bookId);

    // Verify the response
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
}

@Test
public void testUpdateBook() throws Exception {
    // Create a Book object with updated data
    Long bookId = 1L;
    Book updatedBook = new Book();
    updatedBook.setId(bookId);
    updatedBook.setTitle("Updated Title");
    updatedBook.setAuthor("Updated Author");
    updatedBook.setIsbn("9876543210");
    updatedBook.setPublicationYear(Year.now());

    // Stub the behavior of the updateBook method in the bookService mock
    when(bookService.updateBook(bookId, updatedBook)).thenReturn(updatedBook);

    // Call the updateBook method in the controller
    ResponseEntity<Book> response = bookController.updateBook(bookId, updatedBook);

    // Verify the response
    assertAll(
            () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
            () -> assertNotNull(response.getBody())
            // You may add more assertions based on the behavior of your application
    );
}

}
