/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

import ch.bfh.ti.soed.hs16.srs.green.utility.BigArgumentException;
import ch.bfh.ti.soed.hs16.srs.green.utility.SmallArgumentException;

public class ResourceManager extends Customer {

	public ResourceManager(String userName, String pw) {
		super(userName, pw);
	}

	public Resource createResource(String resourceName, int resourceSize, String resourceLocation) throws Exception {
		if (resourceSize <= 0)
			throw new SmallArgumentException(1);
		else if (resourceName.length() > 20)
			throw new BigArgumentException(20);
		else
			return new Resource(resourceName, resourceSize, resourceLocation);
	}

}
