///*
// * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
// *
// * Project Smart Reservation System.
// *
// * Distributable under GPL license. See terms of license at gnu.org.
// */
//package ch.bfh.ti.soed.hs16.srs.green.tests;
//
//import static org.junit.Assert.assertNotNull;
//
//import org.junit.Test;
//
//import ch.bfh.ti.soed.hs16.srs.green.model.ResourceManager;
//import ch.bfh.ti.soed.hs16.srs.green.utility.BigArgumentException;
//import ch.bfh.ti.soed.hs16.srs.green.utility.SmallArgumentException;
//
//public class ResourceManangerTest {
//
//	@Test
//	public void chreatNewResourceManager() {
//		ResourceManager rm1 = new ResourceManager("Sven", "sven@email.ch", "12AB");
//		assertNotNull(rm1);
//	}
//
//	@Test
//	public void createNewResource() throws Exception{
//		ResourceManager rm1 = new ResourceManager("Sven", "sven@email.ch", "12AB");
//		rm1.createResource("Room", 20, "Valhalla");
//	}
//
//	@Test (expected = BigArgumentException.class)
//	public void createNewResourceToLargeName() throws Exception{
//		ResourceManager rm1 = new ResourceManager("Sven", "sven@email.ch", "12AB");
//		rm1.createResource("ThisIsAVeryLongLongLongRoomName", 20, "Valhalla");
//	}
//
//	@Test (expected = SmallArgumentException.class)
//	public void createNewResourceUnderMinSize() throws Exception{
//		ResourceManager rm1 = new ResourceManager("Sven", "sven@email.ch", "12AB");
//		rm1.createResource("Room", -1, "Valhalla");
//	}
//
//}
