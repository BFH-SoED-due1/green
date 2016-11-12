/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package ch.bfh.ti.soed.hs16.srs.green.model;

/**
 *
 * @author Vanessa
 *
 */
public class Customer {

	private String userName, email, pw;
	//private ReservationService rs;

	/**
	 *
	 * @param userName
	 * @param email
	 * @param pw
	 */
	public Customer(String userName, String email, String pw) {
		this.userName = userName;
		this.email = email;
		this.pw = pw;
	}

	public boolean checkPW(String pw) {
		return this.pw == pw;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}
