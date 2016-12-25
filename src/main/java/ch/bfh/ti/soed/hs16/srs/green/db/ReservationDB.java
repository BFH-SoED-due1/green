/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.db;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.green.model.Resource;
import ch.bfh.ti.soed.hs16.srs.green.model.Role;

/**
 * A class which represents the reservations table in the srs.db. Class is only
 * used by the class MyUIControllers.
 * 
 * @author team-green
 * @version 1.4, 18.12.16
 */
public class ReservationDB extends DBConnector {

	/**
	 * A method which adds a reservation to the reservations table in srs.db.
	 * 
	 * @param startTime
	 *            At what time the reservation starts.
	 * @param endTime
	 *            At what time the reservation ends.
	 * @param resource
	 *            In which room the reservation is.
	 * @param customer
	 *            Which customer made the reservation.
	 * @throws Exception
	 */
	public static void addReservation(LocalDateTime startTime, LocalDateTime endTime, Resource resource,
			Customer customer) throws Throwable {

		connectDB();
		ResultSet roIDr = c.createStatement().executeQuery("select rowid from resources where location = '"
				+ resource.getLocation() + "' and  roomName = '" + resource.getName() + "';");
		int roID = roIDr.getInt("rowid");
		String sql = "INSERT INTO RESERVATIONS (STARTTIME, ENDTIME, ROOMID, USERNAME) " + "VALUES ('" + startTime
				+ "', '" + endTime + "', '" + roID + "', '" + customer.getUserName() + "');";
		c.createStatement().executeUpdate(sql);
		disconnectDB();

	}

	/**
	 * Returns all reservations made by a specific customer.
	 * 
	 * @param customer
	 *            customer, of which you want to have all reservations.
	 * @return a set of reservations of a specific customer.
	 * @throws Exception
	 * @see Reservation
	 */

	public static Set<Reservation> getReservationMadeByCustomer(Customer customer) throws Throwable {

		Set<Reservation> reservations = new HashSet<>();

		connectDB();
		ResultSet rs = c.createStatement()
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

			Customer userName = new Customer(cust, pw, Role.valueOf(customerQ.getString("rights")));

			reservations.add(new Reservation(startTime, endTime, resource, userName));

		}

		rs.close();
		disconnectDB();
		return reservations;
	}

	/**
	 * Returns all reservations in the database.
	 * 
	 * @return a set of reservations.
	 * @throws Exception
	 * @see Reservation
	 */
	public static Set<Reservation> getReservations() throws Throwable {

		Set<Reservation> reservations = new HashSet<>();

		connectDB();

		ResultSet rs = c.createStatement().executeQuery("SELECT STARTTIME, ENDTIME, ROOMID FROM RESERVATIONS;");

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

			reservations.add(new Reservation(startTime, endTime, resource, null));
		}
		rs.close();
		disconnectDB();
		return reservations;

	}

}
