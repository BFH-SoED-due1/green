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

public class DBConnector {

	protected static Connection c = null;

	/**
	 * Method, which will do the connection to the database.
	 * @throws Throwable
	 */
	public static void connectDB() throws Throwable {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:srs.db");
	}

	/**
	 * Method, which makes the disconnect to the database.
	 * @throws Throwable
	 */
	public static void disconnectDB() throws Throwable {
		c.close();
	}

	/**
	 * Deletes all the content of the tables in the srs.db - mostly neede for
	 * the test classes.
	 * @throws Throwable
	 */
	public static void delteContentOfTabels() throws Throwable {
		c.createStatement().executeUpdate("delete from reservations;");
		c.createStatement().executeUpdate("delete from customer;");
		c.createStatement().executeUpdate("delete from resources;");
	}

}
