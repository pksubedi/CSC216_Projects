/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * This class keeps the record of all the courses 
 * @author Prem Subedi
 */
public class CourseRecordIO {

	
	/**
	 * Reads the list of courses from the input file
	 * throws FileNotFoundException
	 * @return list the list of the courses
	 * @param fileName name of the course file
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new File(fileName));
		ArrayList<Course> list = new ArrayList<Course>();
		while(fileReader.hasNextLine()) {
			try {
				Course course = readCourse(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < list.size(); i++) {
					Course c = list.get(i);
					if (course.getName().equals(c.getName()) &&
							course.getSection().equals(c.getSection())) {
						//it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					list.add(course);
				}
			} catch (IllegalArgumentException e) {
				//skip the line
			}
		} 

		fileReader.close();
		return list;

	}
	/**
	 * Returns the course
	 * @return c course
	 * @param nextLine line of the file
	 */
	public static Course readCourse(String nextLine) {
		Scanner scan = new Scanner(nextLine);
		scan.useDelimiter(",");
		Course c = null;
		try {
			String n = scan.next();
			String t = scan.next();
			String s = scan.next();
			int co = scan.nextInt();
			String insId = scan.next();
			String mDays = scan.next();
			int stime = 0;
			int etime = 0;
			while(scan.hasNext()) {
				stime = scan.nextInt();
			    etime = scan.nextInt();	
			}
		if (mDays.equals("A") && (stime != 0 || etime != 0)) {
			scan.close();
			throw new IllegalArgumentException();
		}
		c = new Course(n, t, s, co, insId, mDays, stime, etime);
	} catch (NoSuchElementException e) {
		scan.close();
		throw new IllegalArgumentException();
	}
	scan.close();
	return c;
}




/**
 * Writes courses on the filename passed as a parameter
 * @throws IOException
 * @param courses list of the courses
 * @param fileName name of the file
 */
public static void writeCourseRecords(String fileName, ArrayList<Course> courses) throws IOException {
	PrintStream fileWriter = new PrintStream(new File(fileName));
	for (int i = 0; i < courses.size(); i++) {
		fileWriter.println(courses.get(i).toString());
	}
	fileWriter.close();	
}

}
