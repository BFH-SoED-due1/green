/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

/**
 * Class Resource which has all information of a specific Resource.
 * @author team-green
 */
public class Resource {

	private String name, location;
	private int size;

	/**
	 * Constructor which initialize all data for a Resource.
	 * @param name
	 * @param size
	 * @param location
	 */
	public Resource(String name, int size, String location) {

		this.name = name;
		this.size = size;
		this.location = location;

	}

	/**
	 * returns the location of this resource.
	 * @return
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * returns the name of this Resource.
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * returns the size of this Resource.
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * sets the specific location to this Resource.
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * sets the specific name to this Resource.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * sets the specific size to this Resource.
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

}
