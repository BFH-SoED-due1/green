package ch.bfh.ti.soed.hs16.srs.green.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.soed.hs16.srs.green.model.Resource;

public class ResourceDB {

	private static Connection c = null;
	private static Statement stmt = null;
	private static int ID;

	public static void addResource(String roomName, String locatoin, int size) throws Exception {

		try {
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:srs.db");
			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT ROOMID FROM RESOURCES;");

			while (rs.next())
				ID = rs.getInt("roomid") + 1;

			rs.close();

			String sql = "INSERT INTO RESOURCES (ROOMID,ROOMNAME,LOCATION,SIZE) " + "VALUES (" + ID++ + ", '" + roomName
					+ "', '" + locatoin + "', '" + size + "');";
			
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}

	}

	public static int getAmountRooms() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:srs.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT ROOMID FROM RESOURCES;");

			while (rs.next())
				ID = rs.getInt("roomid");

			rs.close();
			stmt.close();
			c.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return ID;
	}

	public static Set<Resource> getResources() {
		
		c = null;
		stmt = null;
		Set<Resource> resources = new HashSet<>();
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:srs.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT ROOMNAME, LOCATION, SIZE FROM RESOURCES;");

			while (rs.next()) {
				String roomName = rs.getString("roomName");
				String location = rs.getString("location");
				int size = rs.getInt("size");
				resources.add(new Resource(roomName, size, location));
			}
			
			rs.close();
			stmt.close();
			c.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return resources;
	}

}
