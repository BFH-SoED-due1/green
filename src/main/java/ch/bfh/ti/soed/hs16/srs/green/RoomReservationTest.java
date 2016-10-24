package ch.bfh.ti.soed.hs16.srs.green;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class RoomReservationTest {

	@Test
	public void testUserReservation() {
		User sven = new User("Sven", "123.123.122");
		Set<Reservation> reservSven = sven.getReservations();
		assertNotNull(reservSven);

	}

	@Test
	public void testGetUsername() {
		User sven = new User("Sven", "123.123.122");
		String user = sven.getUserName();
		assertEquals("Sven", user);
	}

	@Test
	public void testAHVNumber() {
		User sven = new User("Sven", "123.123.122");
		String ahv = sven.getAhv();
		assertEquals("123.123.122", ahv);

	}

	@Test
	public void testNewRoom() {
		Room r1 = new Room(13, 11, null);
		assertNotNull(r1);
	}

	@Test
	public void testAddRoomToReservation() throws Exception {
		User u1 = new User("Sven", "123.123.122");
		Room r1 = new Room(13, 11, null);
		Date d1 = new GregorianCalendar(2016, 10, 18).getTime();
		u1.makeReservation(d1, r1);
		Set<Reservation> reservation = r1.getReservations();
		assertNotNull(reservation);
	}

	@Test(expected = Exception.class)
	public void testReserveRoomAtTheSameTime() throws Exception {
		User u1 = new User("Sven", "123.123.122");
		Room r1 = new Room(13, 11, null);
		Date d1 = new GregorianCalendar(2016, 10, 18).getTime();
		Date d2 = new GregorianCalendar(2016, 10, 18).getTime();
		u1.makeReservation(d1, r1);
		u1.makeReservation(d2, r1); // Exception expected

	}

	@Test
	public void testRoomNumber() throws Exception {
		User u1 = new User("sven", "23.433.2");
		u1.makeReservation(new Date(), new Room(9, 100, null));
		Set<Reservation> reservations = u1.getReservations();
		List<Reservation> listOfReservations = new ArrayList<>(reservations);
		assertEquals(listOfReservations.get(0).getRoom().getRoomNumber(), 9);
	}

	@Test
	public void testSizeOfReservationUserRoom() throws Exception {
		User u1 = new User("Sven", "123.12.333");
		Room r1 = new Room(10, 100, null);
		u1.makeReservation(new Date(), r1);
		assertEquals(u1.getReservations().size(), r1.getReservations().size());

	}

}
