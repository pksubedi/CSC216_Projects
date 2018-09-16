package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests Vehicle
 * 
 * @author someshherath
 *
 */
public class VehicleTest {

	/**
	 * tests Vehicle
	 */
	@Test
	public void testVehicle() {
		Vehicle v = null;
		try {
			v = new RegularCar("ABE-1988", "Shyam Gyawali", 2);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		String s = "R Gold      ABE-1988  Shyam Gyawali";
		assertEquals(s, v.toString());

		assertEquals(0, v.compareToTier(v));
	}

}
