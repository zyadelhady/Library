package com.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Library.models.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron,Long> {

  

}
