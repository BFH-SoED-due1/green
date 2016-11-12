/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package ch.bfh.ti.soed.hs16.srs.green.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import ch.bfh.ti.soed.hs16.srs.green.model.Resource;
import ch.bfh.ti.soed.hs16.srs.green.utility.BigArgumentException;
import ch.bfh.ti.soed.hs16.srs.green.utility.SmallArgumentException;

public class ResourceTest {


	@Test
	public void changeAResourcesLocationTest() throws BigArgumentException, SmallArgumentException {
		Resource res = new Resource("R2.1", 20, "Quellweg 21");
		String loc = res.getLocation();
		res.setLocation("QW21");
		assertNotSame(loc,res.getLocation());
	}

	@Test
	public void changeAResourcesNameTest() throws SmallArgumentException, BigArgumentException {
		Resource res = new Resource("R2.1", 20, "Quellweg 21");
		String name = res.getName();
		res.setName("QW21");
		assertNotSame(name,res.getName());
	}

	@Test
	public void changeAResourcesSizeTest() throws SmallArgumentException, BigArgumentException {
		Resource res = new Resource("R2.1", 20, "Quellweg 21");
		int size = res.getSize();
		res.setSize(25);
		assertNotSame(size,res.getSize());
	}

	@Test
	public void generateAResourceTest() throws SmallArgumentException, BigArgumentException {
		Resource res = new Resource("R1.1", 20, "Quellweg 21");
		assertNotNull(res);
	}

	@Test
	public void twoResourcesTwoUIDTest() throws SmallArgumentException, BigArgumentException {
		Resource res = new Resource("R2.1", 20, "Quellweg 21");
		Resource res2 = new Resource("R2.1", 20, "Quellweg 21");
		assertNotSame(res.getUID(), res2.getUID());
	}


}
