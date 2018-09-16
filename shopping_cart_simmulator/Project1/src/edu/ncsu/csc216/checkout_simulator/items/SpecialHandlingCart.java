package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * SpecialHandlingCart can only choose 25% of total number of register Lines 
 * on far right side of the checkout department of the store.
 * @author premsubedi
 */
public class SpecialHandlingCart extends Cart {
	private static Color color = Color.RED;

	/**
	 * Constructor, as it initializes the fields
	 * @param arrivalTime arrival time of cart to the checkout registerline
	 * @param processTime is a time required for each cart to checkout excluding 
	 * their waiting time.
	 */
	public SpecialHandlingCart(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		super.setRegisterIndex(-1); 
	}

	/**
	 * This method overrides the abstract method in Cart class,
	 * it gets the special handling cart to the one-fourth of the total registers 
	 * on the right hand side.
	 */
	@Override
	public void getInLine(CheckoutRegister[] register) {

		int numOfSpecial = 0;
		if(register.length % 4 != 0) {
			numOfSpecial = (register.length) / 4 + 1;
		} else {
			numOfSpecial = register.length / 4;
		}

		int idx = register.length - numOfSpecial;
		int shortest = register[register.length - numOfSpecial].size();
		for(int i = register.length - numOfSpecial; i < register.length; i++) {
			if(register[i].size() < shortest) {
				shortest = register[i].size();
				idx = i;
			}	
		} 
		register[idx].addCartToLine(this);
		super.setRegisterIndex(idx); 
	}

	/**
	 * This method returns color of special handling cart.
	 */
	@Override
	public Color getColor() {
		return color;
	}

}