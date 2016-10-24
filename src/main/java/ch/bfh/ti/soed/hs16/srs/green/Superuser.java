//package ch.bfh.ti.soed.hs16.srs.green;;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//public class Superuser extends User{
//
//	private static Set<Room> availableRooms;
//
//	{
//		availableRooms = new HashSet<>();
//		Room r1 = new Room(10, 10, null, this);
//		Room r2 = new Room(20, 20, null, this);
//		Room r3 = new Room(30, 30, null, this);
//		Room r4 = new Room(40, 40, null, this);
//
//	}
//
//	public static Set<Room> getRooms() {
//		return Collections.unmodifiableSet(availableRooms);
//	}
//
//	public static void addRoom(Room room) {
//		availableRooms.add(room);
//	}
//
//	public void removeRoom(Room room) {
//		availableRooms.remove(room);
//	}
//
//}
