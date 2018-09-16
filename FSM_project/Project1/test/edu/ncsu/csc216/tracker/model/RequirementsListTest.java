package edu.ncsu.csc216.tracker.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.tracker.requirement.Command;
import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.xml.RequirementIOException;
import edu.ncsu.csc216.tracker.xml.RequirementsReader;

/**
 * Tests RequirementList class.
 * @author premsubedi
 *
 */
public class RequirementsListTest {
	private final String validTestFile = "test_files/test-files/exp_req_all.xml";

	/**
	 * Tests RequirementsList constructor.
	 */
	@Test
	public void testRequirement() {
		RequirementsList rl = new RequirementsList();
		assertEquals(0, rl.getRequirements().size());
		rl.addRequirement("SubmittedToAccepted", "System converts requirement's state " +
				"from submitted to Accepted when user clicks ACCEPT button");
		assertEquals(1, rl.getRequirements().size());

	}

	/**
	 * Tests add requirements method.
	 */
	@Test
	public void testAddRequirements() {
		RequirementsList rl = new RequirementsList();
		assertEquals(0, rl.getRequirements().size());
		rl.addRequirement("SubmittedToAccepted", "System converts requirement's state " +
				"from submitted to Accepted when user clicks ACCEPT button");
		assertEquals(1, rl.getRequirements().size());
	}

	/**
	 * Tests addXMLReqs()
	 */
	@Test
	public void testAddXMLReqs() {
		RequirementsList rList = new RequirementsList();
		RequirementsReader rReader = null;
		try {
			rReader =  new RequirementsReader(validTestFile);
		} catch (RequirementIOException e) {
			e.printStackTrace();
		}
		rList.addXMLReqs(rReader.getReqs());
		assertEquals(6, rList.getRequirements().size());	
	}

	/**
	 * Tests getRequirementById().
	 */
	@Test
	public void testGetRequirementById() {
		RequirementsList rList = new RequirementsList();

		RequirementsReader rReader = null;
		try {
			rReader =  new RequirementsReader(validTestFile);
		} catch (RequirementIOException e) {
			e.printStackTrace();
		}
		rList.addXMLReqs(rReader.getReqs());
		//Requirement r = new Requirement(rReader.getReqs().get(3));
		//assertEquals(r, rList.getRequirementById(4));
		assertEquals(null, rList.getRequirementById(45));

	}

	/**
	 * Tests executeCommand() method.
	 */
	@Test
	public void testExecuteCommand() {
		RequirementsList rList = new RequirementsList();

		RequirementsReader rReader = null;
		try {
			rReader =  new RequirementsReader(validTestFile);
		} catch (RequirementIOException e) {
			e.printStackTrace();
		}
		rList.addXMLReqs(rReader.getReqs());
		Command c = new Command(CommandValue.ASSIGN, "Change a requirement's state from Accepted to Working.", 
				"AcceptedToWorking", 1, "csc216 student", "2 hour", null);
		rList.executeCommand(1, c);
		assertEquals("Working", rList.getRequirements().get(1).getState().getStateName());
	
	}

	/**
	 * Tests deleteRequirementById() method.
	 */
	@Test
	public void testDeleteRequirementById() {
		RequirementsList rList = new RequirementsList();

		RequirementsReader rReader = null;
		try {
			rReader =  new RequirementsReader(validTestFile);
		} catch (RequirementIOException e) {
			e.printStackTrace();
		}
		rList.addXMLReqs(rReader.getReqs());
		rList.deleteRequirementById(4);
		assertEquals(5, rList.getRequirements().size());

		rList.deleteRequirementById(50);
		assertEquals(5, rList.getRequirements().size());


	}



}
