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
			customer.setPassword(rs.getString("passwd"));
			customer.setDataCreated(rs.getTimestamp("dataCreated"));
			customer.setDataUpdated(rs.getTimestamp("dataUpdated"));
			return customer;
		}
	}
	
	public void initDB() {
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course, passwd) VALUES(?, ?, ?, ?, ?)", "Jhon Doe", "Profesor", 54, "DAM", "12346789");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course, passwd) VALUES(?, ?, ?, ?, ?)", "Jane Smith", "Profesor", 35, "DAW", "abcdwxyz");
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course, passwd) VALUES(?, ?, ?, ?, ?)", "Bob Jonhson", "Profesor", 42, "ASIX", "1a2b8y9z");
	}
	
	public void createCust(Customer cust) {
		jdbcTemp.update("INSERT INTO customers (cust_name, cust_description, age, course, passwd) VALUES(?, ?, ?, ?, ?)", 
						cust.getName(), cust.getDescription(), cust.getAge(), cust.getCourse(), cust.getPassword());
	}
	
	public List<Customer> findAll(){
		return jdbcTemp.query("SELECT * FROM customers", new CustomerRowMapper());
	}
	
	public Customer findById(int id) {
		return jdbcTemp.queryForObject("SELECT * FROM customers WHERE id=?", new Object[]{id}, new CustomerRowMapper());
	}
	
	public void replaceCust(int id, Customer newCust) {
		jdbcTemp.update("UPDATE customers SET cust_name = ?, cust_description = ?, age = ?, course = ?, passwd = ? WHERE id = ?", 
						newCust.getName(), newCust.getDescription(), newCust.getAge() , newCust.getCourse(), newCust.getPassword(), id);
	}
	
	public void updateCust(int id, int age) {
		jdbcTemp.update("UPDATE customers SET age = ? WHERE id = ?", age, id);
	}
	
	public void deleteCust(int id) {
		jdbcTemp.update("DELETE FROM customers WHERE id=?", id);
	}
}