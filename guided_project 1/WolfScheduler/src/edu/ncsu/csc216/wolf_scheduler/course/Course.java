/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;


/**
 * This is a course class
 * @author Prem Subedi 
 */
public class Course {

	/** Course's name */
	private String name;
	/** Course's title */
	private String title;
	/** Course's section */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Returns course name
	 * @return name the course name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 4 or
	 * greater than 6, an IllegalArgumentException is thrown.
	 * @param name the course name
	 * @throws IllegalArgumentException
	 * if name is null or length is less than 4 or greater than 6
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name.length() < 4 || name.length() > 6) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * Returns course title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets course title
	 * @param title the course title
	 */
	public void setTitle(String title) {
		if (title == null || title.equals("")) {
			throw new IllegalArgumentException();
		}
		this.title = title;

	}

	/**
	 * Returns course section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets course section
	 * 
	 * @param section
	 *            the section to set
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
	 * Returns the number of credit hours
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the number of course's credit hours
	 * 
	 * @param credits
	 *            the credits to set
	 */
	public void setCredits(int credits) {
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Returns instructor unity Id
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets instructor unity Id
	 * 
	 * @param instructorId
	 *            the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns Course'sthe meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets Course's meeting days
	 * 
	 * @param meetingDays
	 *            the meetingDays to set
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T' && meetingDays.charAt(i) != 'W'
					&& meetingDays.charAt(i) != 'H' && meetingDays.charAt(i) != 'F' && meetingDays.charAt(i) != 'A') {
				throw new IllegalArgumentException();
			}
			if (meetingDays.charAt(i) == 'A' && meetingDays.length() != 1) {
				throw new IllegalArgumentException();
			}
		}

		this.meetingDays = meetingDays;
	}
    /**
     * This methods returns the String representing day and time
     * @return ret ret is a string representing day and time
     */
	public String getMeetingString() {
		String ret = "";
		if (getMeetingDays().charAt(0) == 'A')
			return "Arranged";
		ret += getMeetingDays() + " ";
		int sTime = getStartTime();
		int eTime = getEndTime();
		boolean sTimeIsPM = false;
		if (getStartTime() >= 1200)
			sTimeIsPM = true;
		if (getStartTime() >= 1300) {
			sTime = getStartTime() - 1200;
		}
		if (sTime < 100) {
			sTime += 1200;
		}
		boolean eTimeIsPM = false;
		if (getEndTime() >= 1200)
			eTimeIsPM = true;
		if (getEndTime() >= 1300) {
			eTime = getEndTime() - 1200;
		}
		if (eTime < 100) {
			eTime += 1200;
		}
		ret += (sTime / 100) + ":";
		if ((sTime % 100) < 10)
			ret += "0" + sTime % 100;
		else
			ret += (sTime % 100);
		if (sTimeIsPM) {
			ret += "PM";
		} else {
			ret += "AM";
		}
		ret += "-";
		ret += (eTime / 100) + ":";
		if ((eTime % 100) < 10)
			ret += "0" + eTime % 100;
		else
			ret += (eTime % 100);
		if (eTimeIsPM) {
			ret += "PM";
		} else {
			ret += "AM";
		}

		return ret;
	}

	/**
	 * Returns course's starting time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Sets course's starting time
	 * 
	 * @param startTime
	 *            the startTime to set
	 */

	/**
	 * Returns course's ending time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the course's ending time
	 * @param startTime starting time of the course
	 * @param endTime time that the course ends
	 */
	public void setCourseTime(int startTime, int endTime) {
		if (startTime < 0 || startTime > 2359) {
			throw new IllegalArgumentException();
		}
		if (endTime < 0 || endTime > 2359) {
			throw new IllegalArgumentException();
		}
		if (startTime > endTime) {
			throw new IllegalArgumentException();
		}
		if (startTime % 100 >= 60 || endTime % 100 >= 60) {
			throw new IllegalArgumentException();
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Course constructor constructs those fields
	 * @param name Course name	       
	 * @param title Course's title       
	 * @param section Course's section          
	 * @param credits Course's credit hour          
	 * @param instructorId Course's instructorId            
	 * @param meetingDays Course's meetingDays       
	 * @param startTime Course's starting time       
	 * @param endTime Course's ending time
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		this.setName(name);
		this.setTitle(title);
		this.setSection(section);
		this.setCredits(credits);
		this.setInstructorId(instructorId);
		this.setMeetingDays(meetingDays);
		this.setCourseTime(startTime, endTime);

	}

	/**
	 * Course's name
	 * @param name course's name
	 * @param title course's title
	 * @param section course's section
	 * @param credits course's credits
	 * @param instructorId course's instructorId
	 * @param meetingDays course's meeting days
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {

		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns hashcode as a integer
	 * @return result
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + credits;
		result = prime * result + endTime;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Boolean method which returns true 
	 * if this object is equal to
	 * the passed in object as parameter
	 * @return true returns true if both object are equal
	 * @param obj object instance of object class
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (endTime != other.endTime)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
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
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (meetingDays.equals("A")) {
			return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays;
		}
		return name + "," + title + "," + section + "," + credits + "," + instructorId + "," + meetingDays + ","
				+ startTime + "," + endTime;
	}
}
