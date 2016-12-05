/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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

public class MyUIControllerTest {

	@Test
	public void registerTest() throws Throwable {
		MyUIControllers mc = new MyUIControllers();
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		Statement stmt = c.createStatement();
		stmt.executeUpdate("delete from customer;");
		stmt.close();
		c.close();

		mc.register("Test", "testpW");
	}

	@Test
	public void getCustomerTestNull() throws Throwable {

		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		Statement stmt = c.createStatement();
		stmt.executeUpdate("delete from customer;");
		stmt.close();
		c.close();
		CustomerDB.registerCustomer("MarcoM234", "testPW123");
		CustomerDB.registerCustomer("MarcoM5", "testPW");
		MyUIControllers mc = new MyUIControllers();
		assertNull(mc.getCustomer("MarcoM3"));
	}

	@Test
	public void getCustomerTestNotNull() throws Throwable {
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		Statement stmt = c.createStatement();
		stmt.executeUpdate("delete from customer;");
		stmt.close();
		c.close();
		CustomerDB.registerCustomer("MarcoM234", "testPW123");
		CustomerDB.registerCustomer("MarcoM5", "testPW");
		MyUIControllers mc = new MyUIControllers();
		assertNotNull(mc.getCustomer("MarcoM5"));
	}

	@Test
	public void loginTestFalse() throws Throwable {

		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		Statement stmt = c.createStatement();
		stmt.executeUpdate("delete from customer;");
		stmt.close();
		c.close();
		CustomerDB.registerCustomer("MarcoM", "testPW");
		MyUIControllers mc = new MyUIControllers();
		assertFalse(mc.login("MarcoM", "testPWfalsch"));
	}
	
	@Test
	public void loginTestFalseTwo() throws Throwable {

		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		Statement stmt = c.createStatement();
		stmt.executeUpdate("delete from customer;");
		stmt.close();
		c.close();
		CustomerDB.registerCustomer("MarcoM", "testPW");
		MyUIControllers mc = new MyUIControllers();
		assertFalse(mc.login("MarcoMFalsch", "testPW"));
	}

	@Test
	public void loginTestTrue() throws Throwable {

		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		Statement stmt = c.createStatement();
		stmt.executeUpdate("delete from customer;");
		stmt.close();
		c.close();
		CustomerDB.registerCustomer("MarcoM", "testPW");
		MyUIControllers mc = new MyUIControllers();
		assertTrue(mc.login("MarcoM", "testPW"));
	}

	@Test
	public void getReservationsMadeByCustomerTest() throws Throwable {
		MyUIControllers mc = new MyUIControllers();
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		c.createStatement().executeUpdate("delete from reservations;");
		c.createStatement().executeUpdate("delete from customer;");
		c.createStatement().executeUpdate("delete from resources;");

		CustomerDB.registerCustomer("SvenTest", "testpw");
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);

		Customer u1 = new Customer("SvenTest", "testpw");

		ReservationDB.addReservation(LocalDateTime.of(2018, 12, 12, 12, 30), LocalDateTime.of(2018, 12, 12, 14, 00),
				new Resource("RoomTest2", 44, "RoomLocationTest2"), u1);

		c.close();
		Set<Reservation> sets = mc.getReservationsMadeByCustomer(u1);
		assertNotNull(sets);

	}

	@Test
	public void makeReservationTest() throws Throwable {

		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		c.createStatement().executeUpdate("delete from reservations;");
		c.createStatement().executeUpdate("delete from customer;");
		c.createStatement().executeUpdate("delete from resources;");
		CustomerDB.registerCustomer("SvenTest", "testpw");
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);
		c.close();
		MyUIControllers mc = new MyUIControllers();
		mc.makeReservation(LocalDateTime.of(2018, 12, 12, 12, 30), LocalDateTime.of(2018, 12, 12, 14, 00),
				new Resource("RoomTest2", 44, "RoomLocationTest2"), new Customer("SvenTest", "testpw"));

	}

	@Test
	public void getResourcesTest() throws Throwable {
		MyUIControllers mc = new MyUIControllers();
		assertNotNull(mc.getResources());

	}

	@Test
	public void getAmountRoomsTest() throws Throwable {
		MyUIControllers mc = new MyUIControllers();
		assertNotNull(mc.getAmountRooms());
	}

	@Test
	public void addRessourceTest() throws Throwable {
		MyUIControllers mc = new MyUIControllers();
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		Statement stmt = c.createStatement();
		stmt.executeUpdate("delete from resources;");
		stmt.close();
		c.close();

		mc.addRessource("Ro1", "lange", 55);
	}

}
