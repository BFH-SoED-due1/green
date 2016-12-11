/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.model;

public class SystemAdmin extends ResourceManager {

	// TODO Unused fields; harmonize with super classes
	private String userName, prename, lastname, email, pw;

	public SystemAdmin(String userName, String pw) {
		super(userName, pw);
	}

	// public ResourceManager createRescourceManager(Customer user){
	// return (ResourceManager) user;
	// }

	public ResourceManager createRescourceManager(String userName, String pw) {
		return new ResourceManager(userName, pw);
	}

}
