
package edu.ncsu.csc216.wolf_scheduler.io;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * @author PremSubedi
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses.  Any invalid
	 * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
	 * a File NotFoundException is thrown.
	 * The code for this method is given in the project description written by Dr. Sarah Heckman.
	 * @param fileName name of the file to read Course records from.
	 * @return list - a list of valid Courses.
	 * @throws FileNotFoundException if the file cannot be found or read.
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner input = new Scanner(new File(fileName));
		ArrayList<Course> list = new ArrayList<Course>();
		while(input.hasNextLine()) {
			try {
				Course course = readCourse(input.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < list.size(); i++) {
					Course c = list.get(i);
					if (course.getName().equals(c.getName()) 
							&& course.getSection().equals(c.getSection())) {
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

		input.close();
		return list;

	}

	/**
	 * Reads each line(a single course) from the file and partitions using delimeter
	 * and returns only valid course records.
	 * I got some of the idea from TA Daniel Schaffer on May 30 at 1:12 pm. 
	 * @param line line from the file.
	 * @return c a course that is returned.
	 * @throws IllegalArgumentException if meeting days is A and there is start time and \
	 * ent time.
	 */
	public static Course readCourse(String line) {
		Scanner reader = new Scanner(line);
		reader.useDelimiter(",");
		Course c = null;
		try {
			String name = reader.next();
			String title = reader.next();
			String section = reader.next();
			int credit = reader.nextInt();
			String instructorId = reader.next();
			String meetingDays = reader.next();

			if (meetingDays.equals("A")) {
				if(reader.hasNext()) {
					reader.close();
					throw new IllegalArgumentException();
				}
				reader.close();
				return new Course(name, title, section, credit, instructorId, meetingDays);
			}

			int startTime = reader.nextInt();
			int endTime = reader.nextInt();
			c = new Course(name, title, section, credit, instructorId, meetingDays, startTime, endTime);
		} catch (NoSuchElementException e) {
			reader.close();
			throw new IllegalArgumentException();

		}
		reader.close();
		return c;
	}


}
