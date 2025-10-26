package com.ra2.mysql.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ra2.mysql.model.Customer;

@Repository
public class CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemp;
	
	private static final class CustomerRowMapper implements RowMapper<Customer>{
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setId(rs.getLong("id"));
			customer.setName(rs.getString("cust_name"));
			customer.setDescription(rs.getString("cust_description"));
			customer.setAge(rs.getInt("age"));
			customer.setCourse(rs.getString("course"));
			customer.setDataCreated(rs.getTimestamp("dataCreated"));
			customer.setDataUpdated(rs.getTimestamp("dataUpdated"));
			return customer;
		}
	}
	
	public void initDB() {
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Jhon Doe", "Profesor", 54, "DAM");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Jane Smith", "Profesor", 35, "DAW");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Bob Jonhson", "Profesor", 42, "ASIX");
	}
	
	public void createCust() {
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Set Catalan", "Estudiant", 24, "DAM");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Christian Lloveras", "Estudiant", 25, "DAW");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Aaron Talledo", "Estudiant", 22, "ASIX");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Javier Crespo", "Estudiant", 20, "DAM");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Alex Dueñas", "Estudiant", 23, "DAW");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Miriam Ribolleda", "Estudiant", 26, "ASIX");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Jose Romero", "Estudiant", 28, "DAM");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Adrian Garcia", "Estudiant", 32, "DAW");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Benjamin Herrero", "Estudiant", 22, "ASIX");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course) VALUES(?, ?, ?, ?)", "Juan Alberto", "Estudiant", 24, "DAM");
	}
	
	public List<Customer> findAll(){
		return jdbcTemp.query("SELECT id, cust_name, cust_description, age, course, dataCreated, dataUpdated FROM customers", new CustomerRowMapper());
	}
	
	public Customer findById(int id) {
		return jdbcTemp.queryForObject("SELECT id, cust_name, cust_description, age, course, dataCreated, dataUpdated FROM customers WHERE id=?", new Object[]{id}, new CustomerRowMapper());
	}
	
	public void replaceCust(int id) {
		jdbcTemp.update("UPDATE customers SET cust_name = ?, cust_description = ?, age = ?, course = ? WHERE id = ?", "Actualitzat", "Actualizat", 0, "Actualtzat", id);
	}
	
	public void updateCust(int id) {
		jdbcTemp.update("UPDATE customers SET cust_name = ?, age = ? WHERE id = ?", "Nom", 0, id);
	}
	
	public void deleteCust(int id) {
		jdbcTemp.update("DELETE FROM customers WHERE id=?", id);
	}
}