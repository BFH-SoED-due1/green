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

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.db.ResourceDB;

public class ResourceDBTest {

	@Test
	public void testConstructor() {
		ResourceDB rdb = new ResourceDB();
		assertNotNull(rdb);
	}

	@Test
	public void addResourceTest() throws Throwable {
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		Statement stmt = c.createStatement();
		stmt.executeUpdate("delete from resources;");
		stmt.close();
		c.close();
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);
		assertNotNull(ResourceDB.getResources());

	}
	@Test(expected = Throwable.class)
	public void removeResourceTest() throws Throwable {
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		Statement stmt = c.createStatement();
		stmt.executeUpdate("delete from resources;");
		stmt.close();
		c.close();
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);
		ResourceDB.removeResource("RoomTest2", "RoomLocationTest2");
		assertNull(ResourceDB.getResources());
	}
	@Test
	public void getAmountRoomsTest() throws Throwable {
		assertTrue(ResourceDB.getAmountRooms() >= 0);
	}

}
