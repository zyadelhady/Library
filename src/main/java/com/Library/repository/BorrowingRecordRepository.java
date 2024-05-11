package com.Library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Library.models.BorrowingRecord;


public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {
  	@Query("SELECT br FROM BorrowingRecord br WHERE br.book.id = ?1 AND br.patron.id = ?2")
    Optional<BorrowingRecord> findByBookIdAndPatronId(Long bookId, Long patronId);
}
