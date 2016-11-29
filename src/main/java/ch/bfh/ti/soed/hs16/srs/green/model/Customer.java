/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

public class Customer {

	private String userName, prename, lastname, email, pw;

	// private ReservationService rs;

	public Customer(String userName, String prename, String lastname, String email, String pw) {
		this.userName = userName;
		this.prename = prename;
		this.lastname = lastname;
		this.email = email;
		this.pw = pw;
	}

	public boolean checkPW(String pwToCheck) {
		return pw == pwToCheck;
	}

	public String getEmail() {
		return email;
	}

	public String getLastname() {
		return lastname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLastname(String lastName) {
		lastname = lastName;
	}

	public String getPW() {
		return pw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPrename() {
		return prename;
	}

	public void setPrename(String prename) {
		this.prename = prename;
	}

}
