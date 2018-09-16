package edu.ncsu.csc216.garage.model.vehicle;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;

/**
 * Vehicle class which is abstract class and represents any vehicle in the
 * waiting list to get serviced.
 * 
 * @author premsubedi
 */
public abstract class Vehicle implements Tiered {
	/** Vehicle's license */
	private String license;
	/** Owner's name */
	private String name;

	/** Vehicle tier type */
	private int tierIndex;

	/** Array of vehicle tier type in the form of string */
	public static final String[] CUSTOMER_TIER = {"None", "Silver", "Gold", "Platinum" };

	/**
	 * Vehicle constructor, which constructs vehicle out of those three fields
	 * and initializes all those fields.
	 * 
	 * @param license
	 *            Vehicle's license.
	 * @param name
	 *            vehicle's owner name.
	 * @param tierIndex
	 *            tier type in integer.
	 * @throws BadVehicleInformationException
	 *             if the owner name and license are invalid.
	 */
	public Vehicle(String license, String name, int tierIndex) throws BadVehicleInformationException {
		this.setLicense(license);
		this.setName(name);
		this.setTier(tierIndex);
	}

	/**
	 * This is an abstract method where vehicle picks a service bay based on
	 * their status and type.
	 * 
	 * @param garage
	 *            garage that holds all the service bays.
	 * @throws NoAvailableBayException
	 *             if the vehicle chooses the wrong bay.
	 */
	public abstract void pickServiceBay(Garage garage) throws NoAvailableBayException;

	/**
	 * Returns true if filter is a prefix to the owner's last name. The check is
	 * case insensitive.
	 * 
	 * @param filter
	 *            a prefix of the last name of the vehicle owner.
	 * @return boolean based on the filter passed.
	 */
	public boolean meetsFilter(String filter) {
		if (filter == null || filter.equals("")) {
			return true;
		} else {
			filter = filter.toUpperCase().trim();
			if (name.toUpperCase().trim().startsWith(filter)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns string representation of the vehicle.
	 * 
	 * @return string representation of a vehicle
	 */
	public String toString() {
		String s = CUSTOMER_TIER[tierIndex];
		for (int i = 0; i < (10 - CUSTOMER_TIER[tierIndex].length()); i++) {
			s += " ";
		}
		s += license;
		for (int i = 0; i < (10 - license.length()); i++) {
			s += " ";
		}
		s += name.trim();
		return s;
	}

	/**
	 * Returns vehicle owner name.
	 * 
	 * @return name name of the vehicle owner.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns vehicle's license.
	 * 
	 * @return license license plate number of the vehicle.
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * Returns tier type of the vehicle in the form of integer value.
	 * 
	 * @return tierIndex tier type of the vehicle.
	 */
	public int getTier() {
		return tierIndex;
	}

	/**
	 * Compare the tier status of this object with another. Returns 0 if the two
	 * match, a negative number if the tier status of this object is less than
	 * the other's, a positive number if the tier status of this object is
	 * greater.
	 * 
	 * @param vehicle
	 *            vehicle to be compared.
	 * @return integer value 1, 0 or -1.
	 */
	public int compareToTier(Tiered vehicle) {
		if(vehicle == null) {
			throw new NullPointerException();
		}
		if (this.getTier() < vehicle.getTier()) {
			return -1;
		} else if (this.getTier() > vehicle.getTier()) {
			return 1;
		} else {
			return 0;
		}
		

	}

	/**
	 * sets the tier
	 * 
	 * @param tier
	 *            the tier to set
	 * @throws BadVehicleInformationException
	 *             if the tier is invalid
	 */
	public void setTier(int tier) throws BadVehicleInformationException {
		if (tier < 0 || tier > 3) {
			throw new BadVehicleInformationException("Invalid tier.");
		}
		tierIndex = tier;
	}

	/**
	 * sets the name
	 * 
	 * @param name
	 *            the name too set
	 * @throws BadVehicleInformationException if the vehicle information is invalid.
	 */

	private void setName(String name) throws BadVehicleInformationException {
		if(name != null) {
			if (name.trim().equals("")) {
				throw new BadVehicleInformationException("Owner name cannot be blank.");
			}
			this.name = name.trim();
		} else {
			throw new BadVehicleInformationException("Owner name cannot be null.");
		}
	}

	/**
	 * sets the license
	 * 
	 * @param license
	 *            the license to set
	 * @throws BadVehicleInformationException if the vehicle information is invalid.
	 */

	private void setLicense(String license) throws BadVehicleInformationException {
		if(license != null) {
			if (license.trim().equals("") || license.trim().contains(" ")) {
				throw new BadVehicleInformationException("License cannot be blank.");
			} else if (license.trim().length() > 8) {
				throw new BadVehicleInformationException("License cannot be more than 8 characters.");
			}
			this.license = license.trim();
		} else {
			throw new BadVehicleInformationException("License cannot be null.");
		}
	}

}
