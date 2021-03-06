/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.db.CustomerDB;
import ch.bfh.ti.soed.hs16.srs.green.db.DBConnector;
import ch.bfh.ti.soed.hs16.srs.green.db.ReservationDB;
import ch.bfh.ti.soed.hs16.srs.green.db.ResourceDB;
import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.green.model.Resource;
import ch.bfh.ti.soed.hs16.srs.green.model.Role;

public class ReservationDBTest {
	@Test
	public void testConstructor() {
		ReservationDB rdb = new ReservationDB();
		assertNotNull(rdb);
	}

	@Test
	public void addReservationTest() throws Throwable {

		DBConnector.connectDB();
		DBConnector.delteContentOfTabels();
		DBConnector.disconnectDB();

		CustomerDB.registerCustomer("SvenTest", "testpw", Role.CUSTOMER);
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);
		ReservationDB.addReservation(LocalDateTime.of(2018, 12, 12, 12, 30), LocalDateTime.of(2018, 12, 12, 14, 00),
				new Resource("RoomTest2", 44, "RoomLocationTest2"), new Customer("SvenTest", "testpw", Role.CUSTOMER));

	}

	@Test
	public void getReservMadeByCustomerTest() throws Throwable {

		DBConnector.connectDB();
		DBConnector.delteContentOfTabels();
		DBConnector.disconnectDB();

		CustomerDB.registerCustomer("SvenTest", "testpw", Role.CUSTOMER);
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);

		Customer u1 = new Customer("SvenTest", "testpw", Role.CUSTOMER);

		ReservationDB.addReservation(LocalDateTime.of(2018, 12, 12, 12, 30), LocalDateTime.of(2018, 12, 12, 14, 00),
				new Resource("RoomTest2", 44, "RoomLocationTest2"), u1);

		Set<Reservation> sets = ReservationDB.getReservationMadeByCustomer(u1);
		assertNotNull(sets);

	}

	@Test
	public void getAllReservations() throws Throwable {

		DBConnector.connectDB();
		DBConnector.delteContentOfTabels();
		DBConnector.disconnectDB();

		CustomerDB.registerCustomer("SvenTest", "testpw", Role.CUSTOMER);
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);

		Customer u1 = new Customer("SvenTest", "testpw", Role.CUSTOMER);

		ReservationDB.addReservation(LocalDateTime.of(2018, 12, 12, 12, 30), LocalDateTime.of(2018, 12, 12, 14, 00),
				new Resource("RoomTest2", 44, "RoomLocationTest2"), u1);

		Set<Reservation> sets = ReservationDB.getReservations();

		assertNotNull(sets);

	}
}
