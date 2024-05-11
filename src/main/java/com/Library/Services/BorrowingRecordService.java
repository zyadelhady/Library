package com.Library.Services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Library.Exceptions.BorrowingRecordExistException;
import com.Library.Exceptions.BorrowingRecordNotFoundException;
import com.Library.IServices.IBookService;
import com.Library.IServices.IBorrowingRecordService;
import com.Library.IServices.IPatronService;
import com.Library.models.Book;
import com.Library.models.BorrowingRecord;
import com.Library.models.Patron;
import com.Library.repository.BorrowingRecordRepository;

import jakarta.transaction.Transactional;

@Service
public class BorrowingRecordService implements IBorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IPatronService patronService;

    @Transactional
    @Override
    public BorrowingRecord addBorrowingRecord(Long bookId, Long patronId) {
        Optional<BorrowingRecord> optionalBorrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(bookId,
                patronId);

        if (optionalBorrowingRecord.isPresent()) {
            throw new BorrowingRecordExistException(
                    "Patron with ID " + patronId + " has already borrowed the book with ID " + bookId);
        } else {
            // Retrieve the book and patron
            Book book = bookService.getBookById(bookId);
            Patron patron = patronService.getPatronById(patronId);

            // Create a new borrowing record
            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBook(book);
            borrowingRecord.setPatron(patron);
            borrowingRecord.setBorrowingDate(LocalDate.now());
            return borrowingRecordRepository.save(borrowingRecord);

        }
    }

    @Transactional
    @Override
    public BorrowingRecord recordReturn(Long bookId, Long patronId) {
  Optional<BorrowingRecord> optionalBorrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);

    if (optionalBorrowingRecord.isPresent()) {
        BorrowingRecord borrowingRecord = optionalBorrowingRecord.get();
        borrowingRecord.setReturnDate(LocalDate.now());
        return borrowingRecordRepository.save(borrowingRecord);
    } else {
        // If the borrowing record does not exist, throw an exception
        throw new BorrowingRecordNotFoundException("No borrowing record found for book ID " + bookId + " and patron ID " + patronId);
    }
    }

}