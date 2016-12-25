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
 * Class Reservation which handles one reservation and which has all data of one
 * (this) reservation.
 * @author team-green
 */
public class Reservation {

	private LocalDateTime startTime, endTime;

	private Resource resource;
	private Customer customer;

	/**
	 * Constructor which initialize all necessary data for a reservation.
	 * 
	 * @param startTime
	 * @param endTime
	 * @param resource
	 * @param customer
	 */
	public Reservation(LocalDateTime startTime, LocalDateTime endTime, Resource resource, Customer customer) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.resource = resource;
		this.customer = customer;

	}

	/**
	 * Method returns the Customer which made this Reservation.
	 * @return
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Returns the Date and the End-Time of this Reservation.
	 * @return
	 */
	public LocalDateTime getEndTime() {
		return endTime;
	}

	/**
	 * returns the Resource(Room) of this Reservation.
	 * @return
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * Returns the Date and the Start-Time of this Reservation.
	 * @return
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}
	/**
	 * Sets the given Customer to this reservation.
	 * @param endTime
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Sets the end Time to this reservation.
	 * @param endTime
	 */
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	/**
	 * Sets the given Resource to this Reservation.
	 * @param resource
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * Sets the given StartTime to this Reservation.
	 * @param startTime
	 */
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

}
