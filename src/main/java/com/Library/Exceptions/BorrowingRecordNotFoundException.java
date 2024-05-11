package com.Library.Exceptions;

public class BorrowingRecordNotFoundException extends RuntimeException{
    public BorrowingRecordNotFoundException(String message) {
            super(message);
  }
}
