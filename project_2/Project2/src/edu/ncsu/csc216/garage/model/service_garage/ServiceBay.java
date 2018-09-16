package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Concrete class representing a service bay in the garage. Service bays of this
 * type are considered "regular," and can handle both regular and
 * hybrid/electric vehicles.
 * 
 * @author Prem Subedi
 *
 */
public class ServiceBay {

	/** boolean variable for whether the bay is occupied or not */
	private boolean occupied;

	/** Bay id for a service bay */
	private String bayID;

	/** Number for the next bay to be opened */
	private static int nextNumber = 1;

	/** vehicle instance */
	private Vehicle myVehicle;

	/**
	 * Creates a new empty service bay according to the current bay numbering,
	 * then increments that number.
	 */
	public ServiceBay() {
		this(null);
	}

	/**
	 * Service bay constructor which is same as null constructor but the prefix
	 * is the first non-whitespace character in the argument or "1" if there is
	 * no such character.
	 * 
	 * @param prefix
	 *            first non-whitespace character.
	 */
	public ServiceBay(String prefix) {
		if (prefix == null || prefix.isEmpty()) {

			if (nextNumber < 10) {
				this.bayID = "1" + "0" + nextNumber;
			} else {
				this.bayID = "1" + nextNumber;
			}

		} else {
			String s = prefix.trim();
			if (s.isEmpty()) {
				if (nextNumber < 10) {
					this.bayID = "1" + "0" + nextNumber;
				} else {
					this.bayID = "1" + nextNumber;
				}
			} else {
				if (nextNumber < 10) {
					this.bayID = s.charAt(0) + "0" + nextNumber;
				} else {
					this.bayID = s.charAt(0) + Integer.toString(nextNumber);
				}
			}
		}
		nextNumber++;
		occupied = false;

	}

	/**
	 * Static method. Resets the service bay numbering to start from 1 (so the
	 * next 3 bays added for this project would have numbers/IDs E01, 102, and
	 * 103). This is a convenience mostly for JUnit testing, but it can
	 * optionally be called by the Garage constructor.
	 */
	public static void startBayNumberingAt101() {
		nextNumber = 1;
	}

	/**
	 * returns the bayID
	 * 
	 * @return the bayID
	 */
	public String getBayID() {
		return bayID;
	}

	/**
	 * returns occupied
	 * 
	 * @return occupied
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * returns occupied
	 * 
	 * @return occupied
	 */
	public Vehicle release() {
		Vehicle temp = myVehicle;
		myVehicle = null;
		occupied = false;
		return temp;

	}

	/**
	 * Occupies the service bay with the given vehicle. Throws a
	 * BayOccupiedException if the service bay is already occupied.
	 * 
	 * @param vehicle
	 *            the given vehicle
	 * @throws BayOccupiedException
	 *             if bay is occupied
	 * @throws BayCarMismatchException
	 *             is car is mismatched with the bay
	 */
	public void occupy(Vehicle vehicle) throws BayOccupiedException, BayCarMismatchException {
		if (isOccupied()) {
			throw new BayOccupiedException();
		} else {
			myVehicle = vehicle;
			occupied = true;
		}

	}

	/**
	 * turns the bay to a printable format
	 * 
	 * @return the bay in a printable format
	 */
	public String toString() {
		String s = bayID + ": ";
		if (isOccupied()) {
			s += myVehicle.getLicense();
			for (int i = 0; i < 9 - myVehicle.getLicense().length(); i++) {
				s += " ";
			}
			s += myVehicle.getName();
			return s;

		} else {
			return s + "EMPTY";
		}
	}

}