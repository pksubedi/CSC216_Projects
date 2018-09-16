package edu.ncsu.csc216.tracker.requirement.enums;

/**
 * This is an enumeration that names the possible reasons for rejection of a requirement.
 * @author premsubedi
 */
public enum Rejection {

	/** Duplicate requirement */
	DUPLICATE,
	
	/** Inconvenient requirement */
	INFEASIBLE, 
	
	/** Too large requirement */
	TOO_LARGE, 
	
	/** Requirement that doesn't have scope */
	OUT_OF_SCOPE, 
	
	/** Not appropriate requirement */
	INAPPROPRIATE 
}
