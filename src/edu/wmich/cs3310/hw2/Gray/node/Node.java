package edu.wmich.cs3310.hw2.Gray.node;

public class Node {
	public Node left, right, parent;
	public int key, data;
	public int size;
	
	public Node(){
		left = null;
		right = null;
		parent = null;
		key = 0;
		data = 0;
		size = 0;
	}
	
	public Node(int key, int data){
		left = null;
		right = null;
		parent = null;
		this.key = key;
		this.data = data;
		size = 1;
	}
	
	public boolean isLeaf() {
		if(left==null&&right==null)
			return true;
		else
			return false;
	}
	
	public boolean hasEmptyChildSpace() {
		if(left==null||right==null){
			return true;
		}
		else{
			return false;
		}
	}

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
