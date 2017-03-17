package edu.wmich.cs3310.hw2.Gray.node;

/**
 * This class contains the structure
 * necessary to set up and use the explicit
 * form of a size balanced binary tree.
 * @author john
 *
 */
public class Node {
	public Node left, right, parent;
	public int key, data;
	public int size;
	
	/**
	 * Default constructor, completely null.
	 */
	public Node(){
		left = null;
		right = null;
		parent = null;
		key = 0;
		data = 0;
		size = 0;
	}
	
	/**
	 * Overloaded constructor to save time when creating a new node.
	 * @param key - the new node's key
	 * @param data - the new node's data
	 */
	public Node(int key, int data){
		left = null;
		right = null;
		parent = null;
		this.key = key;
		this.data = data;
		size = 1;
	}
	
	/**
	 * Used in traversing and inserting/deleting
	 * into the tree. Checks if this specific node
	 * being called is a leaf.
	 * @return a boolean answering if it is a leaf
	 */
	public boolean isLeaf() {
		if(left==null&&right==null)
			return true;
		else
			return false;
	}
	
	/**
	 * Used in traversing and inserting/deleting 
	 * into the tree. Checks if this specific node
	 * has an empty child space.
	 * @return a boolean answering if it has any empty children
	 */
	public boolean hasEmptyChildSpace() {
		if(left==null||right==null){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Swaps the current node with a node
	 * sent through parameters.
	 * Used in deleting/inserting + heapifying.
	 * @param current - The node to be swapped with.
	 */
	public void swap(Node current) {
		int key, data;
		key = this.key;
		data = this.data;
		this.key = current.key;
		this.data = current.data;
		current.key = key;
		current.data = data;		
	}
}
