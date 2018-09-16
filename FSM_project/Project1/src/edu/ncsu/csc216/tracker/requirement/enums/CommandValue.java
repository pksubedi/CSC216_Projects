package edu.ncsu.csc216.tracker.requirement.enums;

/**
 * This is an enumeration that names the commands 
 * that the user gives in the GUI for processing by the internal FSM.
 * @author premsubedi
 */
public enum CommandValue {

/** Accepted requirement: Submitted -> Accepted*/
ACCEPT, 

/** Rejected requirement: (Submitted, Accepted, Working, Completed, Verified)  -> Rejected*/
REJECT, 

/** Revise requirement: Rejected -> Submitted*/
REVISE, 

/** Assign requirement: Accepted -> Working */
ASSIGN, 

/** Completed requirement: Working -> Completed */
COMPLETE,

/** Verified requirement that passed: Completed -> Verified */
PASS,

/** Verified requirement that failed: Completed -> Working */
FAIL

}




