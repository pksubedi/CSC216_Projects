/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * WolfScheduler class which reads in and stores all the course records 
 * in file
 * @author Prem Subedi
 */
public class WolfScheduler {
	private ArrayList<Course> courseCatalog;
	private ArrayList<Course> schedule;
	private String title;

	/**
	 * Constructor which constructs course catalog,
	 * course schedule and title of the schedule.
	 * @param fileName name of the file
	 */
	public WolfScheduler(String fileName) {
		this.courseCatalog = new ArrayList<Course>();
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
		try {
			courseCatalog.addAll(CourseRecordIO.readCourseRecords(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file");
		}
	}
    /**
     * getter method that returns 2D array of scheduled courses
     * @return cor 2D array of scheduled courses
     */
	public String[][] getScheduledCourses() {
		String [][] cor = new String[schedule.size()][3];
		for(int i = 0; i < schedule.size(); i++) {
			Course course = schedule.get(i);
			cor[i][0] = course.getName();
			cor[i][1] = course.getSection();
			cor[i][2] = course.getTitle();	
		}
		return cor;
	}

    /**
     * This method returns 2D array of full scheduled courses
     * @return cor 2D array of full scheduled courses
     */
	public String[][] getFullScheduledCourses() {
		String [][] cor = new String[schedule.size()][6];
		for(int i = 0; i < schedule.size(); i++) {
			Course course = schedule.get(i);
			cor[i][0] = course.getName();
			cor[i][1] = course.getSection();
			cor[i][2] = course.getTitle();
			cor[i][3] = Integer.toString(course.getCredits());
			cor[i][4] = course.getInstructorId();
			cor[i][5] = course.getMeetingString();	
		}
		return cor;
	}

    /**
     * This method returns course catalog
     * @return cor course catalog
     */
	public String[][] getCourseCatalog() {
		String [][] cor = new String[courseCatalog.size()][3];

		for(int i = 0; i < courseCatalog.size(); i++) {
			Course course = courseCatalog.get(i);
			cor[i][0] = course.getName();
			cor[i][1] = course.getSection();
			cor[i][2] = course.getTitle();	
		}
		return cor;
	}

    /**
     * Returns the title of the schedule 
     * @return title title of the schedule
     */
	public String getTitle() {
		return title;
	}
    /**
     * This mutator method throws IllegalArgumentException 
     * if title is null
     * @param t title of the schedule
     */
	public void setTitle(String t){
		if(t == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = t;
	}

    /**
     * This method returns course with given name and section as 
     * parameters
     * @return course courses from the catalog
     * @param name course name
     * @param section course section
     */
	public Course getCourseFromCatalog(String name, String section){
		for (int i = 0; i < courseCatalog.size(); i++) {
			Course course = courseCatalog.get(i);
			if(name.equals(course.getName()) && section.equals(course.getSection())) {
				return course;
			}
		}
		return null;
	}

	/**
	 * Returns true if course can be added, false otherwise
	 * @param name name of the course
	 * @param section course section
	 * @return true 
	 */
	public boolean addCourse(String name, String section) {
		Course c = getCourseFromCatalog(name, section);
		if(c == null){
			return false;
		} else {
			for(int i = 0; i < schedule.size(); i++){
				if(schedule.get(i).getName().equals(name)){
					throw new IllegalArgumentException("You are already enrolled in " + c.getName());	
				}
			}
		}
		schedule.add(c);
		return true;	

	}

    /**
     * Returns true if the course can be removed, false otherwise
     * @return false
     * @param name course's name
     * @param section course's section

     */
	public boolean removeCourse(String name, String section) {
		for(int i = 0; i < schedule.size(); i++) {
			Course c = schedule.get(i);
			if(name.equals(c.getName()) && section.equals(c.getSection())) {
				schedule.remove(c);
				return true;	
			}
		}
		return false;
	}

	/**
	 * Exports schedule in the filename passed in as parameter 
	 * @param fileName file name for the schedule
	 */
	public void exportSchedule(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved");
		}	
	}

	/**
	 * Resets the schedule 
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Course>();	
	}
}


