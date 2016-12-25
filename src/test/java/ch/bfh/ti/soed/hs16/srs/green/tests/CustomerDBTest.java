/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.db.CustomerDB;
import ch.bfh.ti.soed.hs16.srs.green.db.DBConnector;
import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Role;

public class CustomerDBTest {

	@Test
	public void testConstructor() throws Throwable {

		CustomerDB cdb = new CustomerDB();
		assertNotNull(cdb);

	}

	@Test
	public void addCustomerTest() throws Throwable {

		DBConnector.connectDB();
		DBConnector.delteContentOfTabels();
		DBConnector.disconnectDB();

		CustomerDB.registerCustomer("MarcoM", "testPW", Role.CUSTOMER);
		Set<Customer> customer = CustomerDB.getCustomers();
		assertNotNull(customer);

	}

	@Test
	public void registerCustomerTest() throws Throwable {
		DBConnector.connectDB();
		DBConnector.delteContentOfTabels();
		DBConnector.disconnectDB();

		CustomerDB.registerCustomer("MarcoM2", "testPW", Role.CUSTOMER);
		Set<Customer> customer = CustomerDB.getCustomers();
		Set<Customer> customerToCheck = new HashSet<>();
		for (Customer c : customer)
			if (c.getUserName().equals("MarcoM2"))
				customerToCheck.add(c);

		assertEquals(customerToCheck.iterator().next().getUserName(), "MarcoM2");

	}

}
