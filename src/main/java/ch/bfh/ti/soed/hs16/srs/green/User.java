/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {

	private final String userName;
	private String department;
	private final String ahv;
	private Set<Reservation> bookedReservation;

	User(String userName, String ahv) {
		this.ahv = ahv;
		this.userName = userName;
		bookedReservation = new HashSet<>();

	}

	public void addReservationsMadeByUser(Reservation reservation) {
		bookedReservation.add(reservation);
	}

	public String getAhv() {
		return ahv;
	}

	public String getDepartment() {
		return department;
	}

	public Set<Reservation> getReservations() {
		return Collections.unmodifiableSet(bookedReservation);
	}

	public String getUserName() {
		return userName;
	}

	public void makeReservation(Date date, Room room) throws Exception {
		Reservation reserv = new Reservation(date, room, this);
		bookedReservation.add(reserv);
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
