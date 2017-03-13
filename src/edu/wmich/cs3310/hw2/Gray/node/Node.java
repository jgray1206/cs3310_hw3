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
}
