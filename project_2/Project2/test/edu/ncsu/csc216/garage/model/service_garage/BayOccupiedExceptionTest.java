package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests BayOccupiedExceptionTest
 * @author Prem Subedi 
 * @author someshherath
 *
 */
public class BayOccupiedExceptionTest {

	/**
	 * tests BayOccupiedException()
	 */
	@Test
	public void testBayOccupiedException() {
		BayOccupiedException boe = new BayOccupiedException();
		assertEquals("Service bay is occupied.", boe.getMessage());
	}

	/**
	 * tests BayOccupiedException(String)
	 */
	@Test
	public void testBayOccupiedExceptionString() {
		BayOccupiedException boe = new BayOccupiedException("Custom exception message");
		assertEquals("Custom exception message", boe.getMessage());

	}
}
