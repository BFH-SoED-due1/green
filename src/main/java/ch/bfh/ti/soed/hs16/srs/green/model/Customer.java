/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

public class Customer {

	private String name, email, pw;

	// private ReservationService rs;

	public Customer(String name, String email, String pw) {
		this.name = name;
		this.email = email;
		this.pw = pw;
	}

	public boolean checkPW(String pwToCheck) {
		return pw == pwToCheck;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String userName) {
		name = userName;
	}
	
	public String getPW(){
		return pw;
	}

}
