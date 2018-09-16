package edu.ncsu.csc216.checkout_simulator.queues;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.items.CartFactory;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;


/**
 * Tests store class
 * @author premsubedi
 */
public class StoreTest {
	private static final int NUM_CARTS = 200;
	private static final CheckoutRegister[] REGISTER = new CheckoutRegister[10];
	
	/**
	 * Tests Store class methods
	 */
	@Test
	public void testStore() {
		Log lg = new Log();
		for(int i = 0; i < REGISTER.length; i++) {
			REGISTER[i] = new CheckoutRegister(lg);
		}
		ArrayList<Cart> firstTen = new ArrayList<Cart>();
		CartFactory.resetFactory();
		Cart c = null;
		for (int i = 0 ; i < 10 ; i++) {
			c = CartFactory.createCart();
			System.out.println(c);
			firstTen.add(c);
		}
	
	// Testing departTimeNext if the size of shopping is zero.
		Store myStore = new Store(0, REGISTER);
		assertEquals(Integer.MAX_VALUE, myStore.departTimeNext());
		
	//Testing hasNext() with zero cart.
	assertFalse(myStore.hasNext());
	
	// Testing hasNext() with non-zero number of carts
    myStore = new Store(NUM_CARTS, REGISTER);
	assertTrue(myStore.hasNext());
	
	// Tests departTimeNext() method
	assertEquals(181, myStore.departTimeNext());
	
	
	// Tests size of the queue
	assertEquals(NUM_CARTS, myStore.size());
	
	// Tests processNext() method
	myStore.processNext();
	c.getInLine(REGISTER);
	assertTrue(c.isWaitingInRegisterLine());
}
}
