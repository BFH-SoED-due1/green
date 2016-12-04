package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.db.ReservationDB;
import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Resource;

public class ReservationDBTest {

	@Test
	public void addReservationTest() throws Exception {
		ReservationDB.addReservation(LocalDateTime.of(2016, 12, 12, 12, 30), LocalDateTime.of(2016, 12, 12, 14, 00),
				new Resource("TestRoom", 20, "TestLoc"),
				new Customer("TestUser123", "teste", "testeNach", "teste@fff.ch", "testpw2"));
	
		assertNotNull(ReservationDB.getReservations());
	}
	
	

}
