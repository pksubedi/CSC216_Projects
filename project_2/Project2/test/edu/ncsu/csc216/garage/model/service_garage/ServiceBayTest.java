package edu.ncsu.csc216.garage.model.service_garage;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * tests ServiceBay
 * 
 * @author someshherath
 *
 */
public class ServiceBayTest {

	/**
	 * tests ServiceBay
	 */
	@Test
	public void testServiceBay() {
		ServiceBay.startBayNumberingAt101();
		ServiceBay sbay = new ServiceBay();
		assertEquals("101", sbay.getBayID());

		Vehicle c;
		try {
			c = new RegularCar("license", "name", 1);

			try {
				sbay.occupy(c);
			} catch (BayOccupiedException e) {
				fail();
			} catch (BayCarMismatchException e) {
				fail();
			}

			assertEquals(true, sbay.isOccupied());
			assertEquals(c, sbay.release());
			assertEquals(false, sbay.isOccupied());

		} catch (BadVehicleInformationException e) {
			fail();
		}

		ServiceBay sbay2 = new ServiceBay("");
		assertEquals("102", sbay2.getBayID());

		try {
			c = new RegularCar("license", "name", 1);

			try {
				sbay2.occupy(c);
			} catch (BayOccupiedException e) {
				fail();
			} catch (BayCarMismatchException e) {
				fail();
			}

			assertEquals(true, sbay2.isOccupied());
			assertEquals(c, sbay2.release());
			assertEquals(false, sbay2.isOccupied());

		} catch (BadVehicleInformationException e) {
			fail();
		}

		ServiceBay sbay3 = new ServiceBay("L  ");
		assertEquals("L03", sbay3.getBayID());

		try {
			c = new RegularCar("license", "name", 1);

			try {
				sbay3.occupy(c);
			} catch (BayOccupiedException e) {
				fail();
			} catch (BayCarMismatchException e) {
				fail();
			}

			assertEquals(true, sbay3.isOccupied());
			assertEquals(c, sbay3.release());
			assertEquals(false, sbay3.isOccupied());

		} catch (BadVehicleInformationException e) {
			fail();
		}

		sbay = new ServiceBay("k  ");
		assertEquals("k04", sbay.getBayID());

		sbay = new ServiceBay(" J  ");
		assertEquals("J05", sbay.getBayID());

		sbay = new ServiceBay(" hj  ");
		assertEquals("h06", sbay.getBayID());

		sbay = new ServiceBay("  ");
		assertEquals("107", sbay.getBayID());

		sbay = new ServiceBay(" smn  ");
		assertEquals("s08", sbay.getBayID());

		sbay = new ServiceBay(" U");
		assertEquals("U09", sbay.getBayID());

		sbay = new ServiceBay(" 6  ");
		assertEquals("610", sbay.getBayID());

		sbay = new ServiceBay("  ");
		assertEquals("111", sbay.getBayID());

		sbay = new ServiceBay("");
		assertEquals("112", sbay.getBayID());

		try {
			c = new RegularCar("License", "Lastname, Firstname", 1);

			try {
				sbay.occupy(c);
				assertEquals("112: License  Lastname, Firstname", sbay.toString());
			} catch (BayOccupiedException e) {
				fail();
			} catch (BayCarMismatchException e) {
				fail();
			}

			try {
				sbay.occupy(c);
			} catch (BayOccupiedException e) {
				assertEquals(true, sbay.isOccupied());
				assertEquals(c, sbay.release());
				assertEquals("112: EMPTY", sbay.toString());
			} catch (BayCarMismatchException e) {
				fail();
			}
		} catch (BadVehicleInformationException e) {
			fail();
		}

	}
}
