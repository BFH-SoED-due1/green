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

public class ResourceDB {

	private static Connection c = null;
	private static Statement stmt = null;

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
	public static void removeResource(String roomName, String locatoin) throws Throwable {

		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		stmt = c.createStatement();

		String sql = "DELETE FROM RESOURCES WHERE ROOMNAME= '" + roomName + "' AND LOCATION= '" + locatoin+ "';";
		stmt.executeUpdate(sql);
		stmt.close();
		c.close();

	}

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
