/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package ch.bfh.ti.soed.hs16.srs.green.model;

import java.rmi.server.UID;

public class Resource {

	private String name, location;
	private int size;
	private UID UID;

	public Resource(String name, int size, String location) {
		this.name = name;
		this.size = size;
		this.location = location;
		UID = new UID();

	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public UID getUID(){
		return UID;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
