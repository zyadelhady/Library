package com.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Library.models.Book;

@Repository
public interface BookRepository  extends JpaRepository<Book,Long>{

}
