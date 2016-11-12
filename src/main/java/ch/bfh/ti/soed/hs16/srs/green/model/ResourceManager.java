/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package ch.bfh.ti.soed.hs16.srs.green.model;

import ch.bfh.ti.soed.hs16.srs.green.controller.ResourceService;

public class ResourceManager extends Customer{

	private String userName, email, pw;
	private ResourceService resourceS;

	public ResourceManager(String userName, String email, String pw) {
		super(userName, email, pw);
	}

}
