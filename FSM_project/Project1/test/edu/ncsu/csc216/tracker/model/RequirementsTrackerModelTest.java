package edu.ncsu.csc216.tracker.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.tracker.requirement.Command;
import edu.ncsu.csc216.tracker.requirement.enums.CommandValue;
import edu.ncsu.csc216.tracker.xml.RequirementIOException;
import edu.ncsu.csc216.tracker.xml.RequirementsReader;

/**
 * Tests RequirementsTrackerModel class.
 * @author premsubedi
 */
public class RequirementsTrackerModelTest {
	private final String validTestFile1 = "test_files/test-files/exp_req_all.xml";
	private final String validTestFile2 = "test_files/test-files/act_req_all.xml";
	private final String inValidTestFile = "test_files/test-files/req_all.txt";
	private final String summary = "The system shall change a requirement's state from "
			+ "Submitted to Accepted when the user supplies an estimate of the time to "
			+ "implement the requirement and clicks the Accept button.";
	
	/**
	 * Tests saveRequirementsToFile().
	 */
	@Test
	public void testSaveRequirementsToFile() {
		RequirementsTrackerModel rtml = RequirementsTrackerModel.getInstance();
		rtml.saveRequirementsToFile(validTestFile2);
		checkFiles(validTestFile1, validTestFile2);	
		try {
			rtml.saveRequirementsToFile(inValidTestFile);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid file format", e.getMessage());
		}
	
	}
	
	/**
	 * Tests loadRequirementsFromFile() method.
	 */
	@Test
	public void testLoadRequirementFromFile() {
		RequirementsTrackerModel rtml = RequirementsTrackerModel.getInstance();
		rtml.loadRequirementsFromFile(validTestFile1);
		Object[][] testArray = rtml.getRequirementListAsArray();
		assertEquals(1, testArray[1][0]);
		assertEquals("Accepted", testArray[1][1]);
		assertEquals(summary, testArray[1][2]);
		assertEquals(23, testArray[4][0]);
		assertEquals("Rejected", testArray[4][1]);	
		
		
	}
	
	/**
	 * Tests createNewRequirementsList().
	 */
	@Test
	public void testCreateNewRequirementsList() {
		RequirementsTrackerModel rtml = RequirementsTrackerModel.getInstance();
		rtml.createNewRequirementsList();
		RequirementsList rList = new RequirementsList();
		assertEquals(0, rList.getRequirements().size());
		
		
	}
	
	/**
	 * Tests getRequirementsListAsArray().
	 */
	@Test
	public void testGetRequirementsListAsArray() {
		RequirementsTrackerModel rtml = RequirementsTrackerModel.getInstance();
		rtml.createNewRequirementsList();
		rtml.loadRequirementsFromFile(validTestFile1);
		Object[][] array = rtml.getRequirementListAsArray();
		assertEquals(1, array[1][0]);
		assertEquals("Accepted", array[1][1]);
		assertEquals(summary, array[1][2]);
		assertEquals(23, array[4][0]);
		assertEquals("Rejected", array[4][1]);	
		
		
	}
	
	/**
	 * Tests getRequirementById().
	 */
	@Test
	public void testGetRequirementById() {
		RequirementsTrackerModel rtml = RequirementsTrackerModel.getInstance();
		assertEquals(null, rtml.getRequirementById(100));
	}
	
	/**
	 * Tests executeCommand().
	 */
	@Test
	public void testExecuteCommand() {
		RequirementsTrackerModel rtml = RequirementsTrackerModel.getInstance();
		rtml.loadRequirementsFromFile(validTestFile1);
		
		Command c = new Command(CommandValue.ASSIGN, "Change a requirement's state from Accepted to Working.", 
				"AcceptedToWorking", 1, "csc216 student", "2 hour", null);
		//rList.executeCommand(1, c);
		rtml.executeCommand(1, c);
		assertEquals("Working", rtml.getRequirementById(1).getState().getStateName());
	
	}
	
	/**
	 * Tests deleteRequirementById().
	 */
	@Test
	public void testDeleteRequirementById() {
		RequirementsTrackerModel rtml = RequirementsTrackerModel.getInstance();
		RequirementsList rList = new RequirementsList();

		RequirementsReader rReader = null;
		try {
			rReader =  new RequirementsReader(validTestFile1);
		} catch (RequirementIOException e) {
			e.printStackTrace();
		}
		rList.addXMLReqs(rReader.getReqs());
		rList.deleteRequirementById(4);
		assertEquals(5, rList.getRequirements().size());

		rtml.deleteRequirementById(50);
		assertEquals(5, rList.getRequirements().size());
	}
	
	/**
	 * Tests addRequirement().
	 */
	@Test
	public void testAddRequirement() {
		RequirementsTrackerModel rtml = RequirementsTrackerModel.getInstance();
		rtml.createNewRequirementsList();
		rtml.loadRequirementsFromFile(validTestFile1);
		rtml.addRequirement("WorkingToCompleted", "System shall change requirement's"
				            + " state from working to Completed");
		assertEquals(33, rtml.getRequirementById(33).getRequirementId());
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			RequirementsReader expReader = new RequirementsReader(expFile);
			RequirementsReader actReader = new RequirementsReader(actFile);
			
			assertEquals(expReader.getClass(), actReader.getClass());
		} catch (RequirementIOException e) {
			e.getStackTrace();
		}
	}

}
