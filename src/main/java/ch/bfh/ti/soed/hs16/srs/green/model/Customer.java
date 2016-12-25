/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

public class Customer {

	private final Role rights;
	private String userName, pw;

	public Customer(String userName, String pw, Role role) {
		this.userName = userName;
		this.pw = pw;
		this.rights=role;
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

	public Role getRole() {
		return rights;
	}

}
