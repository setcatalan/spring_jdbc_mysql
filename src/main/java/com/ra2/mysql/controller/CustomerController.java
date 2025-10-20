package com.ra2.mysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		customerRep.initDB();
		return "Taula emplenada correctament";
	}
	
	@GetMapping("/findAllCustomers")
	public List<Customer> findAllCust(){
		return customerRep.findAll();
	}
}
