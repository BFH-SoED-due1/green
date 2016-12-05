/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

public class Customer {

	private String userName, pw;

	public Customer(String userName, String pw) {
		this.userName = userName;
		this.pw = pw;
	}

	public boolean checkPW(String pwToCheck) {
		return pw.equals(pwToCheck);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
