/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.time.LocalDateTime;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.green.model.Resource;

public class ReservationTest {


	@Test
	public void checkAGeneratedReservationsEndTimeTest() throws Exception  {
		Customer u1 = new Customer("Sven", "sven@email.ch", "12AB");
		Resource r1 = new Resource("R1.1", 20, "Quellweg 21");
		LocalDateTime endTime = LocalDateTime.of(2016, 3, 3, 12, 30);
		Reservation res = new Reservation(LocalDateTime.of(2016, 3, 3, 12, 15),
				endTime, r1, u1);
		assertEquals(res.getEndTime(), endTime);
	}

	@Test
	public void checkAGeneratedReservationsRoomTest() throws Exception  {
		Customer u1 = new Customer("Sven", "sven@email.ch", "12AB");
		Resource r1 = new Resource("R1.1", 20, "Quellweg 21");
		Reservation res = new Reservation(LocalDateTime.of(2016, 3, 3, 12, 15),
				LocalDateTime.of(2016, 3, 3, 12, 30), r1, u1);
		assertEquals(res.getResource(), r1);
	}

	@Test
	public void checkAGeneratedReservationsStartTimeTest() throws Exception {
		Customer u1 = new Customer("Sven", "sven@email.ch", "12AB");
		Resource r1 = new Resource("R1.1", 20, "Quellweg 21");
		LocalDateTime startTime = LocalDateTime.of(2016, 3, 3, 12, 15);
		Reservation res = new Reservation(startTime,
				LocalDateTime.of(2016, 3, 3, 12, 30), r1, u1);
		assertEquals(res.getStartTime(), startTime);
	}

	@Test
	public void checkAGeneratedReservationsUserTest() throws Exception {
		Customer u1 = new Customer("Sven", "sven@email.ch", "12AB");
		Resource r1 = new Resource("R1.1", 20, "Quellweg 21");
		Reservation res = new Reservation(LocalDateTime.of(2016, 3, 3, 12, 15),
				LocalDateTime.of(2016, 3, 3, 12, 30), r1, u1);
		assertEquals(res.getCustomer(), u1);
	}

	@Test
	public void compareTwoDifferentReservations() throws Exception {
		Customer u1 = new Customer("Sven", "sven@email.ch", "12AB");
		Resource r1 = new Resource("R1.1", 20, "Quellweg 21");
		Reservation res = new Reservation(LocalDateTime.of(2016, 3, 3, 12, 15),
				LocalDateTime.of(2016, 3, 3, 12, 30), r1, u1);
		Reservation res2 = new Reservation(LocalDateTime.of(2016, 3, 3, 12, 15),
				LocalDateTime.of(2016, 3, 3, 12, 30), r1, u1);
		assertNotSame(res,res2);
	}

	@Test
	public void generateAReservationTest() throws Exception {
		Customer u1 = new Customer("Sven", "sven@email.ch", "12AB");
		Resource r1 = new Resource("R1.1", 20, "Quellweg 21");
		Reservation res = new Reservation(LocalDateTime.of(2016, 3, 3, 12, 15),
				LocalDateTime.of(2016, 3, 3, 12, 30), r1, u1);
		assertNotNull(res);
	}


}