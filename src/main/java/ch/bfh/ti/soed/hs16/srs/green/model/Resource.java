/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

/**
 * A model class which instances represent resources
 * @author team green
 *
 */
public class Resource {

	private String name, location;
	private int size;

	/**
	 * Constructor of the class
	 * @param name the name of the resource, should not be null or empty
	 * @param size the size in "persons" of the resource, should not be null or empty
	 * @param location the address of the resource, should not be null or empty
	 */
	public Resource(String name, int size, String location) {

		this.name = name;
		this.size = size;
		this.location = location;
	}

	/**
	 * getLocation method
	 * @return location of the resource
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * getName method
	 * @return name of the resource
	 */
	public String getName() {
		return name;
	}

	/**
	 * getSize method
	 * @return the size in "persons" of the resource
	 */
	public int getSize() {
		return size;
	}

	/**
	 * setLocation method
	 * @param location the new location or new "description"
	 * this method sets the location or "description" of this resource
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * setName method
	 * @param name the new name for this resource
	 * this method sets the name of this resource
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * setName method
	 * @param size the new size for this resource
	 * this method sets the size of this resource
	 */
	public void setSize(int size) {
		this.size = size;
	}

}
