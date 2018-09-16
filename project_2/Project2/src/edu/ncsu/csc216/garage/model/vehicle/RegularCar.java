package edu.ncsu.csc216.garage.model.vehicle;

import edu.ncsu.csc216.garage.model.service_garage.BayCarMismatchException;
import edu.ncsu.csc216.garage.model.service_garage.BayOccupiedException;
import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;
import edu.ncsu.csc216.garage.model.service_garage.ServiceBay;

/**
 * Concrete class representing a vehicle that cannot be serviced in
 * HybridElectricBays
 * 
 * @author someshherath
 *
 */
public class RegularCar extends Vehicle {

	/**
	 * RegularCar constructor, which constructs RegularCar out of those three
	 * fields and initializes all those fields.
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
	public RegularCar(String license, String name, int tierIndex) throws BadVehicleInformationException {
		super(license, name, tierIndex);
	}

	/**
	 * This is an method where vehicle picks a service bay from the front of the
	 * list by looking for empty one.
	 * 
	 * @param garage
	 *            garage that holds all the service bays.
	 * @throws NoAvailableBayException
	 *             if the vehicle chooses the wrong bay.
	 */
	@Override
	public void pickServiceBay(Garage garage) throws NoAvailableBayException {
		int i = 0;
		for (; i < garage.getSize(); i++) {
			ServiceBay sb = garage.getBayAt(i);
			try {
				sb.occupy(this);
				break;
			} catch (BayOccupiedException e) {
				// skip this line.
			} catch (BayCarMismatchException e) {
				throw new NoAvailableBayException();
			}
		}
		if (i == garage.getSize()) {
			throw new NoAvailableBayException();
		}
	}

	/**
	 * Returns string representation of the Regular car.
	 * 
	 * @return string representation of a vehicle
	 */
	@Override
	public String toString() {
		return "R " + super.toString();
	}

}
