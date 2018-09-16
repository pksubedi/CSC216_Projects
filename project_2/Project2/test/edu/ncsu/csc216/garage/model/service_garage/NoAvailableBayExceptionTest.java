package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests NoAvailableBayException
 * @author Prem Subedi 
 */
public class NoAvailableBayExceptionTest {

	/**
	 * tests NoAvailableBayException().
	 */
	@Test
	public void testNoAvailableBayException() {
		NoAvailableBayException nvbe = new NoAvailableBayException();
		assertEquals("No appropriate bays opened.", nvbe.getMessage());

	}

	/**
	 * tests NoAvailableBayException(String).
	 */
	@Test
	public void testNoAvailableBayExceptionString() {
		NoAvailableBayException nvbe = new NoAvailableBayException("Custom exception message");
		assertEquals("Custom exception message", nvbe.getMessage());

	}

}
