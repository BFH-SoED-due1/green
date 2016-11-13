/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.model.Customer;


public class CustomerTest {

	@Test
	public void changeCustumerMailTest() {
		Customer u1 = new Customer("Marco", "sven@email.ch", "12AB");
		String mail = "marco@eamil.ch";
		u1.setEmail(mail);;
		assertEquals(mail, u1.getEmail());
	}

	@Test
	public void changeCustumerNameTest() {
		Customer u1 = new Customer("Sven", "sven@email.ch", "12AB");
		String name = "Marco";
		u1.setName(name);
		assertEquals(name, u1.getName());
	}

	@Test
	public void checkFalsePwTest() {
		Customer u1 = new Customer("Sven", "sven@email.ch", "12AB");
		String pw = "12345";
		assertFalse(u1.checkPW(pw));
	}

	@Test
	public void checkRhightPwTest() {
		Customer u1 = new Customer("Sven", "sven@email.ch", "12AB");
		String pw = "12AB";
		assertTrue(u1.checkPW(pw));
	}


	@Test
	public void createCustumerTest() {
		Customer u1 = new Customer("Sven", "sven@email.ch", "12AB");
		assertNotNull(u1);
	}

}
