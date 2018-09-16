package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * tests RegularCar
 * 
 * @author someshherath
 * @author Prem Subedi
 */
public class RegularCarTest {

	/**
	 * tests RegularCar
	 */
	@Test
	public void testRegularCar() {
		Garage ga = new Garage();
		assertEquals(8, ga.getSize());

		Vehicle c = null;

		try {
			c = new RegularCar("license", "name", 5);
			fail();
		} catch (BadVehicleInformationException e1) {
			assertNull(c);
		}
		try {
			c = new RegularCar("license", "name", 1);

			try {
				c.pickServiceBay(ga);
				assertTrue(ga.getBayAt(0).isOccupied());
				c.pickServiceBay(ga);
				assertTrue(ga.getBayAt(1).isOccupied());
				c.pickServiceBay(ga);
				assertTrue(ga.getBayAt(2).isOccupied());
				c.pickServiceBay(ga);
				assertTrue(ga.getBayAt(3).isOccupied());
				c.pickServiceBay(ga);
				assertTrue(ga.getBayAt(4).isOccupied());
				c.pickServiceBay(ga);
				assertFalse(ga.getBayAt(5).isOccupied());
			} catch (NoAvailableBayException e) {
				assertEquals(8, ga.getSize());	
			}
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		//Testing to add if the car 5 goes to bay 5.
		

		// Testing meetsFilter method.
		// @author Prem Subedi.
		Vehicle rCar = null;
		try {
			rCar = new RegularCar("ZSE-2982", "Jackquilene Fernandez", 2);
		} catch (BadVehicleInformationException e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(rCar.meetsFilter("  Ja"));
		assertFalse(rCar.meetsFilter(" Prem "));
		assertTrue(rCar.meetsFilter(""));
		assertTrue(rCar.meetsFilter(null));
		assertEquals(2, rCar.getTier());
		assertEquals(1, rCar.compareToTier(c));
		assertEquals(-1, c.compareToTier(rCar));

		// Trying to construct a vehicle with invalid information like empty
		// license.
		// @author Prem Subedi
		Vehicle hondaAccord = null;
		try {
			hondaAccord = new RegularCar("", "Pabitra Baral", 2);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be blank.", e.getMessage());
			assertNull(hondaAccord);
		}

		// Trying to construct vehicle with invalid information like license
		// more than
		// 8 characters. (@author Prem Subedi)
		Vehicle myNissanRogue = null;
		try {
			myNissanRogue = new RegularCar("AKA-42424167", "Prem Subedi", 1);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be more than 8 characters.", e.getMessage());
			assertNull(myNissanRogue);
		}

		// Trying to construct a vehicle with invalid information
		// like empty owner name, (@author Prem Subedi)
		Vehicle myRogue = null;
		try {
			myRogue = new RegularCar("AKA-4241", "", 1);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("Owner name cannot be blank.", e.getMessage());
			assertNull(myRogue);
		}

	}

	/**
	 * tests RegularCar toString
	 */
	@Test
	public void testToString() {
		Vehicle regCar = null;
		try {
			regCar = new RegularCar("ABE-1988", "Shyam Gyawali", 2);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		String s = "R Gold      ABE-1988  Shyam Gyawali";
		assertEquals(s, regCar.toString());

	}
}
