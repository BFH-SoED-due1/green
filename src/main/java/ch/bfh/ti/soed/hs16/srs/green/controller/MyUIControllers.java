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

	private Set<Customer> customers = CustomerDB.getCustomers();
	private Set<Resource> resources = ResourceDB.getResources();

	public void register(String userName, String preName, String lastName, String email, String pw) throws Throwable {
		CustomerDB.registerCustomer(userName, preName, lastName, email, pw);
	}

	public Customer getCustomer(String userName) {
		for (Customer c : customers)
			if (c.getUserName().equals(userName))
				return c;
		return null;
	}

	public boolean login(String userName, String pw) {
		for (Customer c : customers) {
			if (c.getUserName().equals(userName) && c.getPW().equals(pw))
				return true;
		}
		return false;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public int getAmountRooms() {
		return ResourceDB.getAmountRooms();
	}

	public void addRessource(String roomName, String location, int size) throws Throwable {
		ResourceDB.addResource(roomName, location, size);
	}

	public void makeReservation(LocalDateTime startTime, LocalDateTime endTime, Resource resource, Customer customer)
			throws Throwable {
		ReservationDB.addReservation(startTime, endTime, resource, customer);
	}
	
	public Set<Reservation> getReservationsMadeByCustomer(Customer c){
		return ReservationDB.getReservationMadeByCustomer(c);
	}

}
