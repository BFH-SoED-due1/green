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
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.green.model.Resource;

public class ReservationDB {

	private static Connection c = null;
	private static Statement stmt = null;

	public static void addReservation(LocalDateTime startTime, LocalDateTime endTime, Resource resource,
			Customer customer) throws Exception {

		Class.forName("org.sqlite.JDBC");
		System.out.println("FORNAME");
		c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		stmt = c.createStatement();

		Statement forRoomID = c.createStatement();

		ResultSet roIDr = forRoomID.executeQuery("select rowid from resources where location = '"
				+ resource.getLocation() + "' and  roomName = '" + resource.getName() + "';");

		int roID = roIDr.getInt("rowid");

		String sql = "INSERT INTO RESERVATIONS (STARTTIME, ENDTIME, ROOMID, USERNAME) " + "VALUES ('" + startTime
				+ "', '" + endTime + "', '" + roID + "', '" + customer.getUserName() + "');";

		stmt.executeUpdate(sql);

		roIDr.close();

		stmt.close();
		c.close();

	}

	public static Set<Reservation> getReservationMadeByCustomer(Customer customer) throws Exception {
		c = null;
		stmt = null;
		Set<Reservation> reservations = new HashSet<>();

		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:srs.db");
		c.setAutoCommit(false);
		stmt = c.createStatement();

		ResultSet rs = stmt
				.executeQuery("SELECT STARTTIME, ENDTIME, ROOMID, USERNAME FROM RESERVATIONS WHERE USERNAME = '"
						+ customer.getUserName() + "';");

		while (rs.next()) {

			LocalDateTime startTime = LocalDateTime.parse(rs.getString("startTime"));
			LocalDateTime endTime = LocalDateTime.parse(rs.getString("endTime"));

			int roomID1 = rs.getInt("roomid");

			ResultSet resourceQ = c.createStatement()
					.executeQuery("SELECT * FROM RESOURCES WHERE ROWID = " + roomID1 + ";");

			String roomN = resourceQ.getString("roomName");
			String loc = resourceQ.getString("location");

			int size = resourceQ.getInt("size");

			Resource resource = new Resource(roomN, size, loc);
			ResultSet customerQ = c.createStatement()
					.executeQuery("SELECT * FROM CUSTOMER WHERE USERNAME = '" + customer.getUserName() + "';");

			String cust = customerQ.getString("userName");
			String pw = customerQ.getString("pw");

			Customer userName = new Customer(cust, pw);

			reservations.add(new Reservation(startTime, endTime, resource, userName));

		}

		rs.close();
		stmt.close();
		c.close();

		return reservations;
	}

}
