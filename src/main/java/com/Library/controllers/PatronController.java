package com.Library.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Library.IServices.IPatronService;
import com.Library.models.Patron;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;



@RestController
@RequestMapping(path = "api/v1/patron")
public class PatronController {
  
  @Autowired
  private IPatronService patronService;
  
  @GetMapping
  public List<Patron> getPatrons() {
    return patronService.getAllPatrons();
    
  }
    @GetMapping("/{id}")
  public ResponseEntity<Patron> getPatronById(@PathVariable("id") @NotNull  Long id) {
    Patron patron = patronService.getPatronById(id);
    return ResponseEntity.ok(patron);
  }

  @PostMapping
  public ResponseEntity<?> addPatron(@RequestBody @Valid Patron patron,BindingResult bindingResult) {
if (bindingResult.hasErrors()) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }
    Patron addedPatron = patronService.addPatron(patron);
    return ResponseEntity.status(HttpStatus.CREATED).body(addedPatron);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Patron> updatePatron(@PathVariable("id") @NotNull Long id, @RequestBody @Valid Patron patron) {
    Patron updatedPatron = patronService.updatePatron(id, patron);
    return ResponseEntity.ok(updatedPatron);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePatron(@PathVariable("id") @NotNull Long id) {
    patronService.deletePatron(id);
    return ResponseEntity.noContent().build();
  }

}
