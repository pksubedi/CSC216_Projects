/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * This is an event class the specialized version of Activity.
 * @author Prem Subedi
 */
public class Event extends Activity {

	/** Number of week, that event repeats every after. */
	private int weeklyRepeat;
	
	/** Details information of an event */
	private String eventDetails;

	/**
	 * The event constructor which initializes it's fields.
	 * @param title Event's title
	 * @param meetingDays Event's meeting days
	 * @param startTime Event's starting time
	 * @param endTime Event's ending time
	 * @param weeklyRepeat number of weeks that Event repeats every after.
	 * @param eventDetails Event's information.
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		this.setWeeklyRepeat(weeklyRepeat);
		this.setEventDetails(eventDetails);
		this.setMeetingDays(meetingDays);
	}
	
	/** 
	 * Sets the valid meeting days for events.
	 * @throws IllegalArgumentException if meeting days is not equal to M, T, W, H, F, S, U or A.
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if(meetingDays == null || meetingDays.equals("")) {
			throw new IllegalArgumentException("Invalid meeting days");
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T' 
					&& meetingDays.charAt(i) != 'W' && meetingDays.charAt(i) != 'H' 
					&& meetingDays.charAt(i) != 'F' && meetingDays.charAt(i) != 'S' 
					&& meetingDays.charAt(i) != 'U') {
				throw new IllegalArgumentException();
			}
		}
		if (meetingDays.charAt(0) == 'A' && meetingDays.length() != 1 &&
				(super.getStartTime() != 0 || super.getEndTime() != 0)) {
			throw new IllegalArgumentException();
		}
		super.setMeetingDays(meetingDays);

	}

	/**
	 * Returns weekly or biweekly or monthly repetition of an event.
	 * @return weeklyRepeat number of times the Event occur in the period of 4 weeks.
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}

	/**
	 * Sets how often the Event is repeated.
	 * @param weeklyRepeat number of times the Event occur in the period of 4 weeks.
	 * @throws IllegalArgumentException if number of weeklyRepeat is less than 1 or
	 * greater than 4.
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if(weeklyRepeat < 1 || weeklyRepeat > 4) {
			throw new IllegalArgumentException("Invalid weekly repeat");
		}
		this.weeklyRepeat = weeklyRepeat;
	}

	/**
	 * Returns Event details.
	 * @return eventDetails detailed info of an Event.
	 */ 
	public String getEventDetails() {
		return eventDetails;
	}


	/**
	 * Sets event details, throws exception if event details is invalid.
	 * @param eventDetails detailed info of an Event.
	 * @throws IllegalArgumentException if event details is null.
	 */
	public void setEventDetails(String eventDetails) {
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details");
		}
		this.eventDetails = eventDetails;

	}

	/** 
	 * Returns event description in the form of short String array with first two elements empty. 
	 * @return shortDisplay String array that is returned
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[4];
		shortDisplay[0] = "";
		shortDisplay[1] = "";
		shortDisplay[2] = super.getTitle();
		shortDisplay[3] = getMeetingString();		
		return shortDisplay;
	}

	/**
	 *  Returns event description in the form of long String array with first two, fourth
	 *  and fifth elements empty. 
	 *  @return longDisplay String array that is returned.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = "";
		longDisplay[1] = "";
		longDisplay[2] = super.getTitle();
		longDisplay[3] = "";
		longDisplay[4] = "";
		longDisplay[5] = getMeetingString();
		longDisplay[6] = getEventDetails();
		return longDisplay;
	}

	/** 
	 * Returns string representation of meeting days, start time, end time and 
	 * how often it repeats.
	 */
	@Override
	public String getMeetingString() {
		return super.getMeetingString() + " (every " + getWeeklyRepeat() + " weeks)";
	}

	/**
	 * Returns string representation of meeting days, start time, end time of an event, 
	 * how often it repeats and event details.
	 */
	@Override
	public String toString() {
		return super.toString() + "," + getWeeklyRepeat() + "," + getEventDetails();
	}
	
	/**
	 * Overrides isDuplicate abstract method from the superclass, 
	 * returns true if the passed activity (instance of Event) is the duplicate of the one already
	 * in the list, false otherwise.
	 * @return boolean true if passed activity is a duplicate the current activity.
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Event) {
			Event e = (Event) activity;
			if(e.getTitle().equals(this.getTitle())) {
				return true;
			}
		}
		return false;
	}

}
