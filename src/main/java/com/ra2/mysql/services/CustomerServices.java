package com.ra2.mysql.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ra2.mysql.model.Customer;
import com.ra2.mysql.repository.CustomerRepository;

@Service
public class CustomerServices {
	
	@Autowired
	private CustomerRepository customerRep;

	public String createCustomers(List<Customer> customers) {
		String result = "";
		int posicio = 1;
		for (Customer customer: customers) {
			result += "Customer " + posicio + "%n";
			posicio++;
			
			if (customer.getName().length() <= 2) {
				result += "Nom massa curt";
				continue;
			}
			
			if (customer.getAge() <= 0) {
				result += "L'edat ha de ser positiva";
				continue;
			} else if (customer.getAge() >= 100) {
				result += "És massa major";
			}
			
			switch (customer.getCourse()) {
				case "DAM":
				case "DAW": 
				case "ASIX":
					break;
				default:
					result += "El curs ha de ser DAM, DAW o ASIX";
			}
			
			customerRep.createCust(customer);
			result += "Customer " + customer.getName() + " afegit correctament%n";
		}
		return String.format(result);
	}

	public List<Customer> findAll() {
		
		
		return customerRep.findAll();
	}

	public Customer findById(int id) {


		return customerRep.findById(id);
	}

	public void replaceCust(int id, Customer newCust) {

		
		customerRep.replaceCust(id, newCust);
	}

	public void updateCust(int id, int age) {


		customerRep.updateCust(id, age);
	}

	public void deleteCust(int id) {


		customerRep.deleteCust(id);
	}

}
