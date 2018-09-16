
package edu.ncsu.csc216.checkout_simulator.queues;

import edu.ncsu.csc216.checkout_simulator.items.Cart;
import edu.ncsu.csc216.checkout_simulator.items.CartFactory;

/**
 * This class holds all the register and carts and implements
 * line of items interface
 * @author premsubedi
 */
public class Store implements LineOfItems {
	/** The queue of carts created by cartfactory */
	private ShoppingCartQueue shopping;

	/** An array of checkout register */
	private CheckoutRegister [] register;


	/** 
	 * Store constructor that initializes it's fields and adds cart created
	 * by CartFactory to the queue
	 * @param numOfCarts total number of carts in the store
	 * @param register an array of registers
	 */
	public Store(int numOfCarts, CheckoutRegister[] register){
		this.register = register;
		shopping = new ShoppingCartQueue();
		for (int i = 0; i < numOfCarts; i++) {
			Cart c = CartFactory.createCart();
			shopping.add(c);
		}
	}

	/**
	 * Returns true if shopping queue is not empty.
	 * @return true if shopping is not empty.
	 */
	@Override
	public boolean hasNext() {
		if (shopping.size() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * Removes the front cart from the shopping queue and sends it a getInLine message.
	 * Returns the removed cart
	 * @return c removed cart from the shopping queue.
	 */
	@Override
	public Cart processNext() {
		Cart c = shopping.remove();
		c.getInLine(register);
		return c;
	}

	/**
	 * Tells when the cart at the front of the shopping queue will depart that queue
	 * and enters a checkout register.
	 * returns Integer.MAX_VALUE, if a shopping queue is empty. 
	 * @return c.getArrivalTime() arrival time of cart into the register
	 */
	@Override
	public int departTimeNext() {
		if(shopping.size() == 0) {
			return Integer.MAX_VALUE;
		}
		Cart c = shopping.front();
		return c.getArrivalTime();
	}

	/**
	 * Returns the size of the shopping queue
	 * @return shopping.size() the length of the shopping queue of carts.
	 */
	@Override
	public int size() {
		return shopping.size();
	}

}
