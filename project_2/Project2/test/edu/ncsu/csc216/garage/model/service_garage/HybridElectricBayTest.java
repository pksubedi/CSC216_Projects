package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * tests HybridElectricBay
 * 
 * @author someshherath
 *
 */
public class HybridElectricBayTest {

	/**
	 * tests HybridElectricBay
	 */
	@Test
	public void testHybridElectricBayString() {
		ServiceBay.startBayNumberingAt101();
		ServiceBay ebay = new HybridElectricBay();
		
		assertEquals("E01", ebay.getBayID());

		Vehicle c;
		try {
			c = new HybridElectricCar("license2", "name2", 2);

			try {
				ebay.occupy(c);
			} catch (BayOccupiedException e) {
				fail();
			} catch (BayCarMismatchException e) {
				fail();
			}

			assertEquals(true, ebay.isOccupied());
			assertEquals(c, ebay.release());
			assertEquals(false, ebay.isOccupied());

		} catch (BadVehicleInformationException e) {
			fail();
		}
	}
}
