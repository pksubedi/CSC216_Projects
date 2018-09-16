package edu.ncsu.csc216.garage.model.util;

/**
 * Interface describing behaviors of a generic type of iterator with two
 * methods: next() and hasNext(). This is identical to the Java API Iterator
 * type except it does not declare a remove() method.
 * 
 * @author someshherath
 *
 * @param <E> element
 */
public interface SimpleIterator<E> {

	/**
	 * returns true if there's a next
	 * 
	 * @return true if there's a next
	 */
	boolean hasNext();

	/**
	 * returns the next
	 * 
	 * @return the next
	 */
	E next();
}
