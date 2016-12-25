/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.soed.hs16.srs.green.model.Resource;

/**
 * A class which represents the resources table in the srs.db. Class is only
 * used by the class MyUIControllers.
 * 
 * @author team-green
 * @version 1.4, 18.12.16
 */
public class ResourceDB {

	private static Connection c = null;
	private static Statement stmt = null;

	/**
	 * Adds a new Resource/Room to the resources table in the srs.db.
	 * 
	 * @param roomName
	 *            the specified room name.
	 * @param locatoin
	 *            the specified location of the room.
	 * @param size
	 *            the size of the room.
	 * @throws Throwable
	 */
	public static void addResource(String roomName, String locatoin, int size) throws Throwable {

		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		stmt = c.createStatement();
		String sql = "INSERT INTO RESOURCES (ROOMNAME,LOCATION,SIZE) " + "VALUES ('" + roomName + "', '" + locatoin
				+ "', '" + size + "');";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();
	}

	/**
	 * Method removes a specific resource.
	 * 
	 * @param roomName
	 *            specific room name
	 * @param locatoin
	 *            specific location
	 * @throws Throwable
	 * @see Resource
	 */
	public static void removeResource(String roomName, String locatoin) throws Throwable {

		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		stmt = c.createStatement();

		String sql = "DELETE FROM RESOURCES WHERE ROOMNAME= '" + roomName + "' AND LOCATION= '" + locatoin + "';";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();

	}

	/**
	 * Returns the amount of rooms of the resources table.
	 * 
	 * @return amount of rooms.
	 * @throws Throwable
	 */
	public static int getAmountRooms() throws Throwable {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		stmt = c.createStatement();
		ResultSet rs = c.createStatement().executeQuery("select count (*) AS 'countinho' from resources;");
		int res = rs.getInt("countinho");
		rs.close();
		stmt.close();
		c.close();
		return res;
	}

	/**
	 * Returns all resources/rooms of the resources-table.
	 * 
	 * @return a set of resource.
	 * @throws Throwable
	 * @see Resource
	 */
	public static Set<Resource> getResources() throws Throwable {
		c = null;
		stmt = null;
		Set<Resource> resources = new HashSet<>();

		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		c.setAutoCommit(false);
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ROOMNAME, LOCATION, SIZE FROM RESOURCES;");
		while (rs.next()) {
			String roomName = rs.getString("roomName");
			String location = rs.getString("location");
			int size = rs.getInt("size");
			resources.add(new Resource(roomName, size, location));

		}
		rs.close();
		stmt.close();
		c.close();
		return resources;
	}

}
