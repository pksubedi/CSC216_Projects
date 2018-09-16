/**
 * 
 */
package edu.ncsu.csc216.checkout_simulator.simulation;

import edu.ncsu.csc216.checkout_simulator.items.Cart;

/**
 * This keeps track of what the register is doing or register's activity.
 * how many carts been checkout, how many are waiting etc.
 * @author premsubedi
 */
public class Log {
	/** Number of cart that were checked out */
	private int numCompleted;

	/** Total waiting time for each cart */
	private int totalWaitTime;

	/** Total process time for a cart */
	private int totalProcessTime;

	/**
	 * Returns the number of carts that where completed checkout.
	 * @return numCompleted number of the cart that completed checkout.
	 */
	public int getNumCompleted() {
		return numCompleted;
	}

	/**
	 * This method updates the activity of each cart in checkout register line.
	 * @param c c is a cart object.
	 */
	public void logCart(Cart c) {
		this.totalWaitTime += c.getWaitTime();
		this.totalProcessTime += c.getProcessTime();
		numCompleted++ ;
	}

	/**
	 * Returns an average wait time of the cart in the store.
	 * @return (double)(totalWaitTime / numCompleted) average wait time in floating value.
	 */
	public double averageWaitTime() {
		if(numCompleted == 0) {
			return 0;
		}
		return totalWaitTime * 1.0 / numCompleted; 
	}

	/**
	 * Returns an average process time of the carts in the store
	 * @return (double)(totalProcessTime / numCompleted) average processing time in floating value.
	 */
	public double averageProcessTime() {
		if(numCompleted == 0) {
			return 0;
		}
		return totalProcessTime * 1.0 / numCompleted;
	}


}
