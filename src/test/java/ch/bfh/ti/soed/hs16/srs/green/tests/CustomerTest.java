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
import ch.bfh.ti.soed.hs16.srs.green.model.Role;

public class CustomerTest {

	@Test
	public void checkFalsePwTest() {
		Customer u1 = new Customer("SvenS", "12AB",Role.CUSTOMER);
		String pw = "12345";
		assertFalse(u1.checkPW(pw));
	}
	@Test
	public void getARole() {
		Customer u1 = new Customer("SvenS", "12AB",Role.CUSTOMER);
		Role x = u1.getRole();
		assertNotNull(x);
	}

	@Test
	public void checkRhightPwTest() {
		Customer u1 = new Customer("SvenS", "12AB",Role.CUSTOMER);
		String pw = "12AB";
		assertTrue(u1.checkPW(pw));
	}

	@Test
	public void createCustumerTest() {
		Customer u1 = new Customer("SvenS", "12AB",Role.CUSTOMER);
		assertNotNull(u1);
	}

	@Test
	public void setUserNameTest() {
		Customer u1 = new Customer("SvenS", "12AB",Role.CUSTOMER);
		u1.setUserName("SvenNeuPre");
		assertEquals(u1.getUserName(), "SvenNeuPre");
	}

}
