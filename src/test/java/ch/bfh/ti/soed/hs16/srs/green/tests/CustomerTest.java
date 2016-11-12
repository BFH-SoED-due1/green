package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.model.Customer;


public class CustomerTest {

	@Test
	public void createCustumerTest() {
		Customer u1 = new Customer("Sven", "sven@email.ch", "12AB");
		assertNotNull(u1);
	}

}
