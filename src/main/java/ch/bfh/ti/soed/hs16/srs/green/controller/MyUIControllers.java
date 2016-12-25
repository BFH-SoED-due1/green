/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.controller;

import java.time.LocalDateTime;
import java.util.Set;

import ch.bfh.ti.soed.hs16.srs.green.db.CustomerDB;
import ch.bfh.ti.soed.hs16.srs.green.db.ReservationDB;
import ch.bfh.ti.soed.hs16.srs.green.db.ResourceDB;
import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.green.model.Resource;
import ch.bfh.ti.soed.hs16.srs.green.model.Role;

/**
 * Only class which controls the database srs.db
 * 
 * @author team-green
 */
public class MyUIControllers {
	/**
	 * @see Reservation
	 */
	private Set<Reservation> reservations;
	/**
	 * @see Customer
	 */
	private Set<Customer> customers;
	/**
	 * @see Resource
	 */
	private Set<Resource> resources;

	/**
	 * Constructor which gets all the customers and resources from the database.
	 * 
	 * @throws Throwable
	 */
	public MyUIControllers() throws Throwable {

		customers = CustomerDB.getCustomers();
		resources = ResourceDB.getResources();
		reservations = ReservationDB.getReservations();
	}

	/**
	 * Method registers a customer with given user name and password.
	 * 
	 * @param userName
	 *            specific user name from UI text field username.
	 * @param pw
	 *            specific password from UI text field pw.
	 * @param x
	 *            specific Role
	 * @throws Throwable
	 */
	public void register(String userName, String pw, Role x) throws Throwable {
		CustomerDB.registerCustomer(userName, pw, x);
	}

	/**
	 * Method gets the Object-Customer from the given user name.
	 * 
	 * @param userName
	 * @return returns customer of the specific user name.
	 * @see Customer
	 * @throws Throwable
	 */
	public Customer getCustomer(String userName) throws Throwable {
		customers = CustomerDB.getCustomers();
		for (Customer c : customers)

			if (c.getUserName().equals(userName))
				return c;
		return null;
	}

	/**
	 * Method checks if the password and user name is correct.
	 * 
	 * @param userName
	 * @param pw
	 * @return true if login-data are correct, otherwise false
	 * @throws Throwable
	 */
	public boolean login(String userName, String pw) throws Throwable {
		customers = CustomerDB.getCustomers();
		for (Customer c : customers) {

			if (c.getUserName().equals(userName) && c.checkPW(pw))
				return true;
		}
		return false;
	}

	/**
	 * Method which returns all resources in the database.
	 * 
	 * @return a set of resources.
	 * @see Resource
	 */
	public Set<Resource> getResources() {
		return resources;
	}

	/**
	 * Method which returns the number of available Rooms in the database.
	 * 
	 * @return the number of rooms
	 * @throws Throwable
	 * @see Resource
	 * @see ResourceDB
	 */
	public int getAmountRooms() throws Throwable {
		return ResourceDB.getAmountRooms();
	}

	/**
	 * Methods adds resource to the resources-table.
	 * 
	 * @param roomName
	 *            specific room name
	 * @param location
	 *            specific location
	 * @param size
	 *            specific size
	 * @throws Throwable
	 * @see Resource
	 * @see ResourceDB
	 */
	public void addResource(String roomName, String location, int size) throws Throwable {
		ResourceDB.addResource(roomName, location, size);
	}

	/**
	 * Methods which deletes desired resource
	 * 
	 * @param roomName
	 *            specific room
	 * @param location
	 *            specific location
	 * @throws Throwable
	 * @see ResourceDB
	 */
	public void deleteResource(String roomName, String location) throws Throwable {
		ResourceDB.removeResource(roomName, location);
	}

	/**
	 * Methods which adds a Reservation to the database at a specific time,
	 * date, in a specific room, of a specific customer.
	 * 
	 * @param startTime
	 * @param endTime
	 * @param resource
	 * @param customer
	 * @throws Throwable
	 * @see Reservation
	 * @see ReservationDB
	 */
	public void makeReservation(LocalDateTime startTime, LocalDateTime endTime, Resource resource, Customer customer)
			throws Throwable {
		ReservationDB.addReservation(startTime, endTime, resource, customer);
	}

	/**
	 * Method which returns all reservation made by a specific Customer.
	 * 
	 * @param c
	 *            Customer, of which all the reservation will be returned.
	 * @return a set of Reservations.
	 * @throws Throwable
	 * @see Reservation
	 * @see ReservationDB
	 */
	public Set<Reservation> getReservationsMadeByCustomer(Customer c) throws Throwable {
		return ReservationDB.getReservationMadeByCustomer(c);
	}

	/**
	 * Method which checks if a reservation is possible in the given room at the
	 * specific start and end time.
	 * 
	 * @param start
	 *            specific start time of the requested reservation.
	 * @param end
	 *            specific end time of the requested reservation.
	 * @param room
	 *            desired room, in which the reservation should be.
	 * @return true, if the reservations is available.
	 * @throws Throwable
	 * @see LocalDateTime
	 * @see Resource
	 */
	public boolean isAvailable(LocalDateTime start, LocalDateTime end, Resource room) throws Throwable {

		String startToCheck = start.toString();
		String endToCheck = end.toString();
		String requestedDate = startToCheck.substring(0, 10);

		int startH = Integer.parseInt(startToCheck.substring(11, 13));
		int startM = Integer.parseInt(startToCheck.substring(14, 16));
		int endH = Integer.parseInt(endToCheck.substring(11, 13));
		int endM = Integer.parseInt(endToCheck.substring(14, 16));
		int concatenateStart = Integer.valueOf(String.valueOf(startH) + String.valueOf(startM));
		int concatenateEnd = Integer.valueOf(String.valueOf(endH) + String.valueOf(endM));

		for (Reservation r : reservations) {
			if (r.getStartTime().toString().substring(0, 10).equals(requestedDate)
					&& room.getName().equals(r.getResource().getName())) {
				int startHR = Integer.parseInt(r.getStartTime().toString().substring(11, 13));
				int startMR = Integer.parseInt(r.getStartTime().toString().substring(14, 16));
				int endHR = Integer.parseInt(r.getEndTime().toString().substring(11, 13));
				int endMR = Integer.parseInt(r.getEndTime().toString().substring(14, 16));
				int concatenateStartR = Integer.valueOf(String.valueOf(startHR) + String.valueOf(startMR));
				int concatenateEndR = Integer.valueOf(String.valueOf(endHR) + String.valueOf(endMR));

				if (concatenateStartR == concatenateStart)
					throw new Exception("date at this start-time already booked");
				if (concatenateEndR == concatenateEnd)
					throw new Exception("date at this end-time already booked");
				if (concatenateStartR < concatenateStart && concatenateStart < concatenateEndR)
					throw new Exception("date at between time already booked, 1");
				if (concatenateStartR < concatenateEnd && concatenateEnd < concatenateEndR)
					throw new Exception("date at between time already booked, 2");

			}

		}

		return true;

	}
}
