package edu.ncsu.csc216.garage.model.service_garage;

import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;

/**
 * Concrete class that extends ServiceBay and represents a service bay that can
 * accommodate hybrid/electric vehicles only.
 * 
 * @author someshherath, Prem Subedi.
 *
 */
public class HybridElectricBay extends ServiceBay {

	/**
	 * Creates a new empty bay for servicing hybrid/electric vehicles according
	 * to the current bay numbering, then increments that number. The prefix is
	 * "E".
	 */
	public HybridElectricBay() {
		super("E");
	}

	/**
	 * Occupies the service bay with the given vehicle. Throws a
	 * @throws BayCarMismatchException if the vehicle is not a hybrid/electric car.
	 * @throws BayOccupiedException if the bay is occupied.
	 * @param vehicle
	 *            the given vehicle
	 */
	public void occupy(Vehicle vehicle) throws BayCarMismatchException, BayOccupiedException {
		if(vehicle instanceof RegularCar) {
			throw new BayCarMismatchException();
		} else {
			super.occupy(vehicle);
		}

	}

}
