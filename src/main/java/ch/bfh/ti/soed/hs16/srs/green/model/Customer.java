/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

/**
 * A model class which instances represent customers
 * @author team green
 *
 */
public class Customer {

	private final Role rights;
	private String userName, pw;

	/**
	 * Constructor of the class
	 * @param userName the username of the customer, should not be null or empty
	 * @param pw the password of the customer, should not be null or empty
	 * @param role the role of the customer (customer or room manager), should not be null or empty
	 */
	public Customer(String userName, String pw, Role role) {
		this.userName = userName;
		this.pw = pw;
		rights=role;
	}

	/**
	 * checkPW method to check if the pwToCheck is the same as the pw of this object
	 * @param pwToCheck given pw
	 * @return true if pwToCheck is the same
	 */
	public boolean checkPW(String pwToCheck) {
		return pw.equals(pwToCheck);
	}

	/**
	 * getUsername method
	 * @return userName
	 * 	name of the customer
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * setUsername method
	 * @param userName the new userName, should not be null or empty
	 * this method sets the new userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * getRole method
	 * @return rights
	 * 		role of the customer (customer, resource manager, admin)
	 */
	public Role getRole() {
		return rights;
	}
}
