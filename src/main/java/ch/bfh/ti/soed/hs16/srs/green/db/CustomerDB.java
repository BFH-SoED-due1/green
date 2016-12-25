/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.db;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Role;

/**
 * A class which represents the customer table in the srs.db. Class is only used
 * by the class MyUIControllers.
 * 
 * @author team-green
 * @version 1.4, 18.12.16
 */
public class CustomerDB extends DBConnector {

	/**
	 * A Method which adds a customer to the table customer in the srs.db
	 * 
	 * @param userName
	 *            takes the username from the UI-TextField username.
	 * @param pw
	 *            takes the password from the UI-TextField password.
	 * @throws Exception
	 */
	public static void registerCustomer(String userName, String pw, Role x) throws Throwable {

		connectDB();
		String sql = "INSERT INTO Customer (USERNAME,PW,RIGHTS) " + "VALUES ('" + userName + "', '" + pw + "', '"
				+ x.toString() + "');";
		c.createStatement().executeUpdate(sql);
		disconnectDB();
	}

	/**
	 * Returns all customers in the customer table of srs.db.
	 * 
	 * @return a set of all customers.
	 * @throws Exception
	 * @see Customer
	 */
	public static Set<Customer> getCustomers() throws Throwable {

		Set<Customer> customers = new HashSet<>();
		connectDB();
		ResultSet rs = c.createStatement().executeQuery("SELECT USERNAME, PW, RIGHTS FROM CUSTOMER;");

		while (rs.next()) {
			String userName = rs.getString("username");
			String pw = rs.getString("pw");
			customers.add(new Customer(userName, pw, Role.valueOf(rs.getString("rights"))));
		}
		rs.close();
		disconnectDB();
		return customers;
	}

}
