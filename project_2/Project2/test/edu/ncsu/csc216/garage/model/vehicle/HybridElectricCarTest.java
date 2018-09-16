package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * tests HybridElectricCar
 * 
 * @author someshherath
 *
 */
public class HybridElectricCarTest {

	/**
	 * tests HybridElectricCar
	 */
	@Test
	public void testHybridElectricCar() {
		Garage ga = new Garage();
		assertEquals(8, ga.getSize());

		Vehicle r = null;
		Vehicle c = null;
		try {
			 r = new HybridElectricCar("license2", "name2", 2);
			 c = new HybridElectricCar("license", "name", 1);

			try {
				r.pickServiceBay(ga);
				r.pickServiceBay(ga);
				r.pickServiceBay(ga);
				r.pickServiceBay(ga);
				r.pickServiceBay(ga);
				c.pickServiceBay(ga);
				c.pickServiceBay(ga);
				c.pickServiceBay(ga);
				c.pickServiceBay(ga);
				//fail();
			} catch (NoAvailableBayException e) {
				assertEquals(8, ga.getSize());
			}
		} catch (BadVehicleInformationException e) {
			fail();
		}

		// Testing meetsFilter method.
		// @author Prem Subedi.
		Vehicle rCar = null;
		try {
			rCar = new HybridElectricCar("ZSE-2982", "Jackquilene Fernandez", 2);
		} catch (BadVehicleInformationException e) {
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
			hondaAccord = new HybridElectricCar("", "Pabitra Baral", 2);
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
			myNissanRogue = new HybridElectricCar("AKA-42424167", "Prem Subedi", 1);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("License cannot be more than 8 characters.", e.getMessage());
			assertNull(myNissanRogue);
		}

		// Trying to construct a vehicle with invalid information
		// like empty owner name, (@author Prem Subedi)
		Vehicle myRogue = null;
		try {
			myRogue = new HybridElectricCar("AKA-4241", "", 1);
			fail();
		} catch (BadVehicleInformationException e) {
			assertEquals("Owner name cannot be blank.", e.getMessage());
			assertNull(myRogue);
		}
	}

	/**
	 * tests HybridElectricCar.toString().
	 */
	@Test
	public void testToString() {
		Vehicle heCar = null;
		try {
			heCar = new HybridElectricCar("ABE-1988", "Shyam Gyawali", 2);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		String s = "E Gold      ABE-1988  Shyam Gyawali";
		assertEquals(s, heCar.toString());

	}

}
