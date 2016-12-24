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
import java.time.LocalDateTime;
import java.util.Set;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.controller.MyUIControllers;
import ch.bfh.ti.soed.hs16.srs.green.db.CustomerDB;
import ch.bfh.ti.soed.hs16.srs.green.db.ReservationDB;
import ch.bfh.ti.soed.hs16.srs.green.db.ResourceDB;
import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.green.model.Resource;
import ch.bfh.ti.soed.hs16.srs.green.model.Role;

public class MyUIControllerTest {

	private static Connection c = null;

	private static void connectDB() throws Throwable {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:srs.db");
	}

	private static void disconnectDB() throws Throwable {
		c.close();
	}

	private static void deleteContentOfTables() throws Throwable {
		c.createStatement().executeUpdate("delete from reservations;");
		c.createStatement().executeUpdate("delete from customer;");
		c.createStatement().executeUpdate("delete from resources;");
	}

	@Test
	public void registerTest() throws Throwable {

		connectDB();
		deleteContentOfTables();
		MyUIControllers mc = new MyUIControllers();
		disconnectDB();
		mc.register("Test", "testpW",Role.CUSTOMER);

	}

	@Test
	public void getCustomerTestNull() throws Throwable {

		connectDB();
		deleteContentOfTables();
		disconnectDB();
		CustomerDB.registerCustomer("MarcoM234", "testPW123",Role.CUSTOMER);
		CustomerDB.registerCustomer("MarcoM5", "testPW",Role.CUSTOMER);
		MyUIControllers mc = new MyUIControllers();
		assertNull(mc.getCustomer("MarcoM3"));
	}

	@Test
	public void getCustomerTestNotNull() throws Throwable {
		connectDB();
		deleteContentOfTables();
		disconnectDB();
		CustomerDB.registerCustomer("MarcoM234", "testPW123",Role.CUSTOMER);
		CustomerDB.registerCustomer("MarcoM5", "testPW",Role.CUSTOMER);
		MyUIControllers mc = new MyUIControllers();
		assertNotNull(mc.getCustomer("MarcoM5"));
	}

	@Test
	public void loginTestFalse() throws Throwable {

		connectDB();
		deleteContentOfTables();
		disconnectDB();
		CustomerDB.registerCustomer("MarcoM", "testPW",Role.CUSTOMER);
		MyUIControllers mc = new MyUIControllers();
		assertFalse(mc.login("MarcoM", "testPWfalsch"));
	}

	@Test
	public void loginTestFalseTwo() throws Throwable {

		connectDB();
		deleteContentOfTables();
		disconnectDB();
		CustomerDB.registerCustomer("MarcoM", "testPW",Role.CUSTOMER);
		MyUIControllers mc = new MyUIControllers();
		assertFalse(mc.login("MarcoMFalsch", "testPW"));
	}

	@Test
	public void loginTestTrue() throws Throwable {

		connectDB();
		deleteContentOfTables();
		disconnectDB();
		CustomerDB.registerCustomer("MarcoM", "testPW",Role.CUSTOMER);
		MyUIControllers mc = new MyUIControllers();
		assertTrue(mc.login("MarcoM", "testPW"));
	}

	@Test
	public void getReservationsMadeByCustomerTest() throws Throwable {

		connectDB();
		deleteContentOfTables();
		disconnectDB();

		CustomerDB.registerCustomer("SvenTest", "testpw",Role.CUSTOMER);
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);

		Customer u1 = new Customer("SvenTest", "testpw",Role.CUSTOMER);
		Resource res = new Resource("RoomTest2", 44, "RoomLocationTest2");
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 12, 30);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 14, 30);

		ReservationDB.addReservation(start, end, res, u1);
		MyUIControllers mc = new MyUIControllers();
		Set<Reservation> sets = mc.getReservationsMadeByCustomer(u1);
		assertNotNull(sets);

	}

	@Test
	public void makeReservationTest() throws Throwable {

		connectDB();
		deleteContentOfTables();
		disconnectDB();

		CustomerDB.registerCustomer("SvenTest", "testpw",Role.CUSTOMER);
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);

		Customer u1 = new Customer("SvenTest", "testpw",Role.CUSTOMER);
		Resource res = new Resource("RoomTest2", 44, "RoomLocationTest2");
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 12, 30);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 14, 30);

		MyUIControllers mc = new MyUIControllers();
		mc.makeReservation(start, end, res, u1);

	}

	@Test
	public void getResourcesTest() throws Throwable {
		connectDB();
		deleteContentOfTables();
		disconnectDB();
		MyUIControllers mc = new MyUIControllers();
		assertNotNull(mc.getResources());
	}

	@Test
	public void getAmountRoomsTest() throws Throwable {
		connectDB();
		deleteContentOfTables();
		disconnectDB();
		MyUIControllers mc = new MyUIControllers();
		assertNotNull(mc.getAmountRooms());
	}

	@Test
	public void addRessourceTest() throws Throwable {
		connectDB();
		deleteContentOfTables();
		disconnectDB();
		MyUIControllers mc = new MyUIControllers();
		mc.addRessource("Ro1", "lange", 55);
	}

	@Test
	public void isAvailableTestNotSameRoom() throws Throwable {
		connectDB();
		deleteContentOfTables();
		disconnectDB();

		CustomerDB.registerCustomer("SvenTest", "testpw",Role.CUSTOMER);
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);

		Customer u1 = new Customer("SvenTest", "testpw",Role.CUSTOMER);
		Resource res = new Resource("RoomTest2", 44, "RoomLocationTest2");
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 12, 30);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 14, 30);

		Resource res2 = new Resource("RoomTestv2", 44, "RoomLocationTest2");
		LocalDateTime start2 = LocalDateTime.of(2018, 12, 12, 12, 30);
		LocalDateTime end2 = LocalDateTime.of(2018, 12, 12, 14, 30);

		ReservationDB.addReservation(start, end, res, u1);

		MyUIControllers mc = new MyUIControllers();

		assertTrue(mc.isAvailable(start2, end2, res2));

	}

	@Test
	public void isAvailableTestNotSameDate() throws Throwable {

		connectDB();
		deleteContentOfTables();
		disconnectDB();

		CustomerDB.registerCustomer("SvenTest", "testpw",Role.CUSTOMER);
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);

		Customer u1 = new Customer("SvenTest", "testpw",Role.CUSTOMER);

		Resource res = new Resource("RoomTest2", 44, "RoomLocationTest2");
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 12, 30);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 14, 30);

		Resource res2 = new Resource("RoomTest2", 44, "RoomLocationTest2");
		LocalDateTime start2 = LocalDateTime.of(2019, 12, 12, 12, 30);
		LocalDateTime end2 = LocalDateTime.of(2019, 12, 12, 14, 30);

		ReservationDB.addReservation(start, end, res, u1);

		MyUIControllers mc = new MyUIControllers();

		assertTrue(mc.isAvailable(start2, end2, res2));

	}

	@Test(expected = Throwable.class)
	public void isAvailableSameStartTime() throws Throwable {

		connectDB();
		deleteContentOfTables();
		disconnectDB();

		CustomerDB.registerCustomer("SvenTest", "testpw",Role.CUSTOMER);
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);

		Customer u1 = new Customer("SvenTest", "testpw",Role.CUSTOMER);
		Resource res = new Resource("RoomTest2", 44, "RoomLocationTest2");
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 12, 30);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 14, 30);
		LocalDateTime end2 = LocalDateTime.of(2018, 12, 12, 15, 30);

		ReservationDB.addReservation(start, end, res, u1);

		MyUIControllers mc = new MyUIControllers();
		mc.isAvailable(start, end2, res); // throws exception;

	}

	@Test(expected = Throwable.class)
	public void isAvailableSameEndTime() throws Throwable {

		connectDB();
		deleteContentOfTables();
		disconnectDB();

		CustomerDB.registerCustomer("SvenTest", "testpw",Role.CUSTOMER);
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);

		Customer u1 = new Customer("SvenTest", "testpw",Role.CUSTOMER);
		Resource res = new Resource("RoomTest2", 44, "RoomLocationTest2");
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 12, 30);
		LocalDateTime start2 = LocalDateTime.of(2018, 12, 12, 11, 30);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 14, 30);

		ReservationDB.addReservation(start, end, res, u1);
		MyUIControllers mc = new MyUIControllers();
		mc.isAvailable(start2, end, res); // throws exception;

	}

	@Test(expected = Throwable.class)
	public void isAvailableStartBetweenTime() throws Throwable {

		connectDB();
		deleteContentOfTables();
		disconnectDB();

		CustomerDB.registerCustomer("SvenTest", "testpw",Role.CUSTOMER);
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);

		Customer u1 = new Customer("SvenTest", "testpw",Role.CUSTOMER);
		Resource res = new Resource("RoomTest2", 44, "RoomLocationTest2");
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 12, 30);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 14, 30);
		LocalDateTime start2 = LocalDateTime.of(2018, 12, 12, 13, 30);
		LocalDateTime end2 = LocalDateTime.of(2018, 12, 12, 15, 30);

		ReservationDB.addReservation(start, end, res, u1);
		MyUIControllers mc = new MyUIControllers();

		mc.isAvailable(start2, end2, res); // throws exception;

	}

	@Test(expected = Throwable.class)
	public void isAvailableEndBetweenTime() throws Throwable {

		connectDB();
		deleteContentOfTables();
		disconnectDB();

		CustomerDB.registerCustomer("SvenTest", "testpw",Role.CUSTOMER);
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);

		Customer u1 = new Customer("SvenTest", "testpw",Role.CUSTOMER);
		Resource res = new Resource("RoomTest2", 44, "RoomLocationTest2");
		LocalDateTime start = LocalDateTime.of(2018, 12, 12, 12, 30);
		LocalDateTime end = LocalDateTime.of(2018, 12, 12, 14, 30);
		LocalDateTime start2 = LocalDateTime.of(2018, 12, 12, 11, 30);
		LocalDateTime end2 = LocalDateTime.of(2018, 12, 12, 13, 30);

		ReservationDB.addReservation(start, end, res, u1);
		MyUIControllers mc = new MyUIControllers();
		mc.isAvailable(start2, end2, res); // throws exception;

	}

}
