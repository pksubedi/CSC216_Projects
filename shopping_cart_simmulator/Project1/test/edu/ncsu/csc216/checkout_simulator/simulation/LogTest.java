/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.simulation;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.items.ExpressCart;
import edu.ncsu.csc216.checkout_simulator.items.RegularShoppingCart;
import edu.ncsu.csc216.checkout_simulator.items.SpecialHandlingCart;
import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * Tests Log class
 * @author premsubedi
 */
public class LogTest {
	
	private static final double DELTA = 0.001;

	/** Test logCart method */
	@Test
	public void testLogCart () {
		Log myLog = new Log();
		//ArrayList<Cart> cartLine = new ArrayList<Cart>();
		ExpressCart c1 = new ExpressCart(25, 70);
		RegularShoppingCart c2 = new RegularShoppingCart(28, 120);
		SpecialHandlingCart c3 = new SpecialHandlingCart(30, 150);
		
		assertEquals(0, myLog.getNumCompleted());
		myLog.logCart(c1);
		assertEquals(1, myLog.getNumCompleted());
		myLog.logCart(c2);
		assertEquals(2, myLog.getNumCompleted());
		myLog.logCart(c3);
		assertEquals(3, myLog.getNumCompleted());
	}
	
	/** Test getAverageWaitTime method */
	@Test
	public void testAverageWaitTime() {
		Log myLog = new Log();
		CheckoutRegister r = new CheckoutRegister(myLog);
		ExpressCart c1 = new ExpressCart(25, 70);
		RegularShoppingCart c2 = new RegularShoppingCart(28, 120);
		SpecialHandlingCart c3 = new SpecialHandlingCart(30, 150);
		
		r.addCartToLine(c1);
		myLog.logCart(c1);
		assertEquals(0.0, myLog.averageWaitTime(), DELTA);
	    r.addCartToLine(c2);
		myLog.logCart(c2);
		assertEquals(33.5, myLog.averageWaitTime(), DELTA);
		r.addCartToLine(c3);
		myLog.logCart(c3);
		assertEquals(84.0, myLog.averageWaitTime(), DELTA);
		
		
	}
	
	/**
	 * Tests averageProcessTime method
	 */
	@Test
	public void testAverageProcessTime() {
		Log myLog = new Log();
		ExpressCart c1 = new ExpressCart(25, 70);
		RegularShoppingCart c2 = new RegularShoppingCart(28, 120);
		SpecialHandlingCart c3 = new SpecialHandlingCart(30, 150);
		
		myLog.logCart(c1);
		assertEquals(70.0, myLog.averageProcessTime(), DELTA);
		myLog.logCart(c2);
		assertEquals(95.0, myLog.averageProcessTime(), DELTA);
		myLog.logCart(c3);
		assertEquals(113.333, myLog.averageProcessTime(), DELTA);
		
	}
	
}
