package ch.bfh.ti.soed.hs16.srs.green.tests;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.db.DBConnector;

public class DBConnectorTest {
	@Test
	public void testConnection() throws Throwable {
		DBConnector.connectDB();
		DBConnector.disconnectDB();
	}

	@Test
	public void testDeleteContent() throws Throwable {
		DBConnector.connectDB();
		DBConnector.delteContentOfTabels();
		DBConnector.disconnectDB();
	}

}
