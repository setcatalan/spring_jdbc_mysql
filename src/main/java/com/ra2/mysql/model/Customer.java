package com.ra2.mysql.model;

import java.sql.Timestamp;

public class Customer {
	private Long id;
	private String name, description;
	private int age;
	private String course;
	private Timestamp dataCreated;
	private Timestamp dataUpdated;
	
	public Customer() {
		
	}

	public Customer(Long id, String name, String description, int age, String course, Timestamp dataCreated, Timestamp dataUpdated) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.age = age;
		this.course = course;
		this.dataCreated = dataCreated;
		this.dataUpdated = dataUpdated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Timestamp getDataCreated() {
		return dataCreated;
	}

	public void setDataCreated(Timestamp dataCreated) {
		this.dataCreated = dataCreated;
	}

	public Timestamp getDataUpdated() {
		return dataUpdated;
	}

	public void setDataUpdated(Timestamp dataUpdated) {
		this.dataUpdated = dataUpdated;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + 
				", name=" + name + 
				", description=" + description +
				", age=" + age + 
				", course=" + course + 
				", dataCreated=" + dataCreated + 
				", dataUpdated=" + dataUpdated + "]";
	}
}
