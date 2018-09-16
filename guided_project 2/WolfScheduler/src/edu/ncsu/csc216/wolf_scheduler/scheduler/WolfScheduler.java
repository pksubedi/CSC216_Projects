package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
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
    
    /** List of activities in schedule */
    private ArrayList<Activity> schedule;
    
    /** Name of the title of the schedule */
    private String title;

    /**
     * Constructor that initializes all the fields of wolf scheduler class.
     * @param fileName name of the file that contains the list of all Activities.
     * @throws IllegalArgumentException while file can't found.
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
     * @return sCourses full schedule of courses.
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
     * @throws IllegalArgumentException if file can't be saved.
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
     * Returns true if the course can be added, false otherwise.
     * @param name Course's name.
     * @param section Course's section.
     * @return true if the course can be added.
     * @throws IllegalArgumentException if the course is the duplicate of the one
     * already in the schedule.
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
     * Updates the title.
     * @param title Course's title.
     * @throws IllegalArgumentException if title is null.
;     */
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
     * Add event if it is not already in the schedule
     * @param eventTitle title of an event.
     * @param eventMeetingDays event meeting days.
     * @param eventStartTime event starting time.
     * @param eventEndTime event ending time.
     * @param eventWeeklyRepeat event weekly repeat.
     * @param eventDetails details of event.
     * @throws IllegalArgumentException with error message if an event is the duplicate of the one 
     * already in the schedule.
     */
	public void addEvent(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime,
			int eventWeeklyRepeat, String eventDetails) {
		Event e = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime,
				            eventWeeklyRepeat, eventDetails);
		for(int i = 0; i < schedule.size(); i++) {
			if(schedule.get(i).isDuplicate(e)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
		}
		schedule.add(e);
	}

}
