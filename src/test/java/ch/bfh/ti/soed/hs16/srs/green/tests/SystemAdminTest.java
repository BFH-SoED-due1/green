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

import ch.bfh.ti.soed.hs16.srs.green.model.ResourceManager;
import ch.bfh.ti.soed.hs16.srs.green.model.SystemAdmin;

public class SystemAdminTest {

	/*
	 * @Test public void createResourceManagerFromCustomerTest(){ SystemAdmin ad
	 * = new SystemAdmin("Sven", "sven@email.ch", "12AB"); Customer cu = new
	 * Customer("Marco", "marco@email.ch", "12AB"); ResourceManager rm =
	 * ad.createRescourceManager(cu); System.out.println(rm.getName() +
	 * rm.getEmail()); assertNotNull(rm); }
	 */

	@Test
	public void createResourceManagerTest() {
		SystemAdmin ad = new SystemAdmin("SvenS", "Sven", "Schön", "sven@email.ch", "12AB");
		ResourceManager rm = ad.createRescourceManager("MarcoM", "Marco", "Galatioto", "marco@email.ch", "12AB");
		assertNotNull(rm);
	}

	@Test
	public void createSystemAdminTest() {
		SystemAdmin ad = new SystemAdmin("SvenS", "Sven", "Schön", "sven@email.ch", "12AB");
		assertNotNull(ad);
	}
}
