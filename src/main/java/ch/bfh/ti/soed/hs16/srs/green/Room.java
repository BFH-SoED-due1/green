/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room {
	private static Set<Room> availableRooms;

	static {
		availableRooms = new HashSet<>();
		Room r1 = new Room(10, 10, null);
		Room r2 = new Room(20, 20, null);
		Room r3 = new Room(30, 30, null);
		Room r4 = new Room(40, 40, null);
		availableRooms.add(r1);
		availableRooms.add(r2);
		availableRooms.add(r3);
		availableRooms.add(r4);

	}

	public static void addRoom(Room room) {
		availableRooms.add(room);
	}
	public static Set<Room> getRooms() {
		return Collections.unmodifiableSet(availableRooms);
	}
	private final int roomNumber;
	private final int size;

	private List<String> materials;

	private Set<Reservation> reservations = new HashSet<>();

	Room(int roomNumber, int size, List<String> materials) {

		this.roomNumber = roomNumber;
		this.size = size;
		this.materials = materials;
		availableRooms.add(this);

	}

	public void addReservation(Reservation e) {
		reservations.add(e);
	}

	public List<String> getMaterials() {
		return materials;
	}

	public Set<Reservation> getReservations() {
		return Collections.unmodifiableSet(reservations);
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void getSchedules() {

	}

	public int getSize() {
		return size;
	}

	public void removeRoom(Room room) {
		availableRooms.remove(room);
	}

	public void setMaterials(List<String> materials) {
		this.materials = materials;
	}

}
