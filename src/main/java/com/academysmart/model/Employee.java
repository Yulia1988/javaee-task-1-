package com.academysmart.model;

public class Employee {

	private String name;
	private String surname;
	private String email;
	private String id;

	
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
			return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}