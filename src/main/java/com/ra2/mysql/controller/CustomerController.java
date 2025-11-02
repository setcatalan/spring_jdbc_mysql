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
	public ResponseEntity<String> createCustomer(@RequestBody List<Customer> customers) {
		for(Customer customer: customers) {
			customerRep.createCust(customer);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Customers creats correctament");
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> findAllCust(){
		List<Customer> customers = null;
		customers = customerRep.findAll();
		return ResponseEntity.ok(customers);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> findById(@PathVariable int id) {
		Customer cust = null;
		List<Customer> customers = customerRep.findAll();
		Long idCust = (long) 0;
		for (Customer customer: customers) {
			if (customer.getId() < idCust) {
				continue;
			}
			idCust = customer.getId();
		}
		if (id <= idCust && id > 0) {
			cust = customerRep.findById(id);
		}
		return ResponseEntity.ok(cust);
	}
	
	@PutMapping("/customer/{id}")
	public ResponseEntity<Customer> replaceCust(@PathVariable int id, @RequestBody Customer newCust) {
		customerRep.replaceCust(id, newCust);
		return ResponseEntity.ok(customerRep.findById(id));
	}
	
	@PatchMapping("/customer/{id}/age")
	public ResponseEntity<Customer> updateCust(@PathVariable int id, @RequestParam int age) {
		customerRep.updateCust(id, age);
		return ResponseEntity.ok(customerRep.findById(id));
	}
	
	@DeleteMapping("/customer/{id}")
	public String deleteCust(@PathVariable int id) {
		customerRep.deleteCust(id);
		return "Customer eliminat correctament";
	}
}
