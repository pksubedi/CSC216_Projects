/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Activity class and its methods especially CheckConflict method.
 * @author premsubedi
 */
public class ActivityTest {

	/**
	 * Tests for no conflict. The test code was written by Dr. Heckman. It was available on project description.
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.
	 * Activity#checkConflict(edu.ncsu.csc216.wolf_scheduler.course.Activity)}.
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "TH", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities "
	        		+ "at the same time on completely distinct days were compared.");
	    }
	    
	  //Update a1 with the same meeting days and a start time that overlaps the end time of a2
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1445, 1530);
	    try {
	        a1.checkConflict(a2);
	    } catch (ConflictException e) {
//	        //Check that the internal state didn't change during method call.
//	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
//	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    	 assertEquals("Schedule conflict.", e.getMessage());
	    }
	    a1.setActivityTime(1315, 1345);
	    try {
	        a1.checkConflict(a2);
	    } catch (ConflictException e) {
	        assertEquals("Schedule conflict.", e.getMessage());
	    }
	    
	    a1.setActivityTime(1445, 1515);
	    try {
	        a2.checkConflict(a1);
	    } catch (ConflictException e) {
	        assertEquals("Schedule conflict.", e.getMessage());
	    }
	    
	    
	    a2.setMeetingDays("MWF");
	    try {
	    	a1.checkConflict(a2);
	    	assertEquals("TH 2:45PM-3:15PM", a1.getMeetingString());
	    	assertEquals("MWF 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	    	fail("A ConflictException was thrown when two Activities with distinct meeting days are compared.");
	    }	
	    a1.setMeetingDays("A");
	    a2.setMeetingDays("A");
	    try {
	    	a1.checkConflict(a2);
	    	assertEquals("Arranged", a1.getMeetingString());
	    	assertEquals("Arranged", a2.getMeetingString());
	    } catch (ConflictException e) {
	    	fail("A ConflictException was thrown when two Activites with Arranged meeting days are compared.");
	    	
	    }
	    
	    
	   
	}

}
