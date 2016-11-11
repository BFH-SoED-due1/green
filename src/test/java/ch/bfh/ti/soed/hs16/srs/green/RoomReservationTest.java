package ch.bfh.ti.soed.hs16.srs.green;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.SystemAdmin.RoomManager;
import ch.bfh.ti.soed.hs16.srs.green.SystemAdmin.RoomManager.Room;


public class RoomReservationTest {

	@Test
	public void testUserReservation() {
		Customer sven = new Customer("Sven", "123.123.122");
		Set<Reservation> reservSven = sven.getReservations();
		assertNotNull(reservSven);

	}

	@Test
	public void testGetUsername() {
		Customer sven = new Customer("Sven", "123.123.122");
		String user = sven.getUserName();
		assertEquals("Sven", user);
	}

	@Test
	public void testAHVNumber() {
		Customer sven = new Customer("Sven", "123.123.122");
		String ahv = sven.getAhv();
		assertEquals("123.123.122", ahv);

	}

	@Test
	public void testNewRoom() {
		SystemAdmin sa = new SystemAdmin("svenDrBoss", "666666");
		RoomManager marcoDubu = sa.createResourceManager("marcoDrDubu", "000000");
		marcoDubu.createRoom(10, 10, null);
		assertEquals(marcoDubu.lookupRooms().size(), SystemAdmin.RoomManager.Room.getRooms().size());
	}

	@Test
	public void testAddRoomToReservation() throws Exception {
		SystemAdmin sa = new SystemAdmin("svenDrBoss", "666666");
		RoomManager marcoDubu = sa.createResourceManager("marcoDrDubu", "000000");
		Room r1 = marcoDubu.createRoom(10, 10, null);
		Customer u1 = new Customer("Sven", "123.123.122");
		Date d1 = new GregorianCalendar(2016, 10, 18).getTime();
		u1.makeReservation(d1, r1);
		Set<Reservation> reservation = r1.getReservations();
		assertNotNull(reservation);
	}

	@Test(expected = Exception.class)
	public void testReserveRoomAtTheSameTime() throws Exception {
		Customer u1 = new Customer("Sven", "123.123.122");
		SystemAdmin sa = new SystemAdmin("svenDrBoss", "666666");
		RoomManager marcoDubu = sa.createResourceManager("marcoDrDubu", "000000");
		Room r1 = marcoDubu.createRoom(10, 10, null);
		Date d1 = new GregorianCalendar(2016, 10, 18).getTime();
		Date d2 = new GregorianCalendar(2016, 10, 18).getTime();
		u1.makeReservation(d1, r1);
		u1.makeReservation(d2, r1); // Exception expected

	}

	@Test
	public void testRoomNumber() throws Exception {
		Customer u1 = new Customer("sven", "23.433.2");
		SystemAdmin sa = new SystemAdmin("svenDrBoss", "666666");
		RoomManager marcoDubu = sa.createResourceManager("marcoDrDubu", "000000");
		Room r1 = marcoDubu.createRoom(10, 10, null);
		u1.makeReservation(new Date(), r1);
		Set<Reservation> reservations = u1.getReservations();
		List<Reservation> listOfReservations = new ArrayList<>(reservations);
		assertEquals(listOfReservations.get(0).getRoom().getRoomNumber(), 10);
	}

	@Test
	public void testSizeOfReservationUserRoom() throws Exception {
		Customer u1 = new Customer("Sven", "123.12.333");
		SystemAdmin sa = new SystemAdmin("svenDrBoss", "666666");
		RoomManager marcoDubu = sa.createResourceManager("marcoDrDubu", "000000");
		Room r1 = marcoDubu.createRoom(10, 10, null);
		u1.makeReservation(new Date(), r1);
		assertEquals(u1.getReservations().size(), r1.getReservations().size());

	}

	@Test
	public void testRoomManager() {

		SystemAdmin sa = new SystemAdmin("AdminSvenBoss", "1234567");
		RoomManager rm = sa.createResourceManager("MarcoDubu", "445664");
		assertEquals(rm.getUserName(), "MarcoDubu");

	}

	@Test
	public void testSystemAdmin() {
		SystemAdmin sa = new SystemAdmin("AdminSvenBoss", "1234567");
		RoomManager rm = sa.createResourceManager("MarcoDubu", "445664");
		assertEquals(SystemAdmin.getRoomManagers().size(), 7);
		sa.deleteResourceManager(rm);
		assertEquals(SystemAdmin.getRoomManagers().size(), 6);

	}

}
