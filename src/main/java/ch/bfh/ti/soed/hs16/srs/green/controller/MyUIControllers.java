package ch.bfh.ti.soed.hs16.srs.green.controller;

import java.util.Set;

import ch.bfh.ti.soed.hs16.srs.green.db.CustomerDB;
import ch.bfh.ti.soed.hs16.srs.green.db.ResourceDB;
import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Resource;

public class MyUIControllers {

	private Set<Customer> customers = CustomerDB.getCustomers();
	private Set<Resource> resources = ResourceDB.getResources();

	public void register(String name, String email, String pw) throws Throwable {

		CustomerDB.registerCustomer(name, email, pw);

	}

	public boolean login(String name, String pw) {

		for (Customer c : customers) {
			if (c.getName().equals(name) && c.getPW().equals(pw))
				return true;
		}
		return false;
	}

	public Set<Resource> getResources() {
		return resources;
	}
	
	public int getAmountRooms(){
		return ResourceDB.getAmountRooms();
	}
	
	public void addRessource(String roomName, String location, int size) throws Throwable{
		ResourceDB.addResource(roomName, location, size);
	}

}
