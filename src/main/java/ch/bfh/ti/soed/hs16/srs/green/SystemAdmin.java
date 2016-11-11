package ch.bfh.ti.soed.hs16.srs.green;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;





public class SystemAdmin extends Customer{

	public static class RoomManager extends Customer {

		private static Set<RoomManager> roomManagers = new HashSet<>();

		public static class Room {
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

			private final int roomNumber;
			private final int size;
			private List<String> materials;
			private Set<Reservation> reservations = new HashSet<>();

			private Room(int roomNumber, int size, List<String> materials) {

				this.roomNumber = roomNumber;
				this.size = size;
				this.materials = materials;
				availableRooms.add(this);

			}

			public int getRoomNumber() {
				return roomNumber;
			}

			public List<String> lookupMaterials() {
				return Collections.unmodifiableList(materials);
			}

			public void addReservation(Reservation e) {
				reservations.add(e);
			}

			public Set<Reservation> getReservations() {
				return Collections.unmodifiableSet(reservations);
			}

			public void getSchedules() {

			}

			public static Set<Room> getRooms() {
				return Collections.unmodifiableSet(availableRooms);
			}

			public static void addRoom(Room room) {
				availableRooms.add(room);
			}

			public void removeRoom(Room room) {
				availableRooms.remove(room);
			}

			public int getSize() {
				return size;
			}

		}

		private RoomManager(String userName, String ahv) {
			super(userName, ahv);
			roomManagers.add(this);

		}

		public Room createRoom(int roomNumber, int size, List<String> materials) {
			return new Room(roomNumber, size, materials);
		}

		public void addMaterials(Room r, String toAdd) {
			r.materials.add(toAdd);

		}

		public void removeMaterials(Room r, String toRemove) {

			r.materials.remove(toRemove);

		}

		public void deleteRoom(Room r) {
			Room.availableRooms.remove(r);
		}

		public Set<Reservation> getReservation(Room r) {
			return r.reservations;
		}

	}

	SystemAdmin(String userName, String ahv) {
		super(userName, ahv);

	}

	public RoomManager createResourceManager(String name, String ahv) {
		return new RoomManager(name, ahv);
	}

	public void deleteResourceManager(RoomManager rm) {
		RoomManager.roomManagers.remove(rm);
	}

	public static Set<RoomManager> getRoomManagers() {
		return RoomManager.roomManagers;

	}

}
