/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.db;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.soed.hs16.srs.green.model.Resource;

/**
 * A class which represents the resources table in the srs.db. Class is only
 * used by the class MyUIControllers.
 * @author team-green
 * @version 1.4, 18.12.16
 */
public class ResourceDB extends DBConnector {

	/**
	 * Adds a new Resource/Room to the resources table in the srs.db.
	 * @param roomName
	 *            the specified room name.
	 * @param locatoin
	 *            the specified location of the room.
	 * @param size
	 *            the size of the room.
	 * @throws Throwable
	 */
	public static void addResource(String roomName, String locatoin, int size) throws Throwable {

		connectDB();
		String sql = "INSERT INTO RESOURCES (ROOMNAME,LOCATION,SIZE) " + "VALUES ('" + roomName + "', '" + locatoin
				+ "', '" + size + "');";
		c.createStatement().executeUpdate(sql);
		disconnectDB();
	}

	/**
	 * Method removes a specific resource.
	 * @param roomName
	 *            specific room name
	 * @param locatoin
	 *            specific location
	 * @throws Throwable
	 * @see Resource
	 */
	public static void removeResource(String roomName, String locatoin) throws Throwable {

		connectDB();
		String sql = "DELETE FROM RESOURCES WHERE ROOMNAME= '" + roomName + "' AND LOCATION= '" + locatoin + "';";
		c.createStatement().executeUpdate(sql);
		disconnectDB();
	}

	/**
	 * Returns the amount of rooms of the resources table.
	 * @return amount of rooms.
	 * @throws Throwable
	 */
	public static int getAmountRooms() throws Throwable {
		connectDB();
		ResultSet rs = c.createStatement().executeQuery("select count (*) AS 'countinho' from resources;");
		int res = rs.getInt("countinho");
		rs.close();
		disconnectDB();
		return res;
	}

	/**
	 * Returns all resources/rooms of the resources-table.
	 * @return a set of resource.
	 * @throws Throwable
	 * @see Resource
	 */
	public static Set<Resource> getResources() throws Throwable {

		Set<Resource> resources = new HashSet<>();
		connectDB();
		ResultSet rs = c.createStatement().executeQuery("SELECT ROOMNAME, LOCATION, SIZE FROM RESOURCES;");

		while (rs.next()) {
			String roomName = rs.getString("roomName");
			String location = rs.getString("location");
			int size = rs.getInt("size");
			resources.add(new Resource(roomName, size, location));

		}

		rs.close();
		disconnectDB();
		return resources;
	}

}
