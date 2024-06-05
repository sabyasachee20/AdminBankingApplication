package com.example.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.model.Customer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AdminController adminController;

    @Test
    void testAddUser() {
        // Sample customer data
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("John");

        // Mock the behavior of postForEntity
        when(restTemplate.postForEntity(any(String.class), any(Customer.class), eq(Void.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        // Call the addUser method
        ResponseEntity<?> responseEntity = adminController.addUser(customer);

        // Verify that postForEntity is called with the correct URL and customer object
        verify(restTemplate).postForEntity("http://localhost:9999/cust/add", customer, Void.class);

        // Verify that the response is created
        assert responseEntity.getStatusCode().equals(HttpStatus.CREATED);
    }

    @Test
    void testDeleteUser() {
        int id = 1;

        // Call the deleteUser method
        ResponseEntity<?> responseEntity = adminController.deleteUser(id);

        // Verify that delete is called with the correct URL
        verify(restTemplate).delete("http://localhost:9999/cust/delete/" + id);

        // Verify that the response is OK
        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
    }
}
