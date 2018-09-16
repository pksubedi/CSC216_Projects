package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * This is a regular shopping cart, a sub class of a Cart which 
 * can get checked out in any register except the first express register.
 * @author Premsubedi
 */
public class RegularShoppingCart extends Cart {
	private static Color color = Color.BLUE;

	/**
	 * Class Constructor which initializes it's fields 
	 * @param arrivalTime arrival time of a cart
	 * @param processTime process time of a cart
	 */
	public RegularShoppingCart(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		super.setRegisterIndex(-1); 
	}

	/**
	 * This method overrides getInLine abstract method of Cart class
	 * it gets a cart to the respective checkout register line
	 */
	@Override
	public void getInLine(CheckoutRegister[] register) {

		int smallest = register[1].size();
		int idx = 1;
		for (int i = 2; i < register.length; i++) {
			if(register[i].size() < smallest) {
				smallest = register[i].size();
				idx = i;
			} 
		}
		register[idx].addCartToLine(this);
		super.setRegisterIndex(idx); 
	}

	/**
	 * Overrides abstract method, which returns a color of regular shopping cart
	 * which is blue
	 */
	@Override
	public Color getColor() {
		return color;
	}

}
