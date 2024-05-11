package com.Library.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.Library.Exceptions.PatronNotFoundEXception;
import com.Library.IServices.IPatronService;
import com.Library.models.Patron;
import com.Library.repository.PatronRepository;

import jakarta.transaction.Transactional;

@Service
public class PatronService implements IPatronService {

  
  @Autowired
  private PatronRepository patronRepository;
  
  @Override
  public List<Patron> getAllPatrons() {
    return patronRepository.findAll();
  }

  @Override
  public Patron getPatronById(Long id) {
    Optional<Patron> optionalPatron = patronRepository.findById(id);
    return optionalPatron.orElseThrow(() -> new PatronNotFoundEXception("Patron with Id " + id + " Not found"));
  }

  @Override
  @Cacheable("getPatron")
  public Patron addPatron(Patron patron) {
    return patronRepository.save(patron);
  }

  @Transactional
  @Override
  @CacheEvict(value = "getPatron", key = "#id")
  public Patron updatePatron(Long id, Patron patron) {
    Optional<Patron> optionalPatron = patronRepository.findById(id);
    if (optionalPatron.isPresent()) {
      patron.setId(id);
      return patronRepository.save(patron);
    }
    throw new PatronNotFoundEXception("Patron with ID " + id + " not found");
  }

  @Transactional
  @Override
  public void deletePatron(Long id) {
    patronRepository.deleteById(id);

  }

}
