package com.Library;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Library.IServices.IBorrowingRecordService;
import com.Library.controllers.BorrowingRecordController;
import com.Library.models.BorrowingRecord;
@SpringBootTest
public class BorrowingBooksTestController {
  
    @Mock
    private IBorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBorrowBook_Success() {
        // Mock behavior of borrowingRecordService.addBorrowingRecord
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        when(borrowingRecordService.addBorrowingRecord(anyLong(), anyLong())).thenReturn(borrowingRecord);

        // Call the controller method
        ResponseEntity<BorrowingRecord> responseEntity = borrowingRecordController.borrowBook(1L, 2L);

        // Verify the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(borrowingRecord, responseEntity.getBody());
    }

    @Test
    public void testReturnBook_Success() {
        // Mock behavior of borrowingRecordService.recordReturn
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        when(borrowingRecordService.recordReturn(anyLong(), anyLong())).thenReturn(borrowingRecord);

        // Call the controller method
        ResponseEntity<?> responseEntity = borrowingRecordController.returnBook(1L, 2L);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(borrowingRecord, responseEntity.getBody());
    }

    @Test
    public void testReturnBook_NotFound() {
        // Mock behavior of borrowingRecordService.recordReturn
        when(borrowingRecordService.recordReturn(anyLong(), anyLong())).thenReturn(null);

        // Call the controller method
        ResponseEntity<?> responseEntity = borrowingRecordController.returnBook(1L, 2L);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
