package ch.bfh.ti.soed.hs16.srs.green.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.soed.hs16.srs.green.model.Customer;
import ch.bfh.ti.soed.hs16.srs.green.model.Reservation;
import ch.bfh.ti.soed.hs16.srs.green.model.Resource;

public class ReservationDB {

	private static Connection c = null;
	private static Statement stmt = null;
	private static int ID;

	public static void addReservation(LocalDateTime startTime, LocalDateTime endTime, Resource resource,
			Customer customer) throws Exception {

		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("FORNAME");
			c = DriverManager.getConnection("jdbc:sqlite:srs.db");
			System.out.println("DRIVERMANAGER");

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT RESERVATIONID FROM RESERVATIONS;");
			System.out.println("while von rs");
			while (rs.next())
				ID = rs.getInt("reservationid") + 1;
			System.out.println("resultset roidr");

			Statement forRoomID = c.createStatement();
			ResultSet roIDr = forRoomID.executeQuery("select roomid from resources where location = '"
					+ resource.getLocation() + "' and  roomName = '" + resource.getName() + "';");
			System.out.println(resource.getLocation() + " |||| " + resource.getName());
			System.out.println("hallo der Boss");
			int roID = roIDr.getInt("roomid");
			System.out.println(roID);
			String sql = "INSERT INTO RESERVATIONS (RESERVATIONID,STARTTIME, ENDTIME, ROOMID, USERNAME) " + "VALUES ("
					+ ID++ + ", '" + startTime + "', '" + endTime + "', '" + roID + "', '" + customer.getUserName()
					+ "');";
			System.out.println("CREATESTATEMENT");
			stmt.executeUpdate(sql);
			System.out.println("EXECUTEUPDATE");
			roIDr.close();
			rs.close();
			stmt.close();
			System.out.println("CLOSE");
			c.close();
			System.out.println("CLOSE");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static Set<Reservation> getReservations() {
		c = null;
		stmt = null;
		Set<Reservation> reservations = new HashSet<>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:srs.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT STARTTIME, ENDTIME, ROOMNAME, CUSTOMER FROM RESERVATIONS;");

			while (rs.next()) {

				LocalDateTime startTime = (LocalDateTime) rs.getObject("startTime");
				LocalDateTime endTime = (LocalDateTime) rs.getObject("endTime");
				Resource roomName = (Resource) rs.getObject("roomName");
				Customer customer = (Customer) rs.getObject("username");

				reservations.add(new Reservation(startTime, endTime, roomName, customer));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}

		return reservations;
	}

	public static Set<Reservation> getReservationMadeByCustomer(Customer customer) {
		c = null;
		stmt = null;
		Set<Reservation> reservations = new HashSet<>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:srs.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT STARTTIME, ENDTIME, ROOMID, USERNAME FROM RESERVATIONS WHERE USERNAME = '"
							+ customer.getUserName() + "';");

			while (rs.next()) {

				LocalDateTime startTime = LocalDateTime.parse(rs.getString("startTime"));
				LocalDateTime endTime = LocalDateTime.parse(rs.getString("endTime"));

				int roomID1 = rs.getInt("roomid");

				ResultSet resourceQ = c.createStatement()
						.executeQuery("SELECT * FROM RESOURCES WHERE ROOMID = " + roomID1 + ";");
				String roomN = resourceQ.getString("roomName");
				String loc = resourceQ.getString("location");
				int size = resourceQ.getInt("size");
				Resource resource = new Resource(roomN, size, loc);
				ResultSet customerQ = c.createStatement()
						.executeQuery("SELECT * FROM CUSTOMER WHERE USERNAME = '" + customer.getUserName() + "';");
				String cust = customerQ.getString("userName");
				String pren = customerQ.getString("prename");
				String last = customerQ.getString("lastname");
				String email = customerQ.getString("email");
				String pw = customerQ.getString("pw");
				Customer userName = new Customer(cust, pren, last, email, pw);
				reservations.add(new Reservation(startTime, endTime, resource, userName));

			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}

		return reservations;

	}

}
