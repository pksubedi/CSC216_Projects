package edu.ncsu.csc216.garage.model.service_garage;

/**
 * Holds the constructors for the BayOccupiedException exception
 * 
 * @author someshherath, Prem Subedi.
 *
 */
public class BayOccupiedException extends Exception {

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
	public BayOccupiedException() {
		this("Service bay is occupied.");

	}

	/**
	 * constructor with a String specifying a message for the Exception object
	 * 
	 * @param message
	 *            message for the Exception object
	 * 
	 */
	public BayOccupiedException(String message) {
		super(message);

	}
}
