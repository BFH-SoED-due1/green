/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

import java.time.LocalDateTime;

public class Reservation {

	private LocalDateTime startTime, endTime;

	private Resource resource;
	private Customer customer;

	public Reservation(LocalDateTime startTime, LocalDateTime endTime, Resource resource, Customer customer) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.resource = resource;
		this.customer = customer;

	}

	public Customer getCustomer() {
		return customer;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public Resource getResource() {
		return resource;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

}