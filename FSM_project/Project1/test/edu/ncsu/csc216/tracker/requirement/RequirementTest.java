package edu.ncsu.csc216.tracker.requirement;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
//import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.requirement.enums.Rejection;
import edu.ncsu.csc216.tracker.xml.Req;
import edu.ncsu.csc216.tracker.xml.RequirementIOException;
import edu.ncsu.csc216.tracker.xml.RequirementsReader;

/**
 * Tests Requirement class.
 * @author premsubedi
 */
public class RequirementTest {

	private final String validTestFile = "test_files/test-files/exp_req_all.xml";

	/**
	 * Tests Requirement constructor.
	 */
	@Test
	public void testRequirement() {
		Requirement r = new Requirement("Add some extra features", "SubmittedToAccepted");
		assertEquals("SubmittedToAccepted", r.getAcceptanceTestId());
		assertEquals("Add some extra features", r.getSummary());
		assertEquals(0, r.getPriority());
		assertNull(r.getDeveloper());
		assertNull(r.getEstimate());




	}

	/**
	 * Tests Requirement(Req) constructor.
	 */
	@Test
	public void testRequirementReq() {
		Req req = new Req();
		req.setTest("AcceptedToWorking");
		req.setSummary("Delete unused features");
		req.setPriority(2);
		req.setState("Submitted");
		req.setDeveloper("Prem");
		req.setEstimate("2.00");
		req.setId(1);

		Requirement r = new Requirement(req);
		assertEquals("AcceptedToWorking", r.getAcceptanceTestId());
		assertEquals("Delete unused features", r.getSummary());
		assertEquals(2, r.getPriority());
		assertEquals("Prem", r.getDeveloper());
		assertEquals("2.00", r.getEstimate());
		assertEquals(1, r.getRequirementId());
		assertEquals(Requirement.SUBMITTED_NAME, r.getState().getStateName());

	}

	/**
	 * Tests getRequirementId.
	 */
	@Test
	public void testGetRequirementId() {
		Requirement.setCounter(0);
		Requirement r = new Requirement("SubmittedToAccepted", "Welcome screen need to be bigger");
		assertEquals(0, r.getRequirementId());
		Requirement requirement = new Requirement("SubmittedToAccepted", "Welcome screen need to be bigger");
		assertEquals(1, requirement.getRequirementId());

	}

	/**
	 * Tests getPriority().
	 */
	@Test
	public void testGetPriority() {
		Requirement r = new Requirement("02", "Welcome screen need to be bigger");
		assertEquals(0, r.getPriority());
	}

	/**
	 * Tests getRejectionReason().
	 */
	@Test
	public void testGetRejectionReason() {
		RequirementsReader rReader = null;
		try {
			rReader =  new RequirementsReader(validTestFile);
		} catch (RequirementIOException e) {

			e.printStackTrace();
		}
		Requirement r = new Requirement(rReader.getReqs().get(4));
		assertEquals(Rejection.DUPLICATE, r.getRejectionReason());		
	}

	/**
	 * Tests getRejectionReasonString().
	 */
	@Test
	public void testGetRejectionReasonString() {
		RequirementsReader rReader = null;
		try {
			rReader =  new RequirementsReader(validTestFile);
		} catch (RequirementIOException e) {

			e.printStackTrace();
		}
		Requirement r1 = new Requirement(rReader.getReqs().get(4));
		assertEquals("Duplicate", r1.getRejectionReasonString());
		Requirement r2 = new Requirement(rReader.getReqs().get(0));
		assertEquals(null, r2.getRejectionReasonString());
		Requirement r3 = new Requirement(rReader.getReqs().get(1));
		assertEquals(null, r3.getRejectionReasonString());
		Requirement r4 = new Requirement(rReader.getReqs().get(2));
		assertEquals(null, r4.getRejectionReasonString());
		Requirement r5 = new Requirement(rReader.getReqs().get(3));
		assertEquals(null, r5.getRejectionReasonString());
	}

	/**
	 * Tests setDeveloper().
	 */
	@Test
	public void testSetDeveloper() {
		Requirement r = new Requirement("02", "Welcome screen need to be bigger");
		r.setDeveloper("Michael");
		assertEquals("Michael", r.getDeveloper());
	}

	/**
	 * Tests setAcceptanceTestId().
	 */
	@Test
	public void testSetAcceptanceTestId() {
		Requirement r = new Requirement("SubmittedToAccepted", "Welcome screen need to be bigger");
		r.setAcceptanceTestId("WorkingToCompleted");
		assertEquals("WorkingToCompleted", r.getAcceptanceTestId());

	}


	/**
	 * Tests getXMLReq().
	 */
	@Test
	public void testGetXMLReq() {

		Requirement r = new Requirement("Change state from submitted to Accepted", "SubmittedToAccepted");
		Req req = r.getXMLReq();
		assertEquals("SubmittedToAccepted", req.getTest());
	}

	/**
	 * Tests setCounter().
	 */
	@Test
	public void testSetCounter() {
		Requirement.setCounter(4);
		Requirement r = new Requirement("AcceptedToWorking", "Welcome screen need to be bigger");

		assertEquals(4, r.getRequirementId());



	}

	/**
	 * Tests all the requirement's inner classes.
	 */
	@Test
	public void testTransitionsOfState() {
		Command c1 = new Command(CommandValue.ACCEPT, "Change a requirement's state from Submitted to Accepted.", 
				"SubmittedToAccepted", 1, "csc216 student", "2 hour", null);
		RequirementsReader rReader = null;
		try {
			rReader =  new RequirementsReader(validTestFile);
		} catch (RequirementIOException e) {
			e.printStackTrace();
		}
		Requirement r = new Requirement(rReader.getReqs().get(0));

		//Testing Submitted to Accepted.
		r.getState().updateState(c1);
		assertEquals("Accepted", r.getState().getStateName());


		//Testing with the wrong command while the requirement is the Accepted state.
		try {
			r.getState().updateState(c1);
		} catch (UnsupportedOperationException e) {
			assertEquals("Accepted", r.getState().getStateName());
		}


		//Testing Accepted to Working.
		Command c2 = new Command(CommandValue.ASSIGN, "Change a requirement's state from Accepted to Working.", 
				"AcceptedToWorking", 1, "csc216 student", "2 hour", null);
		r.getState().updateState(c2);
		assertEquals("Working", r.getState().getStateName());

		//Testing with the wrong command while the requirement is the Working state.
		try {
			r.getState().updateState(c2);
		} catch (UnsupportedOperationException e) {
			assertEquals("Working", r.getState().getStateName());
		}

		//Testing Working to completed.
		Command c3 = new Command(CommandValue.COMPLETE, "Change a requirement's state from Working to Completed.", 
				"WorkingToCompleted", 1, "csc216 student", "2 hour", null);
		r.getState().updateState(c3);
		assertEquals("Completed", r.getState().getStateName());

		//Testing with the wrong command while the requirement is the Completed state.
		try {
			r.getState().updateState(c3);
		} catch (UnsupportedOperationException e) {
			assertEquals("Completed", r.getState().getStateName());
		}

		//Testing from Completed to verified
		Command c4 = new Command(CommandValue.PASS, "Change a requirement's state from Working to Completed.", 
				"WorkingToCompleted", 1, "csc216 student", "2 hour", null);
		r.getState().updateState(c4);
		assertEquals("Verified", r.getState().getStateName());

		//Testing with the wrong command while the requirement is the Verified state.
		try {
			r.getState().updateState(c4);
		} catch (UnsupportedOperationException e) {
			assertEquals("Verified", r.getState().getStateName());
		}

		//Testing verified to working
		r.getState().updateState(c2);
		assertEquals("Working", r.getState().getStateName());


		//Testing from Completed to Working
		Requirement r1 = new Requirement(rReader.getReqs().get(3));
		Command c5 = new Command(CommandValue.FAIL, "Change a requirement's state from Working to Completed.", 
				"WorkingToCompleted", 1, "csc216 student", "2 hour", null);
		r1.getState().updateState(c5);
		assertEquals("Working", r1.getState().getStateName());

		//Testing from Completed to Working
		Requirement r2 = new Requirement(rReader.getReqs().get(3));
		r2.getState().updateState(c2);
		assertEquals("Working", r1.getState().getStateName());


		//Testing from Working to Rejected.
		Command c6 = new Command(CommandValue.REJECT, "Change a requirement's state from Working to Completed.", 
				"WorkingToCompleted", 1, "csc216 student", "2 hour", Rejection.DUPLICATE);

		r1.update(c6);
		assertEquals("Rejected", r1.getState().getStateName());

		//Testing with the wrong command while the requirement is the Verified state.
		try {
			r1.update(c4);
		} catch (UnsupportedOperationException e) {
			assertEquals("Rejected", r1.getState().getStateName());
		}

		//Testing from Rejected to Submitted.
		Command c7 = new Command(CommandValue.REVISE, "Change a requirement's state from Working to Completed.", 
				"WorkingToCompleted", 1, "csc216 student", "2 hour", null);
		r1.getState().updateState(c7);
		assertEquals("Submitted", r1.getState().getStateName());

		try {
			r1.getState().updateState(c4);
		} catch (UnsupportedOperationException e) {
			assertEquals("Submitted", r1.getState().getStateName());
		}

		//Testing from Completed to Rejected
		Command c8 = new Command(CommandValue.REJECT, null, null, 0, null, null, Rejection.INFEASIBLE);
		Requirement r3 = new Requirement(rReader.getReqs().get(3));
		r3.getState().updateState(c8);
		assertEquals("Rejected", r3.getState().getStateName());

		//Testing from Submitted to Rejected.
		Requirement req = new Requirement(rReader.getReqs().get(0));
		req.update(c8);
		assertEquals("Rejected", req.getState().getStateName());
		assertEquals("Infeasible", req.getRejectionReasonString());
		assertEquals(0, req.getPriority());
		assertNull(req.getEstimate());
		assertNull(req.getDeveloper());


		//Testing from Accepted to Rejected.
		Requirement r5 = new Requirement(rReader.getReqs().get(1));
		r5.update(c8);
		assertEquals("Rejected", r5.getState().getStateName());
		assertEquals("Infeasible", r5.getRejectionReasonString());
		assertEquals(0, r5.getPriority());
		assertNull(r5.getEstimate());
		assertNull(r5.getDeveloper());

		//Testing from Verified to Rejected.
		Requirement r6 = new Requirement(rReader.getReqs().get(5));
		r6.update(c8);
		assertEquals("Rejected", r6.getState().getStateName());
		assertEquals("Infeasible", r6.getRejectionReasonString());
		assertEquals(0, r5.getPriority());
		assertNull(r5.getEstimate());
		assertNull(r5.getDeveloper());





	}
}