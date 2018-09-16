package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This is a checked exception class, which handles exception.
 * @author premsubedi
 */
public class ConflictException extends Exception {

	
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/** 
	 *This is a parameterized constructor for Exception object which 
	 *calls it's parent class for message to display.
	 * @param message exception message to the clients.
	 */
	public ConflictException(String message){
		super(message);
	}
	
	/** This is a parameterless constructor which calls the other constructor 
	 * by passing specific exception message.
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
