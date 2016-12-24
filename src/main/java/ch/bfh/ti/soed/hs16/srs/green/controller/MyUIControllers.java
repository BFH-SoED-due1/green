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

	public void register(String userName, String pw, Role x) throws Throwable {
		CustomerDB.registerCustomer(userName, pw,x);
	}

	public Customer getCustomer(String userName) throws Throwable {
		customers = CustomerDB.getCustomers();
		for (Customer c : customers)

			if (c.getUserName().equals(userName))
				return c;
		return null;
	}

	public boolean login(String userName, String pw) throws Throwable {
		customers = CustomerDB.getCustomers();
		for (Customer c : customers) {
		
			if (c.getUserName().equals(userName) && c.checkPW(pw))
				return true;
		}
		return false;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public int getAmountRooms() throws Throwable {
		return ResourceDB.getAmountRooms();
	}

	public void addRessource(String roomName, String location, int size) throws Throwable {
		ResourceDB.addResource(roomName, location, size);
	}
	
	public void deleteRessource(String roomName, String location) throws Throwable {
		ResourceDB.removeResource(roomName, location);
	}
	
	public void makeReservation(LocalDateTime startTime, LocalDateTime endTime, Resource resource, Customer customer)
			throws Throwable {
		ReservationDB.addReservation(startTime, endTime, resource, customer);
	}

	public Set<Reservation> getReservationsMadeByCustomer(Customer c) throws Exception {
		return ReservationDB.getReservationMadeByCustomer(c);
	}
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
