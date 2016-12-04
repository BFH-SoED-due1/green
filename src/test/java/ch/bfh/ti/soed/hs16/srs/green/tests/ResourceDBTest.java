package ch.bfh.ti.soed.hs16.srs.green.tests;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.db.ResourceDB;

import static org.junit.Assert.*;

public class ResourceDBTest {
	
	@Test
	public void addResourceTest()throws Exception{
		
		ResourceDB.addResource("RoomTest2", "RoomLocationTest2", 44);
		assertNotNull(ResourceDB.getResources());
		
	}

}
