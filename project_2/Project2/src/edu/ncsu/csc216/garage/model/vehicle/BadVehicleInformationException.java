package edu.ncsu.csc216.garage.model.vehicle;

/**
 * Holds the constructors for the BadVehicleInformationException exception
 * 
 * @author someshherath
 *
 */
public class BadVehicleInformationException extends Exception {

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
	public BadVehicleInformationException() {
		this("Invalid Vehicle Information.");
	}

	/**
	 * constructor with a String specifying a message for the Exception object
	 * 
	 * @param message
	 *            message for the Exception object
	 * 
	 */
	public BadVehicleInformationException(String message) {
		super(message);
	}
}
