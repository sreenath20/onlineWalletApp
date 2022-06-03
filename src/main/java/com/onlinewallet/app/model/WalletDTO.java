package com.onlinewallet.app.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class WalletDTO {
	
	@NotBlank(message = "Name can't be Blank.")
	@Size(min=3,max=25,message ="UserName should be min of 3 chars & max of 25 chars.")
	@Pattern(regexp = "[a-zA-Z0-9]+",message = "Username should be Alphanumeric, No space allowed." )
	String name;
	@Min(value = 1000,message = "Min balance should be 1000.")
	@Max(value = 2000,message = "Max balance should be 2000.")
	
	@NotNull(message = "Balance cant be NULL.")
	Double balance;
	@Email(message = "Please enter valid Email eg: yourname@ipru.com.")
	String email;	
	@Pattern(regexp = "[a-zA-Z0-9]{8,}",message = "At least 8 characters,only uppercase lowercase and digits allowed.")
	String password;
	@Pattern(regexp = "[0-5]{1}",message = "At least 1 digit must be present between 0 to 5.")
	String rating;
	
	Double price;
	
	public WalletDTO(
			@NotBlank(message = "Name can't be Blank.") @Size(min = 3, max = 25, message = "UserName should be min of 3 chars & max of 25 chars.") @Pattern(regexp = "[a-zA-Z0-9]+", message = "Username should be Alphanumeric, No space allowed.") String name,
			@Min(value = 1000, message = "Min balance should be 1000.") @Max(value = 2000, message = "Max balance should be 2000.") @NotNull(message = "Balance cant be NULL.") Double balance,
			@Email(message = "Please enter valid Email eg: yourname@ipru.com.") String email,
			@Pattern(regexp = "[a-zA-Z0-9]{8,}", message = "At least 8 characters,only uppercase lowercase and digits allowed.") String password,
			@Pattern(regexp = "[0-5]{1}", message = "At least 1 digit must be present between 0 to 5.") String rating,
			Double price) {
		super();
		this.name = name;
		this.balance = balance;
		this.email = email;
		this.password = password;
		this.rating = rating;
		this.price = price;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public WalletDTO() {
		super();
	}
	public WalletDTO(String name, Double balance, String email, String password) {
		super();
		this.name = name;
		this.balance = balance;
		this.email = email;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
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
