package edu.ncsu.csc216.checkout_simulator.queues;
import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.items.CartFactory;
import edu.ncsu.csc216.checkout_simulator.simulation.Log;

/**
 * CheckoutRegister class process all the carts in that register
 * and calculates their total time and also show time when available.
 * @author premsubedi
 */
public class CheckoutRegister implements LineOfItems {

	/** The time when the line for this checkout register 
	 *  will finally be clear all of carts currently in line.  */
	private int timeWhenAvailable;

	/** The ShoppingCartQueue of carts waiting for or being processed at this register.*/
	private ShoppingCartQueue line;

	/** The cart at the front of the line logs its information here during checkout. */
	private Log log;

	/**
	 * Checkout register constructor has one parameter, which is of type Log. 
	 * This initializes the instance field log. The line is also constructed.
	 * @param log log that tracks activity of registerline.
	 */
	public CheckoutRegister(Log log) {
		this.log = log;
		this.line = new ShoppingCartQueue();
	}

	/**
	 * Returns the size of the line;
	 * @return line.size() the length of the line of carts.
	 */
	public int size() {
		return line.size();
	}

	/**
	 * Removes the front cart from the line, logging its information in the process
	 * and returns that removed cart.
	 * @return c removed cart
	 */
	public Cart processNext() {
		Cart c = CartFactory.createCart();
		c = line.remove();
		log.logCart(c);
		c.removeFromWaitingLine();

		return c;

	}
	/**
	 * Returns true if the line is not empty.
	 * @return true if there is at least next cart.
	 */
	public boolean hasNext() {
		if(line.size() == 0) {
			return false;
		}
		return true;
	}
	/**
	 * This method returns total time that a cart has spent in the store, 
	 * returns Integer.MAX_VALUE, if a line is empty. 
	 * @return c.getArrivalTime() + c.getWaitTime() + c.getProcessTime() total
	 * time spent in the store.
	 */
	public int departTimeNext() {
		Cart c = line.front();
		if(c == null){
			return Integer.MAX_VALUE; 
		}
		return c.getArrivalTime() + c.getWaitTime() + c.getProcessTime();
	}


	/** 
	 * Adds a cart to the end of the line, 
	 * updating the cart's waitTime as well as
	 * the time when the line will be clear of all carts currently in line.
	 * @param c is a cart object.
	 */
	public void addCartToLine(Cart c) {
		int processTime = c.getProcessTime();
		int arrivalTime = c.getArrivalTime();
		int waitTime = 0;
		if (timeWhenAvailable <= c.getArrivalTime()) {
			timeWhenAvailable = arrivalTime + processTime;  
		} else {
			waitTime = timeWhenAvailable - arrivalTime;
			timeWhenAvailable += processTime;
		}
		c.setWaitTime(waitTime);
		line.add(c);
	}

}

