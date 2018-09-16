package edu.ncsu.csc216.tracker.model;
import edu.ncsu.csc216.tracker.requirement.Requirement;
import edu.ncsu.csc216.tracker.xml.Req;
import edu.ncsu.csc216.tracker.requirement.Command;
import java.util.*;

/**
 * This is a concrete class that maintains the current list of Requirements
 * in the ReqKeeper system.
 * @author Prem Subedi
 *
 */
public class RequirementsList {
	
	/** requirement of the ReqKeeper system. */
	private Requirement reqs;
	
	/** list of requirements */
	private List<Requirement> list;
	
	/**
	 * Constructs the list of requirements and sets counter to 0.
	 */
	public RequirementsList() {
		list = new ArrayList<Requirement>();
		Requirement.setCounter(0);
	}
	
	/**
	 * Adds custom requirement and returns Id of the last requirement added.
	 * @param testId acceptance test id for requirement.
	 * @param summary requirement's summary.
	 * @return integer value.
	 */
	public int addRequirement(String summary, String testId) {
		list.add(new Requirement(summary, testId));
		return list.get(list.size() - 1).getRequirementId();
	}
	
	/**
	 * Converts Reqs from the list of Req objects in the XML file to Requirements and
	 * adds to list.
	 * Sets counter to maximum id among all the rquirements plus 1.
	 * @param reqList reqs list in XML file.
	 */
	public void addXMLReqs(List<Req> reqList) {
		int maxId = 0;
		for(int i = 0; i < reqList.size(); i++) {
			 reqs = new Requirement(reqList.get(i));
			 if(reqs.getRequirementId() > maxId) {
				 maxId = reqs.getRequirementId();
			 }
			 list.add(reqs);
		}
		Requirement.setCounter(maxId + 1);
	}
	
	/**
	 * Returns list of requirements.
	 * @return list a list of requirements.
	 */
	public List<Requirement> getRequirements() {
		return list;
	}
	
	/**
	 * Returns the requirement whose id matches with 
	 * the given or passed id. Returns null, if no 
	 * requirement is found in the list with the given
	 * id.
	 * @param id requirement Id.
	 * @return reqs requirement.
	 */
	public Requirement getRequirementById(int id) {
		for(int i = 0; i < list.size(); i++) {
			Requirement r = list.get(i);
			if(id == r.getRequirementId()) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * Executes command for the updates of the requirement.
	 * @param id requirement id.
	 * @param c command for the requirement.
	 */
	public void executeCommand(int id, Command c) {
		reqs = getRequirementById(id);
		if(reqs != null) {
		reqs.update(c);
		}
	
	}
	
	/** 
	 * Deletes the requirement, if it's id matches with the given id.
	 * @param id requirement Id.
	 */
	public void deleteRequirementById(int id) {
		for(int i = 0; i < list.size(); i++) {
			reqs = list.get(i);
			if(id == reqs.getRequirementId()) {
				list.remove(i);
			}
		}
		
	}

}
