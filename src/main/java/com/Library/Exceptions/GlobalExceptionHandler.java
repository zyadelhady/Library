package com.Library.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// import org.springframework.web.ErrorResponse;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.apache.coyote.BadRequestException;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
  

    @ExceptionHandler({BookNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(BookNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
    
    
    @ExceptionHandler({PatronNotFoundEXception.class})
    public ResponseEntity<Object> handlePatronNotFoundException(PatronNotFoundEXception exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({BorrowingRecordNotFoundException.class})
    public ResponseEntity<Object> handleBorrowingRecordNotFoundException(BorrowingRecordNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler({
        BorrowingRecordExistException.class})
    public ResponseEntity<Object> handleBorrowingRecordExistException(
        BorrowingRecordExistException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
}
