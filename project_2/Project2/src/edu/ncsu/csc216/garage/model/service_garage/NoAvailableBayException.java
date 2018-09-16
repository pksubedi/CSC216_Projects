package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Holds the constructors for the NoAvailableBayException exception
 * 
 * @author someshherath
 *@author Prem Subedi 
 */
public class NoAvailableBayException extends Exception {

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
	public NoAvailableBayException() {
		this("No appropriate bays opened.");

	}

	/**
	 * constructor with a String specifying a message for the Exception object
	 * 
	 * @param message
	 *            message for the Exception object
	 * 
	 */
	public NoAvailableBayException(String message) {
		super(message);
	}
}
