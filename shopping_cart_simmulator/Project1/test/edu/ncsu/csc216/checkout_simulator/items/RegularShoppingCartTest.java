/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.items;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * Tests regular shopping cart
 * @author premsubedi
 */
public class RegularShoppingCartTest {

	private static final int ARRIVAL_TIME = 25;
	private static final int PROCESS_TIME = 80;
	private static final int INVALID_ATIME = -5;
	private static final int INVALID_PTIME = -10;
	private CheckoutRegister[] r = new CheckoutRegister[10];
	private CheckoutRegister register;

	/**
	 * Sets up environment for testing
	 */
	@Before
	public void setUp() {
		Log l = new Log();
		register = new CheckoutRegister(l);
		for(int i = 0; i < r.length; i++) {
			r[i] = new CheckoutRegister(l);
		}	
	}
	/**
	 * Testing invalid arrival time of a cart.
	 */
	@Test
	public void testRegularShoppingCartTime() {
		RegularShoppingCart c = null;
		try {
			c = new RegularShoppingCart(INVALID_ATIME, PROCESS_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Testing invalid process time of a cart.
		try {
			c = new RegularShoppingCart (ARRIVAL_TIME, INVALID_PTIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}
		
		//Testing cart with valid time
		try {
			c = new RegularShoppingCart(ARRIVAL_TIME, PROCESS_TIME);
			
		} catch (IllegalArgumentException e) {
			fail("Throws exception in the situation where it doesn't need to");
		}
	}
	

	/**
	 * Tests getInLine method  
	 */
	@Test
	public  void testGetInLine() {
        RegularShoppingCart c = new RegularShoppingCart(ARRIVAL_TIME, PROCESS_TIME);
		c.getInLine(r);
	    assertEquals(1, c.getRegisterIndex());
	    register.addCartToLine(c);
	    assertEquals(1, r[1].size());
		
	}
	
	/**
	 * Test getColor method
	 */
	@Test
	public void testGetColor() {
		 RegularShoppingCart c = new RegularShoppingCart(ARRIVAL_TIME, PROCESS_TIME);
		 assertEquals(Color.BLUE, c.getColor());
	}
}
