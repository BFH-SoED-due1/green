/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

///**
// * Class Customer which represent the client of the srs-tool.
// * @author team-green
// */
public class Customer {

	private final Role rights;
	private String userName, pw;

//	/**
//	 * Constructor of Customer, needs a user name, a password as well as a role
//	 * to initialize the Customer himself.
//	 * @param userName
//	 * @param pw
//	 * @param role
//	 */
	public Customer(String userName, String pw, Role role) {
		this.userName = userName;
		this.pw = pw;
		this.rights = role;
	}

//	/**
//	 * Method checks if the given password is correct with the actually password
//	 * of the customer. 
//	 * @param pwToCheck
//	 * @return
//	 */
	public boolean checkPW(String pwToCheck) {
		return pw.equals(pwToCheck);
	}

//	/**
//	 * Method returns the user name of this customer.
//	 * @return
//	 */
	public String getUserName() {
		return userName;
	}

//	/**
//	 * Method sets the user name of this customer.
//	 * @param userName
//	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

//	/**
//	 * Method returns the Role of this Customer.
//	 * @return
//	 */
	public Role getRole() {
		return rights;
	}

}
