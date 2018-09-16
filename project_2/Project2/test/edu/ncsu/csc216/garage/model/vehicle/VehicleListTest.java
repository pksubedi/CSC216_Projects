package edu.ncsu.csc216.garage.model.vehicle;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

/**
 * tests VehicleList
 * 
 * @author Prem Subedi
 * @author Somesh Herath
 */
public class VehicleListTest {

	Scanner input = new Scanner("test-files/cars.txt");

	/**
	 * tests VehicleList
	 */
	@Test
	public void testVehicleList() {
		String obj = "";

		VehicleList vl = new VehicleList();
		assertFalse(vl.equals(obj));

		Vehicle v = null;
		try {
			v = new RegularCar("HI-01345", "Rhyne, Lauren", 2);
			vl.add(v);
			assertEquals(v, vl.get("R", 0));
		} catch (BadVehicleInformationException e) {
			fail();
		}
		assertEquals("R Gold      HI-01345  Rhyne, Lauren" + "\n", vl.filteredList(""));
		assertEquals("R Gold      HI-01345  Rhyne, Lauren" + "\n", vl.filteredList("R"));

		try {
			v = new RegularCar("JI-01335", "Ron, Burgandy", 3);
			vl.add(v);
			assertEquals(v, vl.get("R", 0));
		} catch (BadVehicleInformationException e) {
			fail();
		}

		try {
			v = new RegularCar("UF-71398", "Ralph, Smith", 1);
			vl.add(v);
			assertEquals(v, vl.get("R", 2));
		} catch (BadVehicleInformationException e) {
			fail();
		}
		assertEquals("R Platinum  JI-01335  Ron, Burgandy" + "\n" + "R Gold      HI-01345  Rhyne, Lauren" + "\n"
				+ "R Silver    UF-71398  Ralph, Smith" + "\n", vl.filteredList(""));

		try {
			assertNotNull(vl.remove("9", 1));
			fail();
		} catch (IndexOutOfBoundsException | AssertionError e) {
			assertEquals("R Platinum  JI-01335  Ron, Burgandy" + "\n" + "R Gold      HI-01345  Rhyne, Lauren" + "\n"
					+ "R Silver    UF-71398  Ralph, Smith" + "\n", vl.filteredList(""));
		}

		// GET METHOD'S FUNCTIONAILITY IS STRANGE(partially fixed)
		assertEquals("R Gold      HI-01345  Rhyne, Lauren", vl.get("R", 1).toString());
		try {
			vl.get("R", 8);
			fail();
		} catch (IndexOutOfBoundsException | AssertionError e) {
			assertEquals("R Platinum  JI-01335  Ron, Burgandy" + "\n" + "R Gold      HI-01345  Rhyne, Lauren" + "\n"
					+ "R Silver    UF-71398  Ralph, Smith" + "\n", vl.filteredList(""));
		}

		VehicleList vl2 = new VehicleList();

		try {
			v = new RegularCar("HI-01345", "Chyne, Lauren", 3);
			vl2.add(v);
			assertEquals(v, vl2.get("C", 0));
		} catch (BadVehicleInformationException e) {
			fail();
		}

		Vehicle vehicle = null;
		try {
			vehicle = new RegularCar("HI-01345", "Rhyne, Lauren", 3);
			vl2.add(vehicle);
		} catch (BadVehicleInformationException e) {
			assertEquals(vehicle, vl2.get("R", 0));
		}

		assertEquals("R Platinum  HI-01345  Rhyne, Lauren" + "\n", vl2.filteredList("R"));

		assertEquals("R Platinum  HI-01345  Chyne, Lauren" + "\n", vl2.filteredList("C"));

	}

	/**
	 * Tests VehicleList(Scanner scan).
	 */
	@Test
	public void testVehicleListScanner() {
		Vehicle v = null;
		try {
			v = new RegularCar("HI-01345", "Rhyne, Lauren", 3);
		} catch (BadVehicleInformationException e) {
			e.getMessage();
			fail();
		}
		File file = new File(input.next());
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.getMessage();
			fail();
		}
		VehicleList vlist = new VehicleList(input);
		assertEquals(v.getLicense(), vlist.remove("R", 0).getLicense());
	}
}
