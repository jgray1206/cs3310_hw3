package edu.wmich.cs3310.hw2.Gray.trees;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This is the implementation of a complete binary tree.
 * It utilizes my ITree interface for its basic functionality,
 * along with Java's ArrayList, so I don't have to worry
 * about resizing.
 * Javadocs for overriden ITree methods are in ITree.
 * @author john
 */
public class CompleteBinaryTree implements ITree {
	
	ArrayList<Integer[]> tree; 				//holds my complete binary tree
	
	/**
	 * Basic constructor sets up ArrayList tree.
	 */
	public CompleteBinaryTree(){
		tree = new ArrayList<Integer[]>();
	}
	
	@Override
	public String findMin() {
		if(tree.isEmpty()){
			return null;
		}
		else{
			return "<"+tree.get(0)[0]+","+tree.get(0)[1]+">";
		}
	}

	@Override
	public void deleteMin() {
		if(tree.isEmpty()){
			return;
		}
		else{
			Collections.swap(tree, 0, tree.size()-1);
			tree.remove(tree.size()-1);
			topHeapify(0);
		}
	}
	
	/**
	 * Utilized when deleting.
	 * Top heapifies via recursion.
	 * @param i - keeps track of index in array
	 */
	public void topHeapify(int i){
		int swap;
		int left = (i*2)+1;
		int right = (i*2)+2;
		//if both children are null, heapify is done.
		if(left>tree.size()-1&&right>tree.size()-1){
			return;
		}
		else{
			//checks what child is smaller, then swaps if necessary.
			swap = getSmallerChildIndex(i);
			if(swap==-1)
				return;
			else{
				Collections.swap(tree, swap, i);
				topHeapify(swap);
			}
		}
	}
	
	/**
	 * returns the index of the smallest child of 
	 * index i. Utilized in top heapify.
	 * @param i - parent index. checking for smallest child of this.
	 * @return smallest child index
	 */
	private int getSmallerChildIndex(int i) {
		int left = (2*i)+1;
		int right = (2*i)+2;
		Integer indexVal = tree.get(i)[0];
		Integer leftVal;
		Integer rightVal;
		
		//checks if left exists, then gets its key
		if(left>tree.size()-1){
			leftVal = -1;
		}
		else{
			leftVal = tree.get(left)[0];
		}
		
		//checks if right exists, then gets its key
		if(right>tree.size()-1){
			rightVal = -1;
		}
		else{
			rightVal = tree.get(right)[0];
		}
		
		//if no left, check right
		if(left>tree.size()-1){
			if(indexVal.compareTo(rightVal)>0)
				return right;
		} //if no right, check left
		else if (right>tree.size()-1){
			if(indexVal.compareTo(leftVal)>0)
				return left;
		}
		//if there is both children, check which one is smallest, and if it is less than parent, return index
		else{ 
			if(leftVal.compareTo(rightVal)<=0&&indexVal.compareTo(leftVal)>0){
				return left;
			}
			else if(rightVal.compareTo(leftVal)<=0&&indexVal.compareTo(rightVal)>0){
				return right;
			}
			else{
				return -1;
			}
		}
		//if no child is smaller, return -1
		return -1;
	}

	@Override
	public void insert(int key, int value) {
		Integer[] pair = new Integer[2];
		pair[0] = key;
		pair[1] = value;
		
		tree.add(pair);
		bottomHeapify(tree.size()-1);
	}

	/**
	 * Heapifies from the bottom, starting at
	 * index i. Utilized in insert method.
	 * @param i - Recursive starting point
	 */
	private void bottomHeapify(int i) {
		int parent = (i-1)/2;
		if(i==0){
			return;
		}
		else{
			//compare i with its parent. Swap if less.
			if(tree.get(i)[0].compareTo(tree.get(parent)[0])<0){
				Collections.swap(tree, parent, i);
				bottomHeapify(parent);
			}
			return;
		}
		
	}

	/**
	 * Used to print out contents of the CBT
	 */
	public void print(){
		String output = "CBT: {";
		for(Integer[] x: tree){
			output += "<"+x[0]+","+x[1]+">,";
		}
		output = output.substring(0, output.length()-1);
		output += "}";
		System.out.println(output);
	}
	
	/**
	 * Getter for size of tree
	 * @return size of tree
	 */
	public int size(){
		return tree.size();
	}
}
