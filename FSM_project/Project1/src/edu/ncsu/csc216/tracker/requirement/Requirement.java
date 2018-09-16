package edu.ncsu.csc216.tracker.requirement;

import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;
import edu.ncsu.csc216.tracker.xml.Req;

/**
 * Requirement class which is a context class of the state design pattern 
 * for the tracked requirement FSM. It represents a requirement tracked by
 * ReqKeeper system.
 * @author Prem Subedi
 */
public class Requirement {

	/** Requirement's id */
	private int requirementId;

	/** Requirment's summary */
	private String summary;

	/** Requirement's acceptance id */
	private String acceptanceTestId;

	/** Requirement's priority which is integer from 0 to 3. */
	private int priority;

	/** Requirement's estimate time */
	private String estimate;

	/** Requirement's assigned developer */
	private String developer;

	/** Requirement's rejection of Rejection instance   */
	private Rejection rejReason;

	/** Requirement's submittedState variable */
	private final RequirementState submittedState = new SubmittedState();

	/** Requirement's acceptedState variable */
	private final RequirementState  acceptedState = new AcceptedState();

	/** Requirement's workingState variable */
	private final RequirementState  workingState = new WorkingState();

	/** Requirement's completedState variable */
	private final RequirementState  completedState = new CompletedState();

	/** Requirement's verifiedState variable */
	private final RequirementState  verifiedState = new VerifiedState();

	/** Requirement's rejectedState variable */
	private final RequirementState rejectedState = new RejectedState();

	/** Requirement's general state variable */
	private RequirementState  state;

	/** Class constants for submitted state */
	public static final String SUBMITTED_NAME = "Submitted";

	/** Class constants for accepted state */
	public static final String ACCEPTED_NAME = "Accepted";

	/** Class constants for rejected state */
	public static final String REJECTED_NAME = "Rejected";

	/** Class constants for working state */
	public static final String WORKING_NAME = "Working";

	/** Class constants for completed state */
	public static final String COMPLETED_NAME = "Completed";

	/** Class constants for verified state */
	public static final String VERIFIED_NAME = "Verified";

	/** Class constants for duplicate requirement status */
	public static final String DUPLICATE_NAME = "Duplicate";

	/** Class constants for infeasible requirement status */
	public static final String INFEASIBLE_NAME = "Infeasible";

	/** Class constants for too large requirement's status */
	public static final String TOO_LARGE_NAME = "Too_large";

	/** Class constants for out of scope status of requirement */
	public static final String OUT_OF_SCOPE_NAME = "Out_of_Scope";

	/** Class constants for inappropriate requirement */
	public static final String INAPPROPRIATE_NAME = "Inappropriate";

	/** Counter for the updates */
	private static int counter;

	/**
	 * Constructor for Requirement with two parameters, which initializes all the 
	 * instance fields.
	 * @param acceptanceTestId requirement's acceptance test id.
	 * @param summary requirement's summary.
	 */
	public Requirement(String summary, String acceptanceTestId) {
		this.acceptanceTestId = acceptanceTestId;
		this.summary = summary;
		requirementId = counter;
		Requirement.incrementCounter();	
		this.priority = 0;
		this.estimate = null;
		this.developer = null;
		this.state = submittedState;
		this.rejReason = null;
	}

	/** 
	 * Requirement constructor, which constructs Requirement
	 * from Req. 
	 * @param req an instance of Req.
	 */
	public Requirement(Req req) {
		this.acceptanceTestId = req.getTest();
		this.summary = req.getSummary();
		requirementId = req.getId();
		Requirement.incrementCounter();	
		this.priority = req.getPriority();
		this.estimate = req.getEstimate();
		this.developer = req.getDeveloper();
		this.setState(req.getState());
		if(req.getRejection() != null) {
			this.setRejectionReason(req.getRejection());
		}
	}

	/** Increments counter */
	private static void incrementCounter() {
		Requirement.counter++;
	}

	/** Returns requirement id 
	 * @return requirementId requirement's id.
	 */
	public int getRequirementId() {
		return requirementId;
	}

	/**
	 * Returns requirement's state.
	 * @return state requirement's state.
	 */
	public RequirementState getState() {
		return state;
	}

	/**
	 * Sets passed state to the requirement
	 * @param state current requirement's state.
	 * @throws IllegalArgumentException if an invalid state is passed.
	 */

	private void setState(String state) {
		if(state.equals(SUBMITTED_NAME)) {
			this.state = submittedState;
		} else if(state.equals(ACCEPTED_NAME)) {
			this.state = acceptedState;
		} else if(state.equals(WORKING_NAME)) {
			this.state = workingState;
		} else if(state.equals(COMPLETED_NAME)) {
			this.state = completedState;
		} else  if(state.equals(VERIFIED_NAME)) {
			this.state = verifiedState;
		} else if(state.equals(REJECTED_NAME)) {
			this.state = rejectedState;
		} else {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * Returns requirement's priority
	 * @return priority requirement's priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Returns estimated time in the form of string to complete the requirement
	 * @return estimate estimate time
	 */
	public String getEstimate() {
		return estimate;
	}

	/**
	 * Returns an instance of rejection 
	 * @return rejection reason
	 */
	public Rejection getRejectionReason() {
		return rejReason;
	}

	/**
	 * Returns string format of rejection reason, otherwise returns null.
	 * @return null if rejection reason is null.
	 */
	public String getRejectionReasonString() {
		if(rejReason != null) {
			if(rejReason.equals(Rejection.DUPLICATE)) {
				return DUPLICATE_NAME;
			} else if(rejReason.equals(Rejection.INAPPROPRIATE)) {
				return INAPPROPRIATE_NAME;
			} else if(rejReason.equals(Rejection.INFEASIBLE)) {
				return INFEASIBLE_NAME;
			} else if(rejReason.equals(Rejection.OUT_OF_SCOPE)) {
				return OUT_OF_SCOPE_NAME;
			} else if(rejReason.equals(Rejection.TOO_LARGE)) {
				return TOO_LARGE_NAME;
			} 
		} 
		return null;

	}

	/**
	 * Sets rejection reason of the requirement based on the provided reason.
	 * @param reason rejection reason
	 * @throws IllegalArgumentException if passed string reason is not equal 
	 * to any of the valid reason.
	 */
	private void setRejectionReason(String reason) {
		if(reason.equalsIgnoreCase(DUPLICATE_NAME)) {
			this.rejReason = Rejection.DUPLICATE;
		} else if(reason.equalsIgnoreCase(INFEASIBLE_NAME)) {
			this.rejReason = Rejection.INFEASIBLE;
		} else if(reason.equalsIgnoreCase(INAPPROPRIATE_NAME)) {
			this.rejReason = Rejection.INAPPROPRIATE;
		} else if(reason.equalsIgnoreCase(TOO_LARGE_NAME)) {
			this.rejReason = Rejection.TOO_LARGE;
		} else if(reason.equalsIgnoreCase(OUT_OF_SCOPE_NAME)) {
			this.rejReason = Rejection.OUT_OF_SCOPE;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns developer assigned to work for the requirement.
	 * @return developer developer for the requirement.
	 */
	public String getDeveloper() {
		return developer;
	}

	/**
	 * Sets developer for the requirement to implement.
	 * @param developer developer for the working on the requirement.
	 */
	public void setDeveloper(String developer) {
		this.developer = developer;

	}

	/**
	 * Returns requirement's summary in the form of string.
	 * @return summary requirement's summary.
	 */
	public String getSummary() {
		return summary;	
	}

	/**
	 * Returns acceptance test id of the requirement.
	 * @return acceptanceTestId acceptance test id.
	 */
	public String getAcceptanceTestId() {
		return acceptanceTestId;
	}

	/**
	 * Sets acceptance test id for the requirement.
	 * @param acceptanceTestId acceptance test id.
	 */
	public void setAcceptanceTestId(String acceptanceTestId) {
		this.acceptanceTestId = acceptanceTestId;
	}

	/**
	 * Updates the requirement state based on the command provided.
	 * @param c command for the updates.
	 */
	public void update(Command c) {
		state.updateState(c);
	}

	/**
	 * Converts Requirement to Req by setting all of the Req's fields
	 * and returns Req instance of XML.
	 * @return r requirement in the form of req.
	 */
	public Req getXMLReq() {
		Req r = new Req();
		r.setTest(acceptanceTestId); 
		r.setSummary(summary);
		r.setId(requirementId);
		r.setPriority(priority);
		r.setEstimate(estimate);
		r.setDeveloper( developer);
		r.setState(state.getStateName());
		r.setRejection(getRejectionReasonString());
		return r;
	}

	/**
	 * Static method that sets counter.
	 * @param count counter value.
	 */
	public static void setCounter(int count) {
		counter = count;
	}

	/**
	 * Inner or nested class inside requirement class, which represents submitted
	 * state of the requrement
	 * @author Prem Subedi
	 */
	public class SubmittedState implements RequirementState {

		/**
		 * Updates current state.
		 * @param c instance of Command.
		 * @throws UnsupportedOperationException if the command value is invalid.
		 */
		public void updateState(Command c) {
			if(c.getCommand().equals(CommandValue.ACCEPT)) {
				state = acceptedState;
				estimate = c.getEstimate();
				priority = c.getPriority();
				
			} else if(c.getCommand().equals(CommandValue.REJECT)) {
				state = rejectedState;
				setRejectionReason(c.getRejectionReason().toString());
				estimate = null;
				priority = 0;
				developer = null;
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns current state name.
		 * @return SUBMITTED_NAME state's name.
		 */
		public String getStateName() {
			return SUBMITTED_NAME;
		}
	}

	/**
	 * Inner or nested class inside requirement class, which represents accepted
	 * state of the requrement 
	 * @author Prem Subedi
	 *
	 */
	public class AcceptedState implements RequirementState {

		/**
		 * Updates current state.
		 * @param c instance of Command.
		 * @throws UnsupportedOperationException if the command value is invalid.
		 */
		public void updateState(Command c) {
			if(c.getCommand().equals(CommandValue.ASSIGN)) {
				state = workingState;
				developer = c.getDeveloperId();
			} else if(c.getCommand().equals(CommandValue.REJECT)) {
				state = rejectedState;
				setRejectionReason(c.getRejectionReason().toString());
				estimate = null;
				priority = 0;
				developer = null;
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns current state name.
		 * @return ACCEPTED_NAME state's name.
		 */
		public String getStateName() {
			return ACCEPTED_NAME;
		}
	}

	/**
	 * Inner or nested class inside requirement class, which represents working
	 * state of the requrement 
	 * @author Prem Subedi
	 *
	 */
	public class WorkingState implements RequirementState {

		/**
		 * Updates current state.
		 * @param c instance of Command.
		 * @throws UnsupportedOperationException if the command value is invalid.
		 */
		public void updateState(Command c) {
			if(c.getCommand().equals(CommandValue.COMPLETE)) {
				state = completedState;
			} else if(c.getCommand().equals(CommandValue.REJECT)) {
				state = rejectedState;
				setRejectionReason(c.getRejectionReason().toString());
				estimate = null;
				priority = 0;
				developer = null;
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns current state name.
		 * @return WORKING_NAME state's name.
		 */
		public String getStateName() {
			return WORKING_NAME;
		}
	}

	/**
	 * Inner or nested class inside requirement class, which represents completed
	 * state of the requrement 
	 * @author Prem Subedi
	 *
	 */
	public class CompletedState implements RequirementState {

		/**
		 * Updates current state.
		 * @param c instance of Command.
		 * @throws UnsupportedOperationException if the command value is invalid.
		 */
		public void updateState(Command c) {
			if(c.getCommand().equals(CommandValue.ASSIGN)) {
				state = workingState;
				developer = c.getDeveloperId();
			} else if(c.getCommand().equals(CommandValue.REJECT)) {
				state = rejectedState;
				setRejectionReason(c.getRejectionReason().toString());
				estimate = null;
				priority = 0;
				developer = null;
			} else if(c.getCommand().equals(CommandValue.PASS)) {
				state = verifiedState;
			} else if(c.getCommand().equals(CommandValue.FAIL)) {
				state = workingState;
			} else {
				throw new UnsupportedOperationException();
			}

		}

		/**
		 * Returns current state name.
		 * @return COMPLETED_NAME state's name.
		 */
		public String getStateName() {
			return COMPLETED_NAME;
		}
	}

	/**
	 * Inner or nested class inside requirement class, which represents verified
	 * state of the requrement 
	 * @author Prem Subedi
	 */
	public class VerifiedState implements RequirementState {

		/**
		 * Updates current state.
		 * @param c instance of Command.
		 * @throws UnsupportedOperationException if the command value is invalid.
		 */
		public void updateState(Command c) {
			if(c.getCommand().equals(CommandValue.ASSIGN)) {
				state = workingState;
				developer = c.getDeveloperId();
			} else if(c.getCommand().equals(CommandValue.REJECT)) {
				state = rejectedState;
				setRejectionReason(c.getRejectionReason().toString());
				estimate = null;
				priority = 0;
				developer = null;
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns current state name.
		 * @return VERIFIED_NAME state's name.
		 */
		public String getStateName() {
			return VERIFIED_NAME;
		}
	}

	/**
	 * Inner or nested class inside requirement class, which represents rejected
	 * state of the requrement
	 * @author Prem Subedi
	 */
	public class RejectedState implements RequirementState {

		/**
		 * Updates current state.
		 * @param c instance of Command.
		 * @throws UnsupportedOperationException if the command value is invalid.
		 */
		public void updateState(Command c) {
			if(c.getCommand().equals(CommandValue.REVISE)) {
				state = submittedState;
				rejReason = null;
				summary = c.getSummary();
				acceptanceTestId = c.getAcceptanceTestId();
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns current state name.
		 * @return REJECTED_NAME state's name.
		 */
		public String getStateName() {
			return REJECTED_NAME;
		}
	}
}
