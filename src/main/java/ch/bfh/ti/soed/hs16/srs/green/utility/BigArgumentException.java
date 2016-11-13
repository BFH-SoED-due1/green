
/*
 * Copyright (c) 2016 Berner Fachhochschule, Switzerland.
 *
 * Project Smart Reservation System.
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */
package ch.bfh.ti.soed.hs16.srs.green.utility;

public class BigArgumentException extends Exception {

	public BigArgumentException (int max) {
		super("Maximum: " + max);
		System.out.println(getMessage());
	}

}