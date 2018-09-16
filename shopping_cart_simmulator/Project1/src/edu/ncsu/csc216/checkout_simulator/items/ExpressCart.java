package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * This is a sub class of Cart class and it's the cart that needs 
 * express or quick service and needs to be handled in any shortest 
 * register line of carts
 * @author premsubedi
 */
public class ExpressCart extends Cart {
	private static Color color = Color.GREEN;

	/**
	 * Constructor which initializes the fields
	 * @param arrivalTime arrival time of a cart in a register line
	 * @param processTime process time of a cart in a register line
	 */
	public ExpressCart(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		super.setRegisterIndex(-1); 
	}

	/**
	 * This method overrides getInLine abstract method of Cart class
	 * it gets a cart to the respective checkout register line
	 */
	@Override
	public void getInLine(CheckoutRegister[] register) {

		int smallest = register[0].size();
		int idx = 0;
		for (int i = 1; i < register.length; i++) {
			if(register[i].size() < smallest) {
				smallest = register[i].size();
				idx = i;
			} 
		}
		register[idx].addCartToLine(this);
		super.setRegisterIndex(idx); 

	}
	/**
	 * Overrides abstract method, which returns a color of express cart
	 * which is green
	 */
	@Override
	public Color getColor() {
		return color;
	}
}
