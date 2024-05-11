package com.Library;

import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.Library.IServices.IPatronService;
import com.Library.controllers.PatronController;
import com.Library.models.Patron;
@SpringBootTest
public class PatronTestController {
     @Mock
    private IPatronService patronService;

    @InjectMocks
    private PatronController patronController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPatrons() {
        // Mock service method to return a list of patrons
        List<Patron> patrons = new ArrayList<>();
        patrons.add(new Patron());
        patrons.add(new Patron());
        when(patronService.getAllPatrons()).thenReturn(patrons);

        // Call the controller method
        List<Patron> result = patronController.getPatrons();

        // Verify the response
        assertEquals(patrons.size(), result.size());
    }

    @Test
    public void testGetPatronById() {
        // Mock service method to return a patron
        Long id = 1L;
        Patron patron = new Patron();
        patron.setId(id);
        when(patronService.getPatronById(id)).thenReturn(patron);

        // Call the controller method
        ResponseEntity<Patron> response = patronController.getPatronById(id);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testAddPatron_Success() {
        // Mock service method to return the added patron
        Patron patronToAdd = new Patron();
        when(patronService.addPatron(patronToAdd)).thenReturn(patronToAdd);

        // Call the controller method
        ResponseEntity<?> response = patronController.addPatron(patronToAdd, mock(BindingResult.class));

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdatePatron_Success() {
        // Mock service method to return the updated patron
        Long id = 1L;
        Patron updatedPatron = new Patron();
        updatedPatron.setId(id);
        when(patronService.updatePatron(id, updatedPatron)).thenReturn(updatedPatron);

        // Call the controller method
        ResponseEntity<Patron> response = patronController.updatePatron(id, updatedPatron);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testDeletePatron_Success() {
        // Call the controller method
        ResponseEntity<Void> response = patronController.deletePatron(1L);

        // Verify the response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
