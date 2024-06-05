package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.model.Customer;
import com.example.repository.AdminRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {
	 @Autowired
	    RestTemplate restTemplate;
	 @Autowired
	 AdminRepository ad;

	    private final String CUSTOMER_SERVICE_URL = "http://localhost:9999/cust";

	    @PostMapping("/addUser")
	    public ResponseEntity<?> addUser(@RequestBody Customer customer) {
	        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(CUSTOMER_SERVICE_URL + "/add", customer, Void.class);
	        return ResponseEntity.status(responseEntity.getStatusCode()).build();
	    }

	   @DeleteMapping("/deleteUser/{id}")
	    public ResponseEntity<?> deleteUser(@PathVariable int id) {
	        restTemplate.delete(CUSTOMER_SERVICE_URL + "/delete/" + id);
	        return ResponseEntity.ok().build();
	    }

	   /* @GetMapping("/readUsers")
	    public ResponseEntity<?> readUsers() {
	        ResponseEntity<Customer[]> responseEntity = restTemplate.getForEntity(CUSTOMER_SERVICE_URL + "/read", Customer[].class);
	        return ResponseEntity.ok(responseEntity.getBody());
	    }*/

}
