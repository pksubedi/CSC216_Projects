package edu.ncsu.csc216.tracker.requirement;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;

/**
 * Tests Command class.
 * @author premsubedi
 */
public class CommandTest {

	/**
	 * Tests Command methods.
	 */
	@Test
	public void testCommand() {

		//Constructing Command with null value.
		Command c = null;
		try {
			c = new Command(null, "Add more features", "002", 2, 
					 "2:30", "pksubedi", Rejection.DUPLICATE);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing Command with command value-accept but null estimate.
		try {
			c = new Command(CommandValue.ACCEPT, "Add more features", "002", 9, 
					 null, "pksubedi", Rejection.DUPLICATE);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing Command with command value-accept but empty estimate.
		try {
			c = new Command(CommandValue.ACCEPT, "Add more features", "002", 2, 
					 "", "pksubedi", Rejection.DUPLICATE);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing Command with command value-accept but invalid priority.
		try {
			c = new Command(CommandValue.ACCEPT, "Add more features", "002", 4, 
					 "1:30", "pksubedi", Rejection.DUPLICATE);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing Command with command value-accept but invalid priority.
		try {
			c = new Command(CommandValue.ACCEPT, "Add more features", "002", 0, 
					 "1:30", "pksubedi", Rejection.DUPLICATE);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing Command with command value-reject but with null rejection..
		try {
			c = new Command(CommandValue.REJECT, "Add more features", "002", 1, 
					 "1:30", "pksubedi", null);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing Command with command value-ASSIGN but with empty developer id.
		try {
			c = new Command(CommandValue.ASSIGN, "Add more features", "002", 2, 
					 "1:30", "", Rejection.DUPLICATE);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing Command with command value-ASSIGN but with null developer id.
		try {
			c = new Command(CommandValue.ASSIGN, "Add more features", "002", 2, 
					 "1:30", null, Rejection.DUPLICATE);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing Command with command value-REVISE but with null summary.
		try {
			c = new Command(CommandValue.REVISE, null, "002", 2, 
					 "1:30", "pksubedi", Rejection.DUPLICATE);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing Command with command value-REVISE but with empty summary.
		try {
			c = new Command(CommandValue.REVISE, "", "002", 2, 
					 "1:30", "pksubedi", Rejection.DUPLICATE);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing Command with command value-REVISE but with null acceptanceTestId.
		try {
			c = new Command(CommandValue.REVISE, "Ask password during start", null, 2, 
					 "1:30", "pksubedi", Rejection.DUPLICATE);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing Command with command value-REVISE but with empty acceptanceTestId.
		try {
			c = new Command(CommandValue.REVISE, "Ask password during start", "", 2, 
					 "1:30", "pksubedi", Rejection.DUPLICATE);
			
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		//Constructing a valid Command.
		
			c = new Command(CommandValue.ACCEPT, "Ask password during start", "RejectedToSubmitted", 2, 
					 "1:30", "pksubedi", Rejection.DUPLICATE);
			assertEquals(CommandValue.ACCEPT, c.getCommand());
			assertEquals("Ask password during start", c.getSummary());
			assertEquals("RejectedToSubmitted", c.getAcceptanceTestId());
			assertEquals(2, c.getPriority());
			assertEquals("pksubedi", c.getDeveloperId());
			assertEquals("1:30", c.getEstimate());
			assertEquals(Rejection.DUPLICATE, c.getRejectionReason());

		
	}

}
