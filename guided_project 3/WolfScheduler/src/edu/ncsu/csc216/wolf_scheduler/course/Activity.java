package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This is a general or parent class of Course and Event.
 * @author Prem Subedi
 */
public abstract class Activity implements Conflict {

	/** Activity's title */
	private String title;

	/** Activity's meeting days */
	private String meetingDays;

	/** Activity's starting time */
	private int startTime;

	/** Activity's ending time */
	private int endTime;

	/**
	 * Activity constructor which initializes it's fields.
	 * @param title Activity's title
	 * @param meetingDays Activity's meeting days
	 * @param startTime Activity's starting time
	 * @param endTime Activity's ending time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		this.setTitle(title);
		this.setMeetingDays(meetingDays);
		this.setActivityTime(startTime, endTime);
	}

	/** Abstract method which returns short list of string arrays from it's sub classes
	 * @return shortDisplay array
	 */
	public abstract String[] getShortDisplayArray();

	/** Abstract method which returns long list of string arrays from it's sub classes 
	 * @return longDisplay array
	 */
	public abstract String[] getLongDisplayArray();

	/** 
	 * Returns true if it is duplicate of the activity already in the course catalog.
	 * @param activity that is to be compared with the one already in the schedule or catalog.
	 * @return true if the passed activity parameter is the duplicate of the course or event.
	 */
	public abstract boolean isDuplicate(Activity activity);

	/** 
	 * Check to see if the passed activity conflicts with the current activity
	 * Throws conflictException with an error message, if at least a activity is arranged,
	 * or if their start time and end time overlapped, even by a minute.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		if(possibleConflictingActivity.meetingDays.equals("A") || this.meetingDays.equals("A")) {
			return;
		}
		for(int i = 0; i < meetingDays.length(); i++) {
			if(possibleConflictingActivity.getMeetingDays().contains("" + this.meetingDays.charAt(i))) {
				if(possibleConflictingActivity.getStartTime() >= this.getStartTime() 
						&& possibleConflictingActivity.getEndTime() <= this.getEndTime()) {
					throw new ConflictException("Schedule conflict.");
				}

				if(possibleConflictingActivity.getStartTime() < this.getStartTime() 
						&& possibleConflictingActivity.getEndTime() >= this.getStartTime()) {
					throw new ConflictException("Schedule conflict.");
				}

				if(possibleConflictingActivity.getStartTime() <= this.getEndTime() 
						&& possibleConflictingActivity.getEndTime() > this.getEndTime()) {
					throw new ConflictException("Schedule conflict.");
				}
			} 
		}
	}


	/**
	 *  Returns Activity title.
	 *  @return title course title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *  Sets Activity title 
	 *  @param title course title.
	 *  @throws IllegalArgumentException if title is null or an empty.
	 */
	public void setTitle(String title) {
		if(title == null || title.equals("")) {
			throw new IllegalArgumentException("Invalid title");
		}
		this.title = title;
	}

	/**
	 * Returns activity meeting days.
	 * @return meetingDays the activity's meetingDays.
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets or updates activity meeting days.
	 * @param meetingDays the meetingDays to set.
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns Activity's starting time.
	 * @return startTime the Activity's startTime.
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Sets or updates Activity's start and end time.
	 * @param startTime the Activity's  starting Time.
	 * @param endTime the Activity's ending time.
	 * @throws IllegalArgumentException if starting time or ending time is 
	 * less than zero or more than 2359, if start time is greater than end time
	 * or if arranged activity's start time and end time is not equals 0.
	 */
	public void setActivityTime(int startTime, int endTime) {
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
		if(getMeetingDays().equals("A") && (startTime != 0 || endTime != 0)) {
			throw new IllegalArgumentException();
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns Activity's ending time.
	 * @return endTime the Activity's endTime.
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * This method converts time from military to standard 12 hours format(AM/PM)
	 * and returns string representation of day and time.
	 * I used the idea that I learned from TA Derek Batts on Spring 2017.
	 * @return meetInfo string of day and time.
	 */
	public String getMeetingString() {
		if(getMeetingDays().charAt(0) == 'A')  // Meeting String for arranged courses.
			return "Arranged";

		String meetInfo = getMeetingDays() + " ";  // Meeting days part of the meeting string

		int sT = getStartTime();      // start time part of the meeting string.
		boolean sPM = false;
		if(sT >= 1200) 
			sPM = true;

		if(sT >= 1300) 
			sT = sT - 1200;
		if(sT < 100)
			sT += 1200;

		meetInfo += (sT / 100) + ":";

		if((sT % 100) < 10) 
			meetInfo += "0" + (sT % 100);
		else 
			meetInfo += sT % 100;

		if(sPM) 
			meetInfo += "PM";
		else 
			meetInfo += "AM";

		meetInfo += "-";


		int eT = getEndTime();    // end time part of the meeting string.
		boolean ePM = false;
		if(eT >= 1200)
			ePM = true;

		if(eT >= 1300) 
			eT = eT - 1200;

		if(eT < 100)
			eT += 1200;

		meetInfo += (eT / 100) + ":";
		if((eT % 100) < 10)
			meetInfo += "0" + (eT % 100);
		else 
			meetInfo += eT % 100;

		if(ePM) 
			meetInfo += "PM";
		else 
			meetInfo += "AM";
		return meetInfo;
	}

	/** 
	 * Overridden hasCode method for activity.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/** 
	 * Overridden equals method that compares two activity object and check to
	 * see if they are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
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

	/**
	 * Returns string representation of the Activity object.
	 * @return title, meeting days, start time and end time.
	 */
	public String toString() {
		return title + "," + meetingDays + "," + startTime + "," + endTime;
	}

}