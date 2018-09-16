/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.queues;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.items.ExpressCart;
import edu.ncsu.csc216.checkout_simulator.items.RegularShoppingCart;
import edu.ncsu.csc216.checkout_simulator.items.SpecialHandlingCart;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * Tests CheckoutRegister class functionality
 * @author premsubedi
 */
public class CheckoutRegisterTest {

	/**
	 * Tests hasNext() methods
	 */
	@Test
	public void testHasNext() {
		Log l = new Log();
		CheckoutRegister r = new CheckoutRegister(l);
		ExpressCart c1 = new ExpressCart(25, 70);
		RegularShoppingCart c2 = new RegularShoppingCart(28, 120);
		SpecialHandlingCart c3 = new SpecialHandlingCart(30, 150);
		
		assertFalse(r.hasNext());
		r.addCartToLine(c1);
		assertTrue(r.hasNext());
		r.addCartToLine(c2);
		assertTrue(r.hasNext());
		r.addCartToLine(c3);
		assertTrue(r.hasNext());
		
	
	}
	
	
	/**
	 * Tests hasNext() methods
	 */
	@Test
	public void testProcessNext() {
		Log l = new Log();
		CheckoutRegister r = new CheckoutRegister(l);
		ExpressCart c = new ExpressCart(15, 120);
		

		try {
			r.processNext();
			fail();
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
		
		r.addCartToLine(c);
	    r.processNext();
		assertFalse(c.isWaitingInRegisterLine());
	} 
	
	/**
	 * Tests departTimeNext() method.
 	 */
	@Test
	public void testDepartTimeNext() {
		Log l = new Log();
		CheckoutRegister r = new CheckoutRegister(l);
		ExpressCart c1 = new ExpressCart(25, 70);
		
		assertEquals(Integer.MAX_VALUE, r.departTimeNext());
		
		r.addCartToLine(c1);
		l.logCart(c1);
		assertEquals(95, r.departTimeNext());
		
		RegularShoppingCart c2 = new RegularShoppingCart(100, 120);
		r.addCartToLine(c2);
		l.logCart(c2);
		assertEquals(95, r.departTimeNext());
	}
	
}
