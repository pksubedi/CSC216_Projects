package edu.ncsu.csc216.garage.model.vehicle;

/**
 * provides methods for behavior of tiered classes
 * 
 * @author someshherath
 *
 */
public interface Tiered {
	/**
	 * Returns tier of the vehicle
	 * 
	 * @return tier of the vehicle
	 */
	int getTier();

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
	int compareToTier(Tiered vehicle);

}
