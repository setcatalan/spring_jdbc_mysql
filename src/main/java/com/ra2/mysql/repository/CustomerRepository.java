package com.ra2.mysql.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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
			customer.setName(rs.getString("name"));
			return customer;
		}
	}
	
	public void initDB() {
		jdbcTemp.update("INSERT INTO customers ( ) VALUES()");
		jdbcTemp.update("INSERT INTO customers ( ) VALUES()");
		jdbcTemp.update("INSERT INTO customers ( ) VALUES()");
	}
}
