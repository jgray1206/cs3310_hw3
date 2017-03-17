package edu.wmich.cs3310.hw2.Gray.trees;

/**
 * This interface ensures that each
 * tree has the assignment's required
 * functionality
 * @author john
 *
 */
public interface ITree {
	
	/**
	 * This operation will return the
	 * smallest number in the heap,
	 * which should be the root.
	 * @return smallest number in heap, as a String
	 */
	public String findMin();
	
	/**
	 * This operation will delete the
	 * top of the heap, then heapify
	 * to retain its order.
	 */
	public void deleteMin();
	
	/**
	 * This operation inserts a key value pair
	 * into the heap, then heapifies to maintain
	 * the proper relationship.
	 * @param key New node key
	 * @param value New node value
	 */
	public void insert(int key, int value);
}	
