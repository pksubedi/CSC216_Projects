package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * tests Garage
 * 
 * @author someshherath
 *
 */
public class GarageTest {

	/**
	 * tests Garage
	 */
	@Test
	public void testGarage() {
		Garage ga = new Garage();
		assertEquals(8, ga.getSize());

		assertEquals("1", ga.getBayAt(0).toString().charAt(0) + "");
		assertEquals("1", ga.getBayAt(1).toString().charAt(0) + "");
		assertEquals("1", ga.getBayAt(2).toString().charAt(0) + "");
		assertEquals("1", ga.getBayAt(3).toString().charAt(0) + "");
		assertEquals("1", ga.getBayAt(4).toString().charAt(0) + "");
		assertEquals("E", ga.getBayAt(5).toString().charAt(0) + "");
		assertEquals("E", ga.getBayAt(6).toString().charAt(0) + "");
		assertEquals("E", ga.getBayAt(7).toString().charAt(0) + "");

		assertEquals(8, ga.numberOfEmptyBays());
		try{
			ga.getBayAt(8);
		} catch(NullPointerException e) {
			assertEquals(8, ga.numberOfEmptyBays());
		}

		ga.addRepairBay();
		assertEquals("E", ga.getBayAt(8).toString().charAt(0) + "");
		ga.addRepairBay();
		assertEquals("E", ga.getBayAt(9).toString().charAt(0) + "");
		assertEquals(10, ga.numberOfEmptyBays());

		assertEquals(null, ga.release(0));

		Vehicle c;

		try {
			c = new RegularCar("license", "name", 1);

			try {
				c.pickServiceBay(ga);
				assertEquals(true, ga.getBayAt(0).isOccupied());
				assertEquals(false, ga.getBayAt(3).isOccupied());
				assertEquals(false, ga.getBayAt(5).isOccupied());
				assertEquals(false, ga.getBayAt(2).isOccupied());
				
				assertEquals(false, ga.getBayAt(1).isOccupied());
				assertNull(ga.release(1));
				assertEquals(false, ga.getBayAt(1).isOccupied());
			} catch (NoAvailableBayException e) {
				fail();
			}
		} catch (BadVehicleInformationException e) {
			fail();
		}
		
		try {
			c = new HybridElectricCar("license2", "name2", 2);

			try {
				c.pickServiceBay(ga);
				assertEquals(false, ga.getBayAt(1).isOccupied());
				assertTrue(ga.getBayAt(0).isOccupied());
				assertEquals(false, ga.getBayAt(3).isOccupied());
				assertEquals(false, ga.getBayAt(5).isOccupied());
				
				assertEquals(true, ga.getBayAt(9).isOccupied());
				assertEquals(c, ga.release(9));
				assertEquals(false, ga.getBayAt(9).isOccupied());
			} catch (NoAvailableBayException e) {
				fail();
			}
		} catch (BadVehicleInformationException e) {
			fail();
		}

	}
}
