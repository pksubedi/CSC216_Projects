package edu.ncsu.csc216.checkout_simulator.items;

import java.awt.Color;

import edu.ncsu.csc216.checkout_simulator.queues.CheckoutRegister;

/**
 * This is an abstract and a super class of all the carts
 * All three types carts inherit the behavior from this class
 * @author premsubedi
 */
public abstract class Cart {

	/** Constant for initial register index */
	public static final int INITIAL_REGISTER_IDX = -1;

	/** Time when the cart leaves the shopping area and enters a checkout register line */
	private int arrivalTime;

	/** Number of seconds the cart waits in a checkout register line before processing */
	private int waitTime;

	/** Number of seconds required to check out at the register */
	private int processTime;

	/** The index of the register that the cart has selected */
	private int registerIndex;

	/** True if the cart is currently in a line for a register, false otherwise */
	private boolean waitingProcessing;

	/**
	 * This is a Cart constructor which initializes it's fields
	 * @param arrivalTime cart's arrival time in a line.
	 * @param processTime cart's processing time in a checkout register.
	 */
	public Cart (int arrivalTime, int processTime) {
		this.setArrivalTime(arrivalTime);
		this.setProcessTime(processTime);
		this.setWaitTime(waitTime);
		this.setRegisterIndex(INITIAL_REGISTER_IDX);
	}

	/**
	 * Returns arrival time of a cart in a register line
	 * @return arrivalTime arrival time of a cart 
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets arrival time of a cart
	 * @param arrivalTime arrival time of a cart into a line
	 */
	public void setArrivalTime(int arrivalTime) {
		if(arrivalTime < 0) {
			throw new IllegalArgumentException();
		}
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Returns waiting time of a cart in a checkout register line
	 * @return waitTime wait time of a cart 
	 */
	public int getWaitTime() {
		return waitTime;
	}

	/**
	 * Sets waiting time of a cart in a checkout register line
	 * @param waitTime wait time of a cart
	 */
	public void setWaitTime(int waitTime) {
		if(waitTime < 0) {
			throw new IllegalArgumentException();
		}
		this.waitTime = waitTime;
	}

	/**
	 * Returns process time of a cart in a checkout register 
	 * @return processTime process time of a cart in checkout register
	 */
	public int getProcessTime() {
		return processTime;
	}

	/**
	 * Sets processing time of cart in checkout register
	 * @param processTime process time of a cart
	 */
	public void setProcessTime(int processTime) {
		if(processTime < 0) {
			throw new IllegalArgumentException();
		}
		this.processTime = processTime;
	}

	/**
	 * Returns index of a register that a cart has selected.
	 * @return registerIndex register's index .
	 */
	public int getRegisterIndex() {
		return registerIndex;
	}

	/**
	 * Sets register's index that a cart has selected 
	 * @param index index of a register
	 */
	public void setRegisterIndex(int index) {
		this.registerIndex = index;
		if(registerIndex >= 0) {
			waitingProcessing = true;
		}

	}

	/**
	 * Removes cart from waiting list of a line 
	 */
	public void removeFromWaitingLine() {
		waitingProcessing = false;
	}

	/**
	 * Returns true if a cart is waiting in a line for processing
	 * @return waitingProcessing waiting for processing 
	 */
	public boolean isWaitingInRegisterLine() {
		return waitingProcessing;
	}

	/** 
	 * Abstract method that will be implemented by all it's subclasses later
	 * @param register an arraylist of checkout register
	 */
	public abstract void getInLine(CheckoutRegister[] register);

	/** 
	 * Abstract method that will later be implemented by it's subclasses later
	 * to access color of their type.
	 * @return Color
	 */
	public abstract Color getColor();


}
