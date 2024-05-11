package com.Library.IServices;


import com.Library.models.BorrowingRecord;

public interface IBorrowingRecordService {
    BorrowingRecord addBorrowingRecord(Long bookId, Long patronId);
    BorrowingRecord recordReturn(Long bookId, Long patronId);
}
