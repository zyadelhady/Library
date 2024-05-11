package com.Library.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Library.IServices.IBorrowingRecordService;
import com.Library.models.BorrowingRecord;

@RestController
@RequestMapping("/api/v1")
public class BorrowingRecordController {

    @Autowired
    private IBorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
    var borrowingRecord = borrowingRecordService.addBorrowingRecord(bookId, patronId);
    return ResponseEntity.status(HttpStatus.CREATED).body(borrowingRecord);

    }
  @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        var borrowingRecord =  borrowingRecordService.recordReturn(bookId, patronId);
        return ResponseEntity.status(HttpStatus.OK).body(borrowingRecord);
    }
}