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

	public static void registerCustomer(String name, String email, String pw) throws Exception {

		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("FORNAME");
			c = DriverManager.getConnection("jdbc:sqlite:customer.db");
			System.out.println("DRIVERMANAGER");

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT ID FROM CUSTOMER;");

			while (rs.next())
				ID = rs.getInt("id") + 1;

			rs.close();

			String sql = "INSERT INTO CUSTOMER (ID,NAME,EMAIL,PW) " + "VALUES (" + ID++ + ", '" + name + "', '" + email
					+ "', '" + pw + "');";
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
			c = DriverManager.getConnection("jdbc:sqlite:customer.db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT NAME, EMAIL, PW FROM CUSTOMER;");

			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String pw = rs.getString("pw");
				customers.add(new Customer(name, email, pw));
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