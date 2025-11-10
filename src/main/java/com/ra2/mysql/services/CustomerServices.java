package com.ra2.mysql.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
				result += "Nom massa curt%n";
				continue;
			}
			
			if (customer.getAge() <= 0) {
				result += "L'edat ha de ser positiva%n";
				continue;
			} else if (customer.getAge() >= 100) {
				result += "És massa major%n";
				continue;
			}
			
			switch (customer.getCourse()) {
				case "DAM":
				case "DAW": 
				case "ASIX":
					break;
				default:
					result += "El curs ha de ser DAM, DAW o ASIX%n";
					continue;
			}
			
			customerRep.createCust(customer);
			result += "Customer " + customer.getName() + " afegit correctament%n";
		}
		return String.format(result);
	}

	public List<Customer> findAll() {
		
		
		return customerRep.findAll();
	}

	public Customer findById(Long id) {
		Customer cust = null;
		List<Customer> customers = customerRep.findAll();
		for (Customer customer: customers) {
			if (customer.getId() == id) {
				cust = customer;
				break;
			}
		}
		return cust;
	}

	public void replaceCust(Long id, Customer newCust) {

		
		customerRep.replaceCust(id, newCust);
	}

	public void updateCust(Long id, int age) {


		customerRep.updateCust(id, age);
	}

	public void deleteCust(Long id) {


		customerRep.deleteCust(id);
	}

	public String saveCustomerImage(Long id, MultipartFile image) {
		Path pathDirectori = Paths.get("src/main/resources/public/image");
		Path pathFile = Paths.get(image.getOriginalFilename() + "/generateUniqueFileName()");
		
		if (findById(id) == null) {
			return "Customer " + id + "no existeix";
		}
		
		try {
			Files.createDirectories(pathDirectori);
			Files.createFile(pathFile);
			String image_path = pathFile.resolve(pathDirectori).toString();
			Files.copy(pathFile, pathDirectori);
			customerRep.saveCustomerImage(id, image_path);
			return image_path;
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}
	}

}
