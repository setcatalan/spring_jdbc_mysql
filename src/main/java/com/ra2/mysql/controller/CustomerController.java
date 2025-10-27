package com.ra2.mysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra2.mysql.model.Customer;
import com.ra2.mysql.repository.CustomerRepository;

@RestController
@RequestMapping("/jdbctemplate")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRep;
	
	@PostMapping("/initdataDB")
	public String initdataDB() {
		/*customerRep.createTableDB();*/
		customerRep.initDB();
		return "Taula emplenada correctament";
	}
	
	@PostMapping("/customer")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
		customerRep.createCust(customer);
		return ResponseEntity.ok("Customer  creat: " + customer.getName());
	}
	
	@GetMapping("/findAllCustomers")
	public List<Customer> findAllCust(){
		return customerRep.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public Customer findById(@PathVariable int id) {
		return customerRep.findById(id);
	}
	
	@PutMapping("/replaceCust/{id}")
	public Customer replaceCust(@PathVariable int id) {
		customerRep.replaceCust(id);
		return customerRep.findById(id);
	}
	
	@PatchMapping("/updateCust/{id}")
	public Customer updateCust(@PathVariable int id) {
		customerRep.updateCust(id);
		return customerRep.findById(id);
	}
	
	@DeleteMapping("/deleteCust/{id}")
	public String deleteCust(@PathVariable int id) {
		customerRep.deleteCust(id);
		return "Customer eliminat correctament";
	}
}
