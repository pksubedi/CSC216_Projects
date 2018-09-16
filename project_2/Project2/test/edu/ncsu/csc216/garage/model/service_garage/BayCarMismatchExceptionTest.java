package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests BayCarMismatchException
 * 
 * @author Prem Subedi, Somesh Herath.
 *
 */
public class BayCarMismatchExceptionTest {

	/**
	 * tests BayCarMismatchException(String )
	 */
	@Test
	public void testBayCarMismatchExceptionString() {
		BayCarMismatchException bcexc = new BayCarMismatchException("Custom exception message");
		assertEquals("Custom exception message", bcexc.getMessage());
	}

	/**
	 * Test method for BayCarMismatchException() method.
	 */
	@Test
	public void testBayCarMismatchException() {
		BayCarMismatchException bce = new BayCarMismatchException();
		assertEquals("Wrong bay.", bce.getMessage());
	}
}
