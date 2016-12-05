/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.soed.hs16.srs.green.model.Customer;

public class CustomerDB {

	private static Connection c = null;
	private static Statement stmt = null;

	public static void registerCustomer(String userName, String pw) throws Exception {

		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:srs.db");

		String sql = "INSERT INTO Customer (USERNAME,PW) " + "VALUES ('" + userName + "', '" + pw + "');";

		c.createStatement().executeUpdate(sql);

		c.close();
	}

	public static Set<Customer> getCustomers() throws Exception {

		c = null;
		stmt = null;
		Set<Customer> customers = new HashSet<>();

		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		c.setAutoCommit(false);
		stmt = c.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT USERNAME, PW FROM CUSTOMER;");

		while (rs.next()) {
			String userName = rs.getString("username");

			String pw = rs.getString("pw");
			customers.add(new Customer(userName, pw));
		}

		rs.close();
		stmt.close();
		c.close();

		return customers;
	}

}
