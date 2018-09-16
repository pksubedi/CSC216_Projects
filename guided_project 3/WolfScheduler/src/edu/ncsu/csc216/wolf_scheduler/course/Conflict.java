
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This Interface class contains the list of methods/behaviors that 
 * class must implement them. 
 * @author Prem Subedi
 */
public interface Conflict {
	
	/** Abstract method that the sub class must implement it
	 * @param possibleConflictingActivity conflicting activities, that can't be added
	 * in the schedule together at the same time.
	 * @throws ConflictException if there is conflict between the activity about to 
	 * be added and the one already in the schedule.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
		
}
