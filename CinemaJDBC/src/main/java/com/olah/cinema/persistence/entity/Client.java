package com.olah.cinema.persistence.entity;

public class Client {
	private int id;
	private String fullname;
	private String password;
	private String phoneNumber;

	public Client(int id, String fullname, String password, String phoneNumber) {
		this.id = id;
		this.fullname = fullname;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public Client(String fullname, String password, String phoneNumber) {
		this.fullname = fullname;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Client{" +
		    "id=" + id +
		    ", fullname='" + fullname + '\'' +
		    ", password='" + password + '\'' +
		    ", phoneNumber='" + phoneNumber + '\'' +
		    '}';
	}
}
