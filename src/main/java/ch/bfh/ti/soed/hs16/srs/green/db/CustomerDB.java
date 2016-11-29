package ch.bfh.ti.soed.hs16.srs.green.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ch.bfh.ti.soed.hs16.srs.green.model.Customer;

public class CustomerDB {

	private static Connection c = null;
	private static Statement stmt = null;
	private static int ID;

	public static void registerCustomer(String userName, String preName, String lastName, String email, String pw)
			throws Exception {

		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("FORNAME");
			c = DriverManager.getConnection("jdbc:sqlite:srs.db");
			System.out.println("DRIVERMANAGER");

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT CUSTOMERID FROM CUSTOMER;");

			while (rs.next())
				ID = rs.getInt("customerid") + 1;

			rs.close();

			String sql = "INSERT INTO CUSTOMER (CUSTOMERID,USERNAME,PRENAME, LASTNAME, EMAIL, PW) " + "VALUES (" + ID++
					+ ", '" + userName + "', '" + preName + "', '" + lastName + "', '" + email + "', '" + pw + "');";
			System.out.println("CREATESTATEMENT");
			stmt.executeUpdate(sql);
			System.out.println("EXECUTEUPDATE");

			stmt.close();
			System.out.println("CLOSE");
			c.close();
			System.out.println("CLOSE");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static Set<Customer> getCustomers() {
		c = null;
		stmt = null;
		Set<Customer> customers = new HashSet<>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:srs.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT USERNAME, PRENAME, LASTNAME, EMAIL, PW FROM CUSTOMER;");

			while (rs.next()) {
				String userName = rs.getString("username");
				String preName = rs.getString("prename");
				String lastName = rs.getString("lastname");
				String email = rs.getString("email");
				String pw = rs.getString("pw");
				customers.add(new Customer(userName, preName, lastName, email, pw));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}

		return customers;
	}

}
