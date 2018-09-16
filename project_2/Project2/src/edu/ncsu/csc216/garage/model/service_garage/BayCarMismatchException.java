package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Holds the constructors for the BayCarMismatchException exception
 * 
 * @author someshherath
 *
 */

public class BayCarMismatchException extends Exception {

	/**
	 * ID used for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * constructor that calls the parameterized constructor with an author
	 * specified default message
	 * 
	 */
	public BayCarMismatchException() {
		this("Wrong bay.");

	}

	/**
	 * constructor with a String specifying a message for the Exception object
	 * 
	 * @param message
	 *            message for the Exception object
	 * 
	 */
	public BayCarMismatchException(String message) {
		super(message);

	}
}
