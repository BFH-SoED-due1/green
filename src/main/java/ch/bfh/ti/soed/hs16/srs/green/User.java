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

	public String getUserName() {
		return userName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAhv() {
		return ahv;
	}

	public void makeReservation(Date date, Room room) throws Exception {
		Reservation reserv = new Reservation(date, room, this);
		bookedReservation.add(reserv);
	}

	public Set<Reservation> getReservations() {
		return Collections.unmodifiableSet(bookedReservation);
	}

	public void addReservationsMadeByUser(Reservation reservation) {
		bookedReservation.add(reservation);
	}

}
