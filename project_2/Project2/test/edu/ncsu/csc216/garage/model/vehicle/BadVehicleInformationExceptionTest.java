package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * tests BadVehicleInformationException
 * @author premsubedi 
 * @author someshherath
 *
 */
public class BadVehicleInformationExceptionTest {
	
	/**
	 * tests BadVehicleInformationException()
	 */
	@Test
	public void testBadVehicleInformationException() {
		BadVehicleInformationException bvie = new BadVehicleInformationException();
		assertEquals("Invalid Vehicle Information.", bvie.getMessage());
	}

	/**
	 * tests BadVehicleInformationException(String)
	 */
	@Test
	public void testBadVehicleInformationExceptionString() {
		BadVehicleInformationException bvie = new BadVehicleInformationException("Custom exception message");
		assertEquals("Custom exception message", bvie.getMessage());
	}
}
