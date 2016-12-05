package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.db.ResourceDB;

public class ResourceDBTest {
	
	@Test
	public void addResourceTest()throws Exception{
		
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);
		assertNotNull(ResourceDB.getResources());
		
	}
	 
	@Test
	public void getAmountRoomsTest() throws Exception {
		assertTrue(ResourceDB.getAmountRooms() > 0 );
	}

}
