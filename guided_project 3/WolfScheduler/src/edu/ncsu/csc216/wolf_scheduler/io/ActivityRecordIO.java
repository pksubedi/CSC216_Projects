package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * Keeps all the records of the Activity to the file name.
 * @author Prem Subedi
 */
public class ActivityRecordIO {

	/**
	 * Writes the given list of Activities to provided filename.
	 * @param fileName filename to write activities
	 * @param activities list of activities (courses or events).
	 * @throws IOException exception if file cannot be written.
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> activities) throws IOException {
	    PrintStream fileWriter = new PrintStream(new File(fileName));
	    for(Activity a: activities) {
	        fileWriter.println(a.toString());
	    }
	    fileWriter.close();
	}
	

}
