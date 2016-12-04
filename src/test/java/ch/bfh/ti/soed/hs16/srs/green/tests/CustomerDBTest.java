package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.db.CustomerDB;
import ch.bfh.ti.soed.hs16.srs.green.model.Customer;

public class CustomerDBTest {

	@Test
	public void addCustomerTest() throws Exception {
		CustomerDB.registerCustomer("TestUserF", "TestP", "TestL", "test.test@msn.ch", "testPW");
		Set<Customer> customer = CustomerDB.getCustomers();
		assertNotNull(customer);

	}

	@Test
	public void registerCustomerTest() throws Exception{
		CustomerDB.registerCustomer("TestUserS", "TestP", "TestL", "test.test@msn.ch", "testPW");
		Set<Customer> customer = CustomerDB.getCustomers();
		Set<Customer> customerToCheck = new HashSet<>();
		for (Customer c : customer)
			if (c.getUserName().equals("TestUserS"))
				customerToCheck.add(c);
			
		assertEquals(customerToCheck.iterator().next().getUserName(), "TestUserS");
		
	}

}
