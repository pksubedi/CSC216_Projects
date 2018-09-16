package edu.ncsu.csc216.garage.model.vehicle;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.garage.model.util.SimpleIterator;

/**
 * Maintains its vehicles in a linked list.
 * 
 * @author someshherath
 *
 */
public class VehicleList {

	/** the head node similar to the front */
	private Node head;

	/**
	 * Creates an empty list of vehicles.
	 */
	public VehicleList() {
		head = null;
	}

	/**
	 * Creates a list of vehicles from a Scanner. The Scanner would have been
	 * initialized by the calling code as a Scanner on an input text file.
	 * 
	 * @param scan
	 *            the scanner used in construction
	 */
	public VehicleList(Scanner scan) {

		while (scan.hasNext()) {

			try {
				String type = scan.next();
				int tier = scan.nextInt();
				String license = scan.next();
				String name = scan.nextLine();

				if (type.equalsIgnoreCase("E")) {
					this.add(new HybridElectricCar(license, name, Integer.valueOf(tier)));

				} else if (type.equalsIgnoreCase("R")) {
					this.add(new RegularCar(license, name, Integer.valueOf(tier)));
				}

			} catch (BadVehicleInformationException e) {
				// skip
			} catch (InputMismatchException e) {
				// skip
			}
		}

		scan.close();
	}

	/**
	 * Returns a SimpleIterator initialized to point to the first element in the
	 * list
	 * 
	 * @return a SimpleIterator initialized to point to the first element in the
	 *         list
	 */
	public SimpleIterator<Vehicle> iterator() {
		return new Cursor();
	}

	/**
	 * Removes the vehicle that appears in the filtered list in the given
	 * position.
	 * 
	 * @param filter
	 *            prefix filter which is the first initial of the vehicle owner.
	 * @param position
	 *            tier of the vehicle to remove
	 * @return the vehicle that appears in the filtered list in the given
	 *         position
	 */
	public Vehicle remove(String filter, int position) {
		Vehicle vehicle = null;

		Node traveler = head;
		Node previous = null;

		int count = 0;

		while (traveler != null) {
			if (traveler.vehicle.meetsFilter(filter)) {
				if (count == position) {
					vehicle = traveler.vehicle;

					if (traveler == head) {
						head = traveler.next;
					} else if (traveler.next == null) {
						previous.next = null;
					} else {
						previous.next = traveler.next;
					}

					return vehicle;
				}
				count++;
			}

			previous = traveler;
			traveler = traveler.next;
		}

		return null;

	}

	/**
	 * Gets the vehicle that appears in the filtered list in the given position.
	 * 
	 * @param filter
	 *            filter for filtering the list.
	 * @param position
	 *            position of the vehicle in the filteredList.
	 * @return the vehicle that appears in the filtered list in the given
	 *         position
	 */
	public Vehicle get(String filter, int position) {
		Node node = null;
		try {
			node = findTrailingNode(filter, position);
		} catch (NoSuchElementException e) {
			return null;
		}
		if (node == null) {
			return head.vehicle;
		}
		return node.next.vehicle;
	}

	private Node findTrailingNode(String filter, int position) {
		if (head == null) {
			throw new NoSuchElementException("Empty list");
		}
		if (position < 0) {
			throw new NoSuchElementException("Illegal position.");
		}
		Node previous = null;
		Node current = head;
		int sizeCount = -1;
		while (current != null && sizeCount < position) {
			if (current.vehicle.meetsFilter(filter)) {
				sizeCount++;
			}
			if (sizeCount < position) {
				previous = current;
				current = current.next;
			}
		}
		if (sizeCount == position) {
			return previous;
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Adds the given vehicle to the list of those awaiting service.
	 * 
	 * @param v
	 *            the vehicle to add
	 */
	public void add(Vehicle v) {

		Node current = head;
		Node previous = null;

		if (head == null) {

			head = new Node(v, null);
			return;

		} else if (isDuplicate(v)) {
			return;

		} else {
			while (current.next != null) {

				if (current.vehicle.compareToTier(v) < 0) {

					Node node = new Node(v, current);
					if (previous != null) {
						previous.next = node;
					} else {
						head = node;
					}

					return;
				}

				previous = current;
				current = current.next;
			}

			if (current.next == null && previous == null) {
				if (current.vehicle.compareToTier(v) < 0) {
					Node node = new Node(v, current);
					head = node;
					return;
				} else {
					Node node = new Node(v, null);
					current.next = node;
					return;
				}
			}

			if (current.vehicle.compareToTier(v) < 0) {
				Node node = new Node(v, current);
				if (previous != null) {
					previous.next = node;
				} else {
					head = node;
				}

				return;
			}

			Node node = new Node(v, null);
			current.next = node;

		}
	}
	
	/**
     * true if vehicle is already in the list
     * 
     * @param vehicle
     *            vehicle to check for
     */
    private Boolean isDuplicate(Vehicle vehicle) {
        if (vehicle == null) {
            return true;
        }
        SimpleIterator<Vehicle> iter = this.iterator();

        while (iter.hasNext()) {

            if (vehicle.equals(iter.next())) {
                return true;
            }
        }

        return false;

    }

	/**
	 * String representation of all vehicles that meet the filter. Each
	 * substring corresponding to a vehicle is terminated by a newline.
	 * 
	 * @param filter
	 *            filter or first initial of the owner's name.
	 * @return representation of all vehicles that meet the filter
	 */
	public String filteredList(String filter) {
		String vehicleData = "";
        Node temp = head;
        while (temp != null) {
            if (temp.vehicle.meetsFilter(filter)) {
                vehicleData += temp.vehicle.toString() + "\n";
            }
            temp = temp.next;
        }

        return vehicleData;
	}

	/**
	 * Inner class of VehicleList, which represents a single node object.
	 * 
	 * @author Prem Subedi
	 *
	 */
	public class Node {
		/** next node */
		public Node next;

		/** vehicle */
		public Vehicle vehicle;

		/**
		 * constructs a node
		 * 
		 * @param v
		 *            data
		 * @param next
		 *            the next node
		 */
		public Node(Vehicle v, Node next) {
			this.next = next;
			this.vehicle = v;
		}
	}

	/**
	 * The current location of the iterator within the list.
	 * 
	 * @author Somesh Herath
	 * @author Prem Subedi
	 */
	public class Cursor implements SimpleIterator<Vehicle> {
		/**
		 * the current location of the iterator within the list
		 */
		public Node cursor;

		/**
		 * constructs Cursor
		 */
		private Cursor() {
			this.cursor = head;
		}

		/**
		 * Returns true if there's a next non - null node.
		 * 
		 * @return true if there's a next
		 */
		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		/**
		 * returns the next
		 * 
		 * @return the next
		 */
		@Override
		public Vehicle next() {
			Vehicle v = null;
			if (cursor == null) {
				throw new NoSuchElementException();
			}

			v = cursor.vehicle;
			cursor = cursor.next;
			return v;
		}

	}

}
