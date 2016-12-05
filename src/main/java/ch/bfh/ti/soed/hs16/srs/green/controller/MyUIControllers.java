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

public class MyUIControllers {

	private Set<Customer> customers;

	private Set<Resource> resources;

	public MyUIControllers() throws Throwable {

		customers = CustomerDB.getCustomers();
		resources = ResourceDB.getResources();

	}

	public void register(String userName, String pw) throws Throwable {
		CustomerDB.registerCustomer(userName, pw);
	}

	public Customer getCustomer(String userName) {
		for (Customer c : customers)

			if (c.getUserName().equals(userName))
				return c;
		return null;
	}

	public boolean login(String userName, String pw) {
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

	public void makeReservation(LocalDateTime startTime, LocalDateTime endTime, Resource resource, Customer customer)
			throws Throwable {
		ReservationDB.addReservation(startTime, endTime, resource, customer);
	}

	public Set<Reservation> getReservationsMadeByCustomer(Customer c) throws Exception {
		return ReservationDB.getReservationMadeByCustomer(c);
	}

}
