package edu.ncsu.csc216.tracker.requirement;

import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;

/**
 * Concrete class that represents a command (assign, reject, and so on)
 * that the user enters from the GUI to be handled by the internal FSM.
 * @author premsubedi
 */
public class Command {
	/** Requirement's summary */
	private String summary;
	
	/** Requirement's acceptance test id.*/
	private String acceptanceTestId;
	
	/** Developer assigned to work on the requirement */
	private String developerId;
	
	/** Estimated time to finished implementing the requirement */
	private String estimate;
	
	/** Requirement's priority, which is an integer value */
	private int priority;
	
	/** Command value for a requirement */
	private CommandValue value;
	
	/** Requirement rejection reason which is a Rejection object */
	private Rejection resolutionReason;

	/**
	 * Command constructor that initializes it's fields.
	 * @param value command value
	 * @param summary requirement's summary
	 * @param acceptanceTestId requirement's acceptance test Id.
	 * @param priority requirement's priority.
	 * @param developerId requirement's developer id.
	 * @param estimate requirement's estimated time.
	 * @param rejection rejection instance for requirement.
	 * @throws IllegalArgumentException if command construction is invalid.
	 */
	public Command(CommandValue value, String summary, String acceptanceTestId, int priority, 
			String estimate, String developerId, Rejection rejection) {
		if(value == null) {
			throw new IllegalArgumentException();
		} else {
			if(value == CommandValue.ACCEPT && (estimate == null || estimate.equals("") 
					|| priority < 1 || priority > 3)) {
				throw new IllegalArgumentException();
			}
			else if(value == CommandValue.REJECT && rejection == null) {
				throw new IllegalArgumentException();
			}

			else if(value == CommandValue.ASSIGN && (developerId == null || developerId.equals(""))) {
				throw new IllegalArgumentException();
			}
			else if(value == CommandValue.REVISE && (summary == null || summary.equals("") ||
					acceptanceTestId == null || acceptanceTestId.equals(""))) {
				throw new IllegalArgumentException();
			}
			
		}
		this.value = value;
		this.summary = summary;
		this.acceptanceTestId = acceptanceTestId;
		this.priority = priority;
		this.developerId = developerId;
		this.estimate = estimate;
		this.resolutionReason = rejection;
	}

	/**
	 * Returns requirement's summary in the form of string.
	 * @return summary requirement's summary.
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Returns acceptance test id for the requirement.
	 * @return acceptanceTestId acceptance test id of the requirement.
	 */
	public String getAcceptanceTestId() {
		return acceptanceTestId;
	}

	/**
	 * Returns instance of CommandValue
	 * @return value instance of CommandValue.
	 */
	public CommandValue getCommand() {
		return value;
	}

	/**
	 * Returns estimated time for the requirement.
	 * @return estimate the string representation of estimated time.
	 */
	public String getEstimate() {
		return estimate;
	}

	/**
	 * Returns requirement's priority as a integer from 0 to 3.
	 * @return priority requirment's priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Returns developer Id.
	 * @return developerId -Id of a develper.
	 */
	public String getDeveloperId() {
		return developerId;
	}


	/**
	 * Returns rejection reason as an instance of Rejection.
	 * @return resolutionReason reason for the requirement.
	 */
	public Rejection getRejectionReason() {
		return resolutionReason;
	}
}
