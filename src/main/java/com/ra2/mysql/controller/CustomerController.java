package com.ra2.mysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ra2.mysql.model.Customer;
import com.ra2.mysql.services.CustomerServices;

@RestController
@RequestMapping("/jdbctemplate")
public class CustomerController {

	@Autowired
	private CustomerServices customerServ;
	
	/*@PostMapping("/initdataDB")
	public String initdataDB() {
		customerRep.createTableDB();
		customerRep.initDB();
		return "Taula emplenada correctament";
	}*/
	
	@PostMapping("/customer")
	public ResponseEntity<String> createCustomer(@RequestBody List<Customer> customers) {
		return ResponseEntity.status(HttpStatus.CREATED).body(customerServ.createCustomers(customers));
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> findAllCust(){
		List<Customer> customers = null;
		customers = customerServ.findAll();
		return ResponseEntity.ok(customers);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		return ResponseEntity.ok(customerServ.findById(id));
	}
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> replaceCust(@PathVariable Long id, @RequestBody Customer newCust) {
		customerServ.replaceCust(id, newCust);
		return ResponseEntity.ok(customerServ.findById(id));
	}
	
	@PatchMapping("/customer/{id}/age")
	public ResponseEntity<Customer> updateCust(@PathVariable Long id, @RequestParam int age) {
		customerServ.updateCust(id, age);
		return ResponseEntity.ok(customerServ.findById(id));
	}
	
	@DeleteMapping("/customer/{id}")
	public String deleteCust(@PathVariable Long id) {
		customerServ.deleteCust(id);
		return "Customer eliminat correctament";
	}
	
	@PostMapping("/customer/{id}/image")
	public ResponseEntity<String> saveCustomerImage(@PathVariable Long id, @RequestParam MultipartFile image){
		return ResponseEntity.ok(customerServ.saveCustomerImage(id, image));
	}
}
