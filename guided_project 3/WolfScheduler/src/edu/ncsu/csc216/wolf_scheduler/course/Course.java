package edu.ncsu.csc216.wolf_scheduler.course;

/** 
 * This is a course class that extends from it's superclass Activity
 * which represents a single course object in NC state University system.
 * @author Prem Subedi
 */
public class Course extends Activity {
	
	/* Course name */
	private String name;

	/* Course section */
	private String section;

	/* Course credit hours*/
	private int credits;

	/* Instructor's id */
	private String instructorId;

	/**
	 * Constructor for all non-arranged courses which initializes the fields.
	 * @param name course name.
	 * @param title course title
	 * @param section course section.
	 * @param credits course credit hours.
	 * @param instructorId course instructor's unity id.
	 * @param meetingDays course meeting days.
	 * @param startTime course starting time.
	 * @param endTime course ending time.
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays, int startTime,
			int endTime) {
		super(title, meetingDays, startTime, endTime);
		this.setName(name);
		this.setSection(section);
		this.setCredits(credits);
		this.setInstructorId(instructorId);

	}

	/**Creates the course with name, title, section, credits, instructorId, meeting days for
	 * the course that are arranged.
	 * @param name course name
	 * @param title course's title.
	 * @param section course section
	 * @param credits course credit hours
	 * @param instructorId course instructor's unity id.
	 * @param meetingDays course's meeting days.
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns course name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets course name
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is empty, null, length less than 4
	 * or length greater than 6.
	 */
	private void setName(String name) {
		if(name == null || name.equals("") || name.length() < 4 || name.length() > 6) {
			throw new IllegalArgumentException("Invalid name");
		}
		this.name = name;
	}

	/**
	 * Returns course section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Set's or updates course section.
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is null 
	 * or if it is not starts with digit or (exactly 3 digits long).
	 */
	public void setSection(String section) {
		if (section == null || section.equals("") || section.length() != 3) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException();
			}
		}
		this.section = section;
	}
	
	/**
	 * Returns course number of credit hours.
	 * @return credits the credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets updates or filters the valid course credit hours.
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credit hours is less than 1 or
	 * greater than 5
	 */
	public void setCredits(int credits) {
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException("Invalid credits");
		}
		this.credits = credits;
	}
	
	/**
	 * Returns instructor's unity Id.
	 * @return instructorId the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets instructor unity Id.
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructor id is null or an empty string.
	 */
	public void setInstructorId(String instructorId) {
		if(instructorId == null || instructorId.equals("")) {
			throw new IllegalArgumentException("Invalid instructor Id");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Sets the valid meeting days for courses.
	 * @throws IllegalArgumentException if meeting days is not equal to M T W H F & A.
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if(meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		for(int i = 0; i < meetingDays.length(); i++) {
			if(meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T' &&
					meetingDays.charAt(i) != 'W' && meetingDays.charAt(i) != 'H' &&
					meetingDays.charAt(i) != 'F' && meetingDays.charAt(i) != 'A') {
				throw new IllegalArgumentException();
			}
			
			if(meetingDays.charAt(i) == 'A' && meetingDays.length() != 1) {
				throw new IllegalArgumentException();
			}
		}
		
		super.setMeetingDays(meetingDays);
	}
	
	
	/** 
	 * Overrides from Activity hash code.
	 * @return result result of the hash code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/** 
	 * Compares two courses and see if they are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	/**
	 * Overridden method which returns brief info of each course
	 * in the form of string array.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[4];
		shortDisplay[0] = getName();
		shortDisplay[1] = getSection();
		shortDisplay[2] = super.getTitle();
		shortDisplay[3] = super.getMeetingString();
		
		return shortDisplay;
	}

	/**
	 * Overridden method which returns detailed info of each course 
	 * in the form of String array.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = getName();
		longDisplay[1] = getSection();
		longDisplay[2] = super.getTitle();
		longDisplay[3] = Integer.toString(getCredits());
		longDisplay[4] = getInstructorId();
		longDisplay[5] = super.getMeetingString();
		longDisplay[6] = "";
		return longDisplay;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course.
	 */
	@Override
	public String toString() {
		if (super.getMeetingDays().equals("A")) {
			return name + "," + super.getTitle() + "," + section + "," + credits + "," + instructorId + "," + super.getMeetingDays();
		}
		return name + "," + super.getTitle() + "," + section + "," + credits + "," + instructorId + ","
		+ super.getMeetingDays() + "," + super.getStartTime() + "," + super.getEndTime(); 
	}

	/**
	 * Returns true if passed activity (instance of course) 
	 * is the duplicate of the current course.
	 * @return boolean true if passed activity is the duplicate.
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Course) {
			Course c = (Course) activity;
			if(c.getName().equals(this.name)) {
				return true;
			}
			
		}
		return false;
	}

	

}