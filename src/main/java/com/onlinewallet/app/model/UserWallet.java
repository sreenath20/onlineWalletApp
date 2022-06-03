package com.onlinewallet.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document
public class UserWallet {
	@Id
	String id;
	String name;
	Double balance;
	String email;
	@JsonIgnore
	String password;
	
	
	public UserWallet() {
		super();
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public UserWallet(String id, String name, Double balance, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.email = email;
		this.password = password;
	}
	
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	

}
