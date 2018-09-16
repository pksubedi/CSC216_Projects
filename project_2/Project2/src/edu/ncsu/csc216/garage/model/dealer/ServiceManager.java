package edu.ncsu.csc216.garage.model.dealer;

import java.util.Scanner;

import edu.ncsu.csc216.garage.model.service_garage.Garage;
import edu.ncsu.csc216.garage.model.service_garage.NoAvailableBayException;
import edu.ncsu.csc216.garage.model.service_garage.ServiceBay;
import edu.ncsu.csc216.garage.model.util.SimpleIterator;
import edu.ncsu.csc216.garage.model.vehicle.BadVehicleInformationException;
import edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar;
import edu.ncsu.csc216.garage.model.vehicle.RegularCar;
import edu.ncsu.csc216.garage.model.vehicle.Tiered;
import edu.ncsu.csc216.garage.model.vehicle.Vehicle;
import edu.ncsu.csc216.garage.model.vehicle.VehicleList;

/**
 * Concrete class that implements Manageable.
 * 
 * @author Prem Subedi
 * @author someshherath
 *
 */
public class ServiceManager implements Manageable {

	/** waiting cars */
	private VehicleList waitingCars;

	/** my Garage */
	private Garage myGarage;

	/**
	 * Creates a service manager with no vehicles awaiting service
	 */
	public ServiceManager() {
		this(null);
	}

	/**
	 * Initializes the list of vehicles awaiting service with data from the
	 * Scanner
	 * 
	 * @param scanner
	 *            scanner used in ServiceManager
	 */
	public ServiceManager(Scanner scanner) {
		ServiceBay.startBayNumberingAt101();
		myGarage = new Garage();

		if (scanner == null) {
			waitingCars = new VehicleList();
		} else {
			waitingCars = new VehicleList(scanner);
		}
	}

	/**
	 * 
	 * Puts a vehicle on the list of vehicles awaiting service
	 * 
	 * @param type
	 *            determines regular or hybrid/electric
	 * @param license
	 *            the vehicle's license
	 * @param name
	 *            owner name
	 * @param tierStatus
	 *            the tier status
	 * 
	 */
	@Override
	public void putOnWaitingList(String type, String license, String name, int tierStatus) {
		if (type.equalsIgnoreCase("E")) {
			try {
				Vehicle heVehicle = new HybridElectricCar(license, name, tierStatus);
				waitingCars.add(heVehicle);
			} catch (BadVehicleInformationException e) {
				// skip
			}

		} else if (type.equalsIgnoreCase("R")) {
			try {
				Vehicle rCar = new RegularCar(license, name, tierStatus);
				waitingCars.add(rCar);
			} catch (BadVehicleInformationException e) {
				// skip
			}

		}
	}

	/**
	 * Puts an item in the list of those awaiting service.
	 * 
	 * @param vehicle
	 *            The item to put on waiting list
	 */
	@Override
	public void putOnWaitingList(Tiered vehicle) {
		waitingCars.add((Vehicle) vehicle);
	}

	/**
	 * Returns the Tiered item meeting the given filter and position from the
	 * list of items awaiting service.
	 * 
	 * @param filter
	 *            Filters the list of items considered
	 * @param position
	 *            Position in the filtered list of items
	 * @return The item at the position in the list that meets the given filter
	 */
	@Override
	public Tiered getWaitingItem(String filter, int position) {
		String car = waitingCars.filteredList(filter);
		String[] cars = car.split("\n");

		if (position < 0 || position > cars.length) {
			return null;
		}

		return waitingCars.get(filter, position);

	}

	/**
	 * Removes an item meeting the given filter from the list of items awaiting
	 * service.
	 * 
	 * @param filter
	 *            Filters the list of items considered for removal
	 * @param position
	 *            Position in the filtered list of the item to be removed
	 * @return The item that was removed, or null if nothing was removed
	 */
	@Override
	public Tiered remove(String filter, int position) {
		return waitingCars.remove(filter, position);
	}

	/**
	 * Fills the service bays with items awaiting service.
	 */
	@Override
	public void fillServiceBays() {
		try {
			SimpleIterator<Vehicle> iterator = waitingCars.iterator();
			Vehicle v;

			while (myGarage.numberOfEmptyBays() > 0) {
				if (iterator.hasNext()) {
					v = iterator.next();
					v.pickServiceBay(myGarage);
					waitingCars.remove(v.getName(), 0);
				} else {
					break;
				}
			}
		} catch (NoAvailableBayException e) {
			// skip
		}
		try {
			SimpleIterator<Vehicle> iterator = waitingCars.iterator();
			Vehicle v;

			while (myGarage.numberOfEmptyBays() > 0) {
				if (iterator.hasNext()) {
					v = iterator.next();

					if (v.getClass().getName().equals("edu.ncsu.csc216.garage.model.vehicle.HybridElectricCar")) {
						v.pickServiceBay(myGarage);
						waitingCars.remove(v.getName(), 0);
					}

				} else {
					break;
				}
			}

		} catch (NoAvailableBayException e) {
			// skip
		}

	}

	/**
	 * Returns garage inside the serviceManager.
	 * 
	 * @return myGarage garage of the ServiceManager.
	 */
	public Garage getGarage() {
		return myGarage;
	}

	/**
	 * Returns VehicleList inside the ServiceManager.
	 * 
	 * @return waitingCars waiting list of vehicles.
	 */
	public VehicleList getWaitingList() {
		return waitingCars;
	}

	/**
	 * Releases the item from the given service bay.
	 * 
	 * @param idx
	 *            Location of the bay where the item is being serviced
	 * @return Item that was released from service, or null if the bay was empty
	 */
	@Override
	public Tiered releaseFromService(int idx) {
		return myGarage.release(idx);
	}

	/**
	 * Adds a new service bay to the service area.
	 */
	@Override
	public void addNewBay() {
		myGarage.addRepairBay();
	}

	/**
	 * A string representation of the list of items awaiting service that meet
	 * the given filter.
	 * 
	 * @param filter
	 *            Determines which items are are of interest
	 * @return String String representation of the filtered list
	 */
	@Override
	public String printWaitList(String filter) {
		return waitingCars.filteredList(filter);
	}

	/**
	 * A string representation of the list of bays in the service area.
	 * 
	 * @return String representation of the service area
	 */
	@Override
	public String printServiceBays() {
		String bays = "";
		for (int i = 0; i < myGarage.getSize(); i++) {
			bays += myGarage.getBayAt(i).toString() + "\n";
		}
		return bays;
	}

}
