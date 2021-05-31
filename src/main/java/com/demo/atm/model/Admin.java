package com.demo.atm.model;

import java.io.Serializable;

public class Admin implements Serializable{
	
	private String name;

	private String password;
	
	private int amount;
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
