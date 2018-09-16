package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * WolfScheduler reads in and stores as a list of all of the Activity records
 * stored in a file. Also WolfScheduler creates a schedule 
 * and provides functionality for naming a schedule.
 * @author Prem Subedi
 */
public class WolfScheduler {
	/** list of courses in course catalog */
    private ArrayList<Course> courseCatalog;
    
    /** list of activities in the student's schedule */
    private ArrayList<Activity> schedule;
    
    /** Title of schedule of that particular semester. */
    private String title;

    /**
     * Constructor that initializes all the fields of wolf scheduler class. Also loads courses
     * in the course catalog from the passed parameter (filename). 
     * @param fileName name of the file that contains the list of all Activities.
     * @throws IllegalArgumentException if file cannot be found.
     */
    public WolfScheduler(String fileName) {
        this.courseCatalog = new ArrayList<Course>();
        this.schedule = new ArrayList<Activity>();
        this.title = "My Schedule";

        try {
            courseCatalog.addAll(CourseRecordIO.readCourseRecords(fileName));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot find file");
        }
    }

    /**
     * Returns 2D String array of Courses in the course catalog with limited
     * course information.
     * @return courses 2D array of all the courses.
     */
    public String[][] getCourseCatalog() {
        String[][] courses = new String[courseCatalog.size()][4];
       
            for (int i = 0; i < courseCatalog.size(); i++) {
                Course cor = (Course) courseCatalog.get(i);
                courses[i][0] = cor.getName();
                courses[i][1] = cor.getSection();
                courses[i][2] = cor.getTitle();
                courses[i][3] = cor.getMeetingString();
            }
        return courses;
    }

    /**
     * Returns 2D String array of courses in schedule with limited course
     * information.
     * @return sCourses scheduled courses.
     */
    public String[][] getScheduledActivities() {
        String[][] activity = new String[schedule.size()][];
        for(int i = 0; i < schedule.size(); i++) {
        	activity[i] = schedule.get(i).getShortDisplayArray();
        }
        return activity;
    }

    /**
     * Returns full information of the scheduled courses with all the course
     * information.
     * @return activity full schedule of activities.
     */
    public String[][] getFullScheduledActivities() {
    	String[][] activity = new String[schedule.size()][];
        for(int i = 0; i < schedule.size(); i++) {
        	activity[i] = schedule.get(i).getLongDisplayArray();
        }
        return activity;
    }

    /**
     * Returns course's title
     * @return title course title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * It exports schedule in the filename provided as parameter.
     * @param fileName which used as to store the scheduled courses.
     * @throws IllegalArgumentException if file cannot be written or saved.
     */
    public void exportSchedule(String fileName) {
        try {
            ActivityRecordIO.writeActivityRecords(fileName, schedule);
        } catch (IOException e) {
            throw new IllegalArgumentException("The file cannot be saved.");
        }

    }

    /**
     * Returns course based on the passed name and section parameter.
     * Returns null if the course with the provided name and section can't be
     * found in the catalog.
     * @param name course's name.
     * @param section course's section.
     * @return null if course can't be found.
     */
    public Course getCourseFromCatalog(String name, String section) {
        for (int i = 0; i < courseCatalog.size(); i++) {
            Course course = courseCatalog.get(i);
            if (name.equals(course.getName()) && section.equals(course.getSection())) {
                return course;
            }
        }
        return null;
    }

    /**
     * Returns boolean if the course can be added, otherwise throws conflict 
     * exception if the course cannot be added due to conflict.
     * @param name Course's name.
     * @param section Course's section.
     * @return true if the course can be added.
     * @throws IllegalArgumentException if the course passed is the duplicate
     * of the course already in the schedule.
     */
    public boolean addCourse(String name, String section) {
        Course c = getCourseFromCatalog(name, section);
        if (c == null) {
            return false;
        } else {
            for (int i = 0; i < schedule.size(); i++) {
                if (schedule.get(i).isDuplicate(c)) {
                    throw new IllegalArgumentException("You are already enrolled in " + name);
                }
                
                try {
                	c.checkConflict(schedule.get(i));
                } catch(ConflictException e) {
                	throw new IllegalArgumentException("The course cannot be added due to a conflict.");
                }
            }
        }
        schedule.add(c);
        return true;
    }

    /**
     * Resets schedule.
     */
    public void resetSchedule() {
        this.schedule = new ArrayList<Activity>();

    }

    /**
     * Sets schedule title.
     * @param title Course's title.
     * @throws IllegalArgumentException if title is null.
     */
    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null.");
        }
        this.title = title;

    }

    /**
     * Returns true if the activity can be removed, false otherwise.
     * @param idx index from which activity to be removed.
     * @return false idx is less than zero or greater than schedule's size.
     */
    public boolean removeActivity(int idx) {
        if(idx < 0 || idx >= schedule.size()) {
        	return false;
        }
        schedule.remove(idx);
        return true;
    }

    /**
     * Add event if it is not already in the schedule, otherwise catch conflict exception
     * and throws an exception with an error message.
     * @param eventTitle title of an event.
     * @param eventMeetingDays event meeting days.
     * @param eventStartTime event starting time.
     * @param eventEndTime event ending time.
     * @param eventWeeklyRepeat event weekly repeat.
     * @param eventDetails details of event.
     * @throws IllegalArgumentException if event passed in, is (duplicate of)/conflicts
     * an event already in the schedule.
     */
	public void addEvent(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime,
			int eventWeeklyRepeat, String eventDetails) {
		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime,
				            eventWeeklyRepeat, eventDetails);
		for(int i = 0; i < schedule.size(); i++) {
			if(schedule.get(i).isDuplicate(event)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
			
			try{
				event.checkConflict(schedule.get(i));
			} catch(ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		schedule.add(event);
	}

}
