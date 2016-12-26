/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

import java.time.LocalDateTime;

/**
 * A model class which instances represent reservations of a resource
 * @author team green
 */
public class Reservation {

	private LocalDateTime startTime, endTime;

	private Resource resource;
	private Customer customer;


	/**
	 * constructor of the class
	 * @param startTime
	 * 		the time when the reservation of the resource starts
	 * @param endTime
	 * 		the time when the reservation of the resource ends
	 * @param resource
	 * 		the reserved resource
	 * @param customer
	 * 		the customer / "owner" of the reservation
	 */
	public Reservation(LocalDateTime startTime, LocalDateTime endTime, Resource resource, Customer customer) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.resource = resource;
		this.customer = customer;

	}

	/**
	 * getCustomer method
	 * @return customer
	 * 		the customer / "owner" of the reservation
	 */
	public Customer getCustomer() {
		return customer;
	}


	/**
	 * getEndTime method
	 * @return endTime
	 * 		the end time of the reservation
	 */
	public LocalDateTime getEndTime() {
		return endTime;
	}

	/**
	 * getResource method
	 * @return resource
	 * 		the reserved resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * getStartTime method
	 * @return startTime
	 * 		the start time of the reservation
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}

	/**
	 * setCustomer method
	 * @param customer
	 * 		the new customer, should not be null or empty
	 * this method sets the customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * setEndTime method
	 *
	 * @param endTime
	 * 		the new end time, should not be null or empty and "bigger" than endTime
	 * this method sets the EndTime
	 */
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	/**
	 * setResource method
	 * @param resource
	 * 		the new resource, should not be null or empty
	 * this method sets the resource
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * setStartTime method
	 * @param startTime
	 * 		the new startTime, should not be null or empty and "smaller" than endTime
	 * this method sets the startTime
	 */
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

}
