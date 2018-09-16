package edu.ncsu.csc216.garage.model.vehicle;

import edu.ncsu.csc216.garage.model.service_garage.BayCarMismatchException;
import edu.ncsu.csc216.garage.model.service_garage.BayOccupiedException;
import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;
import edu.ncsu.csc216.garage.model.service_garage.ServiceBay;

/**
 * Concrete class representing a vehicle that can be serviced in
 * HybridElectricBays.
 * 
 * @author someshherath
 *
 */
public class HybridElectricCar extends Vehicle {

	/**
	 * HybridElectricCar constructor, which constructs HybridElectricCar out of
	 * those three fields and initializes all those fields.
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
	public HybridElectricCar(String license, String name, int tierIndex) throws BadVehicleInformationException {
		super(license, name, tierIndex);
	}

	/**
	 * This is an abstract method where vehicle picks a service bay from the end
	 * of the list.
	 * 
	 * @param garage
	 *            garage that holds all the service bays.
	 * @throws NoAvailableBayException
	 *             if the vehicle chooses the wrong bay.
	 */
	@Override
	public void pickServiceBay(Garage garage) throws NoAvailableBayException {
		int i = garage.getSize() - 1;
		for (; i >= 0; i--) {
			ServiceBay sb = garage.getBayAt(i);
			try {
				sb.occupy(this);
				break;
			} catch (BayOccupiedException e) {
				// skip this line.
			} catch (BayCarMismatchException e) {
				// skip this line.
			}
		}
		if (i == -1) {
			throw new NoAvailableBayException();
		}
	}

	/**
	 * Returns string representation of the Hybride elecritc car.
	 * 
	 * @return string representation of a vehicle
	 */
	@Override
	public String toString() {
		return "E " + super.toString();
	}

}
