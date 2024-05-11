package com.Library.IServices;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Library.models.Patron;

@Service
public interface IPatronService {

  List<Patron> getAllPatrons();
  Patron getPatronById(Long id);
  Patron addPatron(Patron patron);
  Patron updatePatron(Long id ,Patron patron);
  void deletePatron(Long id);
}
