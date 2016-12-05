/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.controller.LoginManager;
import ch.bfh.ti.soed.hs16.srs.green.controller.ReservationService;

public class ServiceTest {

	@Test
	public void testConstructorsLoginManager() {
		LoginManager sa = new LoginManager();
		assertNotNull(sa);

	}

	@Test
	public void testConstructorsReservationService() {

		ReservationService rm = new ReservationService();
		assertNotNull(rm);

	}
}
