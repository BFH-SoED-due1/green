package ch.bfh.ti.soed.hs16.srs.green;

import java.util.Date;
import java.util.Set;

public class Reservation {

	private Date date;
	// private Time time;
	private boolean periodic;
	private Room room;
	private final User user;

	Reservation(Date date, /* Time time */ Room room, User user) throws Exception {

		if (user == null)
			throw new Exception("Cannot make a Reservation, User must be needed");
		else {
			this.user = user;
			this.user.addReservationsMadeByUser(this);
		}

		if (!Room.getRooms().contains(room))
			throw new Exception("No such Room");

		this.room = room;
		this.date = date;

		if (isAvailable(room))
			room.addReservation(this);

		else
			throw new Exception("Date already booked!");

	}

	// public void setReservation(Room room) throws Exception {
	// if (isAvailable(room)) {
	// Reservation newRes = new Reservation(date, room, user);
	// room.addReservation(this);
	// user.addReservationsMadeByUser(this);
	// }
	//
	// else
	// throw new Exception("Room already booked at the desired time");
	// }

	public Date getDate() {
		return date;
	}

	public User getUser() {
		return user;
	}

	public boolean isPeriodic() {
		return periodic;
	}

	public void setPeriodic(boolean periodic) {
		this.periodic = periodic;
	}

	public Room getRoom() {
		return room;
	}

	public boolean isAvailable(Room room) {
		Set<Reservation> reservations = room.getReservations();
		if (reservations == null)
			return true;
		for (Reservation r : reservations)
			if (r.getDate().equals(date))
				return false;
		return true;

	}

}
