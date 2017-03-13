package edu.wmich.cs3310.hw2.Gray.trees;

import java.util.ArrayList;
import java.util.Collections;

public class CompleteBinaryTree implements ITree {
	
	ArrayList<Integer[]> tree;
	
	public CompleteBinaryTree(){
		tree = new ArrayList<Integer[]>();
	}
	
	@Override
	public Integer[] findMin() {
		if(tree.isEmpty()){
			return null;
		}
		else{
			return tree.get(0);
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
	
	public void topHeapify(int i){
		System.out.println(this);
		int swap;
		int left = i*2+1;
		int right = i*2+2;
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
	
	private int getSmallerChildIndex(int i) {
		int left = 2*i+1;
		int right = 2*i+2;
		Integer indexVal = tree.get(i)[0];
		Integer leftVal;
		Integer rightVal;
		
		if(left>tree.size()-1){
			leftVal = -1;
		}
		else{
			leftVal = tree.get(left)[0];
		}
		
		if(right>tree.size()-1){
			rightVal = -1;
		}
		else{
			rightVal = tree.get(right)[0];
		}
		
		if(left>tree.size()-1){
			if(indexVal.compareTo(rightVal)>0)
				return right;
		}
		else if (right>tree.size()-1){
			if(indexVal.compareTo(leftVal)>0)
				return left;
		}
		else{
			if(leftVal.compareTo(rightVal)<=0&&indexVal.compareTo(leftVal)>0){
				return left;
			}
			else if(rightVal.compareTo(leftVal)<=0&&indexVal.compareTo(leftVal)>0){
				return right;
			}
			else{
				return -1;
			}
		}
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

	private void bottomHeapify(int i) {
		int parent = (i-1)/2;
		if(i==0){
			return;
		}
		else{
			if(tree.get(i)[0].compareTo(tree.get(parent)[0])<0){
				Collections.swap(tree, parent, i);
				bottomHeapify(parent);
			}
			return;
		}
		
	}

	public String toString(){
		String output = "{";
		for(Integer[] x: tree){
			output += "<"+x[0]+","+x[1]+">,";
		}
		output = output.substring(0, output.length()-1);
		output += "}";
		return output;
	}
}
