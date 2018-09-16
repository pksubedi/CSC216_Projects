package edu.ncsu.csc216.tracker.model;

import edu.ncsu.csc216.tracker.requirement.Command;
import edu.ncsu.csc216.tracker.requirement.Requirement;
import edu.ncsu.csc216.tracker.xml.RequirementIOException;
import edu.ncsu.csc216.tracker.xml.RequirementsReader;
import edu.ncsu.csc216.tracker.xml.RequirementsWriter;

/**
 * This class maintains the RequirementsList and handles CommandValues from the GUI.
 * @author premsubedi
 */
public class RequirementsTrackerModel {
	
	/** The only instance of RequirementsTrackerModel */
	private static RequirementsTrackerModel model;
	
	/** A requirement instance variable */
	private Requirement requirement;
	
	/** An instance of RequirementsList */
	private RequirementsList reqList;


	private RequirementsTrackerModel() {
		reqList = new RequirementsList();
	}

	/**
	 * Returns an instance of it's own.
	 * @return model an instance of RequirementsTrackerModel.
	 */
	public static RequirementsTrackerModel getInstance() {
		if(model == null) {
			model = new RequirementsTrackerModel();
		}
		return model;
	}

	/**
	 * Saves requirements in to the given file by using RequirementWriter, 
	 * which marshals Req objects into valid XML and writes them to a file.
	 * @param fileName name of the file, where the requirements going to be saved.
	 */
	public void saveRequirementsToFile(String fileName) {
		RequirementsWriter rWriter = null;
		try {
			rWriter = new RequirementsWriter(fileName);
			for(int i = 0; i < reqList.getRequirements().size(); i++) {
				requirement = reqList.getRequirements().get(i);
				rWriter.addReq(requirement.getXMLReq());
			}
			rWriter.marshal();
		} catch (RequirementIOException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Loads requirements from file by using requirementReader, 
	 * which unmarshals XML files and creates Req objects, 
	 * which can then be used to create Requirements.
	 * @param fileName name of a XML file from which Req objects are loaded.
	 */
	public void loadRequirementsFromFile(String fileName) {
		RequirementsReader rReader = null;
		try {
			rReader = new RequirementsReader(fileName);
			reqList.addXMLReqs(rReader.getReqs());
		} catch (RequirementIOException e) {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * Creates new requirement list.
	 */
	public void createNewRequirementsList() {
		reqList = new RequirementsList();
	}

	/**
	 * Returns 2D object array of requirements list.
	 * @return obj 2D array of requirements.
	 */
	public Object[][] getRequirementListAsArray() {

		Object[][] requirementList = new Object[reqList.getRequirements().size()][3];
		if(!reqList.getRequirements().isEmpty()) {
			for(int i = 0; i < reqList.getRequirements().size(); i++) {
				Requirement r = reqList.getRequirements().get(i);
				requirementList[i][0] = r.getRequirementId();
				requirementList[i][1] = r.getState().getStateName();
				requirementList[i][2] = r.getSummary();
			} 
		}
		return requirementList;
	}

	/**
	 * Returns requirement that matches with the given id.
	 * @param id passed requirement Id.
	 * @return req requirement that is returned.
	 */
	public Requirement getRequirementById(int id) {
		requirement = reqList.getRequirementById(id);
		return requirement;
	}

	/**
	 * Executes the given command.
	 * @param reqId requirement Id. 
	 * @param c Command for requirement.
	 */
	public void executeCommand(int reqId, Command c) {
		if(reqList != null) {
		reqList.executeCommand(reqId, c);
		}
	}

	/**
	 * Deletes requirement whose id matches with the given Id.
	 * @param id requirement Id.
	 */ 
	public void deleteRequirementById(int id) {
		reqList.deleteRequirementById(id);
	}

	/**
	 * Adds requirement with the given Id and given summary.
	 * @param id given requirement's Id.
	 * @param summary given requirement's summary.
	 */
	public void addRequirement(String id, String summary) {
		reqList.addRequirement(id, summary);
	}
}
