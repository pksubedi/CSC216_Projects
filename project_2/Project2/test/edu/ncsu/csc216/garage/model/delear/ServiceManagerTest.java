package edu.ncsu.csc216.garage.model.delear;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.garage.model.dealer.ServiceManager;
import edu.ncsu.csc216.garage.model.service_garage.ServiceBay;
import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Tiered;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * tests ServiceManager
 * 
 * @author someshherath
 * @author Prem Subedi
 */
public class ServiceManagerTest {

	/**
	 * tests ServiceManager
	 */
	@Test
	public void testServiceManager() {
		ServiceBay.startBayNumberingAt101();
		ServiceManager sm = new ServiceManager();
		String s = "108: EMPTY\n106: EMPTY\n105: EMPTY\n103: EMPTY\n"
				+ "102: EMPTY\nE01: EMPTY\nE04: EMPTY\nE07: EMPTY\n";
		assertEquals(s, sm.printServiceBays());

	}

	/**
	 * Tests ServiceManager(Scanner) constructor
	 */
	@Test
	public void testServiceManagerScanner() {
		Scanner input = null;
		try {
			input = new Scanner(new File("test-files/cars.txt"));

		} catch (FileNotFoundException e) {
			fail();
			e.getMessage();
		}

		ServiceManager sm = new ServiceManager(input);
		Tiered v = null;
		try {
			v = new HybridElectricCar("NC-5678", "Emerson, Jane", 2);
		} catch (BadVehicleInformationException e) {
			fail();
			e.printStackTrace();
		}
		assertEquals(v.toString(), sm.getWaitingItem("E", 0).toString());
	}

	/**
	 * Tests putOnWaitingList(String, String, String, int)
	 */
	@Test
	public void testPutOnWaitingListStringStringStringint() {
		ServiceManager smanager = new ServiceManager();
		smanager.putOnWaitingList("E", "ZSE2982", "Subedi, Prem", 0);
		Vehicle vcle = null;
		try {
			vcle = new HybridElectricCar("ZSE2982", "Subedi, Prem", 0);
		} catch (BadVehicleInformationException e) {
			fail();
			e.getMessage();
		}
		assertEquals(vcle.toString(), smanager.getWaitingItem("S", 0).toString());

		Vehicle rCar = null;
		try {
			rCar = new RegularCar("EKA4241", "Baral, Pabitra", 0);
		} catch (BadVehicleInformationException e) {
			fail();
			e.getMessage();
		}
		ServiceManager sm = new ServiceManager();
		sm.putOnWaitingList("R", "EKA4241", "Baral, Pabitra", 0);
		assertEquals(rCar.toString(), sm.getWaitingItem("B", 0).toString());

	}

	/**
	 * Tests putOnWaitingList(Tiered)
	 */
	@Test
	public void testPutOnWaitingListTiered() {
		ServiceManager smanager = new ServiceManager();

		Tiered vcle = null;
		try {
			vcle = new HybridElectricCar("ZSE2982", "Subedi, Prem", 2);
		} catch (BadVehicleInformationException e) {
			fail();
		}
		smanager.putOnWaitingList(vcle);
		assertEquals(vcle.toString(), smanager.getWaitingItem("S", 0).toString());
	}

	/**
	 * Tests getWaitingItems()
	 */
	@Test
	public void testGetWaitingItem() {
		ServiceManager smanager = new ServiceManager();
		smanager.putOnWaitingList("E", "ZSE2982", "Subedi, Prem", 2);
		Tiered vcle = null;
		try {
			vcle = new HybridElectricCar("ZSE2982", "Subedi, Prem", 2);
		} catch (BadVehicleInformationException e) {
			e.getMessage();
			fail();
		}
		assertEquals(vcle.toString(), smanager.getWaitingItem("S", 0).toString());
	}

	/**
	 * Tests remove().
	 */
	@Test
	public void remove() {
		ServiceManager smanager = new ServiceManager();
		smanager.putOnWaitingList("E", "ZSE2982", "Subedi, Prem", 2);
		Tiered vcle = null;
		try {
			vcle = new HybridElectricCar("ZSE2982", "Subedi, Prem", 2);
		} catch (BadVehicleInformationException e) {
			e.getMessage();
			fail();
		}
		assertEquals(vcle.toString(), smanager.remove("S", 0).toString());
	}

	/**
	 * Tests fillServiceBays().
	 */
	@Test
	public void testFillServiceBays() {
		Scanner input = null;
		try {
			input = new Scanner(new File("test-files/cars.txt"));
		} catch (FileNotFoundException e) {
			e.getMessage();
			fail();
		}
		ServiceManager sm = new ServiceManager(input);
		//System.out.println(sm.printWaitList(""));
		sm.fillServiceBays();
		assertTrue(sm.getGarage().getBayAt(0).isOccupied());
		assertTrue(sm.getGarage().getBayAt(1).isOccupied());
		assertTrue(sm.getGarage().getBayAt(3).isOccupied());
		assertTrue(sm.getGarage().getBayAt(4).isOccupied());

	}

	/**
	 * Tests releaseFromService().
	 */
	@Test
	public void testReleaseFromService() {
		Scanner input = null;
		try {
			input = new Scanner(new File("test-files/cars.txt"));
		} catch (FileNotFoundException e) {
			e.getMessage();
			fail();
		}
		ServiceManager sm = new ServiceManager(input);
		Vehicle v = (Vehicle) sm.releaseFromService(0);
		assertEquals(v, sm.getGarage().release(0));
	}

	/**
	 * Tests addNewBay().
	 */
	@Test
	public void testAddNewBay() {
		Scanner input = null;
		try {
			input = new Scanner(new File("test-files/cars.txt"));
		} catch (FileNotFoundException e) {
			e.getMessage();
			fail();
		}
		ServiceManager sm = new ServiceManager(input);
		assertEquals(8, sm.getGarage().getSize());
		sm.addNewBay();
		assertEquals(9, sm.getGarage().getSize());
		sm.addNewBay();
		assertEquals("E10", sm.getGarage().getBayAt(9).getBayID());

	}

	/**
	 * Tests printWaitingList.
	 */
	@Test
	public void testPrintWaitingList() {
		Scanner input = null;
		try {
			input = new Scanner(new File("test-files/cars.txt"));
		} catch (FileNotFoundException e) {
			e.getMessage();
			fail();
		}

		ServiceManager sm = new ServiceManager(input);
		assertEquals("E Gold      NC-5678   Emerson, Jane" + "\n", sm.printWaitList("E"));
	}

	/**
	 * Tests PrintServiceBays.
	 */
	@Test
	public void testPrintServiceBays() {
		Scanner input = null;
		try {
			input = new Scanner(new File("test-files/cars.txt"));
		} catch (FileNotFoundException e) {
			e.getMessage();
			fail();
		}
		String s = "E17: EMPTY" + "\n" + "118: EMPTY" + "\n" + "119: EMPTY" + "\n" + "E20: EMPTY" + "\n" + "121: EMPTY"
				+ "\n" + "122: EMPTY" + "\n" + "E23: EMPTY" + "\n" + "124: EMPTY";
		ServiceManager sm = new ServiceManager(input);
		assertNotEquals(s, sm.printServiceBays());
	}

}
