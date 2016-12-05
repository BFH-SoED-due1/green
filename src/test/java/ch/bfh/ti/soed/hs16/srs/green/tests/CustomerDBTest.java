/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.db.CustomerDB;
import ch.bfh.ti.soed.hs16.srs.green.model.Customer;

public class CustomerDBTest {

	@Test
	public void testConstructor() throws Exception {

		CustomerDB cdb = new CustomerDB();
		assertNotNull(cdb);

	}

	@Test
	public void addCustomerTest() throws Exception {

		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		Statement stmt = c.createStatement();
		stmt.executeUpdate("delete from customer;");
		stmt.close();
		c.close();
		
		CustomerDB.registerCustomer("MarcoM", "testPW");
		Set<Customer> customer = CustomerDB.getCustomers();
		assertNotNull(customer);

	}

	@Test
	public void registerCustomerTest() throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection co = DriverManager.getConnection("jdbc:sqlite:srs.db");
		Statement stmt = co.createStatement();
		stmt.executeUpdate("delete from customer;");
		stmt.close();
		co.close();

		CustomerDB.registerCustomer("MarcoM2", "testPW");
		Set<Customer> customer = CustomerDB.getCustomers();
		Set<Customer> customerToCheck = new HashSet<>();
		for (Customer c : customer)
			if (c.getUserName().equals("MarcoM2"))
				customerToCheck.add(c);

		assertEquals(customerToCheck.iterator().next().getUserName(), "MarcoM2");

	}

}
