/**
 * Simulation package
 */
package edu.ncsu.csc216.checkout_simulator.simulation;

import static org.junit.Assert.*;
import java.awt.Color;
import java.util.ArrayList;
import org.junit.Test;
import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.items.CartFactory;


/**
 * Tests Simulator class
 * @author premsubedi
 */
public class SimulatorTest {
	

	/** Tests invalid simulator constructor */
	@Test
	public void testInValid() {

		// Tests invalid number of registers
		try {
			new Simulator(2, 5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Number of registers must be between 3 and 12 inclusive.", e.getMessage());
		}

		// Tests invalid number of carts
		try {
			new Simulator(9, 0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("There must be at least one shopping cart in the simulation.", e.getMessage());
		}

	}

	/** tests simulation Colors */
	@Test
	public void testSimulationColors() {
		Color[] col = {Color.GREEN, Color.BLUE, Color.RED};
		assertArrayEquals(col, Simulator.simulationColors());
	}

	/** Tests Simulationlabels */
	@Test
	public void testSimulationLabels() {
		String [] label = {"Express Carts", "Regular Carts", "Special Carts"};
		assertArrayEquals(label, Simulator.simulationLabels());
	}

	/** Tests step() method */
	@Test
	public void testStep() {
		 ArrayList<Cart> firstThree = new ArrayList<Cart>();
			CartFactory.resetFactory();
			Cart c = null;
			for (int i = 0 ; i < 10 ; i++) {
				c = CartFactory.createCart();
				firstThree.add(c);
			}
			CartFactory.resetFactory();
			Simulator s = new Simulator (7, 10);
			s.step();
			assertEquals(1, s.getStepsTaken());
			
			s.step();
			assertEquals(2, s.getStepsTaken());
	}
	
	

	

	/** Tests if there are any more steps */
	@Test
	public void testMoreSteps() {
		ArrayList<Cart> firstTwo = new ArrayList<Cart>();
		CartFactory.resetFactory();
		for (int i = 0 ; i < 2 ; i++) {
			Cart c = CartFactory.createCart();
			firstTwo.add(c);
		}
		CartFactory.resetFactory();
		Simulator s = new Simulator (3, 2);
		s.step();
		assertTrue(s.moreSteps());
		s.step();
		assertTrue(s.moreSteps());
	}

	/** Tests the total number of steps */
	@Test
	public void testTotalNumberOfSteps() {
		ArrayList<Cart> firstTen = new ArrayList<Cart>();
		CartFactory.resetFactory();
		for (int i = 0 ; i < 10 ; i++) {
			Cart c = CartFactory.createCart();
			firstTen.add(c);
		}
		CartFactory.resetFactory();
		Simulator s = new Simulator (7, 10);
		s.step();
		assertEquals(20, s.totalNumberOfSteps());
	} 
			
	/** Tests the current cart's color */
	@Test
	public void testGetCurrentCartColor() {
		ArrayList<Cart> firstTen = new ArrayList<Cart>();
		CartFactory.resetFactory();
		for (int i = 0 ; i < 10 ; i++) {
			Cart c = CartFactory.createCart();
			firstTen.add(c);
		}
		CartFactory.resetFactory();
		Simulator s = new Simulator (7, 10);
		s.step();
		assertEquals(Color.GREEN, s.getCurrentCartColor());

	}

	/** Tests if an item left the simulation or not */
	@Test
	public void testItemLeftSimulation() {
    
		ArrayList<Cart> oneCart = new ArrayList<Cart>();
		CartFactory.resetFactory();
        Cart c = CartFactory.createCart();
	    oneCart.add(c);
		
		CartFactory.resetFactory();
		Simulator s = new Simulator (3, 1);
		s.step();
		assertFalse(s.itemLeftSimulation());
		
	}

	/** Tests for the current register index that the cart is waiting in */
	@Test
	public void testGetCurrentIndex() {
	
		ArrayList<Cart> firstTen = new ArrayList<Cart>();
		CartFactory.resetFactory();
		for (int i = 0 ; i < 10 ; i++) {
			Cart c = CartFactory.createCart();
			firstTen.add(c);
		}
		CartFactory.resetFactory();
		Simulator s = new Simulator (3, 10);
		s.step();
		assertEquals(0, s.getCurrentIndex());
		s.step();
		assertEquals(1, s.getCurrentIndex());
		s.step();
		assertEquals(2, s.getCurrentIndex());
		
		
	}

}

