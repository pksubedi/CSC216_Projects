package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Holds information about a garage that holds service bays 
 * by using custom array based list.
 * 
 * @author someshherath, Prem Subedi.
 */
public class Garage {


	/** Maximum number of service bays that a garage can hold */
	private static final int MAX_BAYS = 30;

	/** default size of a garage */
	private static final int DEFAULT_SIZE = 8;

	/** size of the bay list */
	private int size;

	/** Array of serviceBays */
	private ServiceBay[] bay;

	/**
	 * Creates a list of 8 empty service bays
	 */
	public Garage() {
		ServiceBay.startBayNumberingAt101();
		bay = new ServiceBay[MAX_BAYS];
		size = 0;
		initBays(DEFAULT_SIZE);
	}

	/**
	 * Initializes bays
	 * 
	 * @param bayNum
	 *            the bay number
	 */
	private void initBays(int bayNum) {

		for(int i = 0; i < bayNum; i++) {
			if(i % 3 == 0) {
				bay[i] = new HybridElectricBay();
			} else { //add to beginning
				//shift everything in the list to the right
				//bay[0] = new ServiceBay();
				for (int j = size - 1; j >= 0; j--) {
					bay[j + 1] = bay[j];
				}
				bay[0] = new ServiceBay(); 
			}
			size++;
		}
		size = bayNum;

	}

	/**
	 * Adds a repair bay. At least 1/3 of the bays in the garage are dedicated
	 * to hybrid/electric vehicles.
	 */
	public void addRepairBay() {
		if(size < MAX_BAYS) {
			if(size % 3 == 0) {
				bay[size] = new HybridElectricBay();
			} else {
				for (int j = size - 1; j >= 0; j--) {
					bay[j + 1] = bay[j];
				}
				bay[0] = new ServiceBay(); 
			}
			size++;
		}
	}

	/**
	 * Number of open service bays that are currently empty.
	 * 
	 * @return the number of open service bays that are currently empty.
	 */
	public int numberOfEmptyBays() {
		int counter = 0;
		for(int i = 0; i < size; i++) {
			if(!bay[i].isOccupied()) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * returns the bay at a index
	 * 
	 * @param idx
	 *            the index to look for
	 * @return the bay at a index
	 */
	public ServiceBay getBayAt(int idx) {
		if(idx >= 0 || idx < size) {
			return bay[idx];
		}
		return null;
	}

	/**
	 * Returns the number of bays
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Releases the vehicle currently being serviced at a certain bay.
	 * 
	 * @param idx
	 *            index of the bay
	 * @return vehicle released
	 */
	public Vehicle release(int idx) {
		if(idx < size) {
			return bay[idx].release();
		}
		return null;
	}
}
