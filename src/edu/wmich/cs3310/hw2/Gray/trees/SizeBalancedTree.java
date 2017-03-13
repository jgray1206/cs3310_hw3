package edu.wmich.cs3310.hw2.Gray.trees;
import edu.wmich.cs3310.hw2.Gray.node.*;

public class SizeBalancedTree implements ITree {
	
	Node root;
	
	public SizeBalancedTree(){
		root = null;
	}
	
	@Override
	public String findMin() {
		if(root==null)
			return null;
		else
			return "<"+root.key+","+root.data+">";
	}

	@Override
	public void deleteMin() {
		if(root==null){
			return;
		}
		else if(root.size==1){
			root = null;
		}
		else{
			Node current = root;
			while(!current.isLeaf()){
				if(current.left.size>=current.right.size){
					current = current.left;
				}
				else{
					current = current.right;
				}
			}
			root.key = current.key;
			root.data = current.data;
			if(current.parent.left.key == current.key){
				current.parent.left = null;
			}
			else{
				current.parent.right = null;
			}
			topHeapify(root);
		}
	}

	private void topHeapify(Node current) {
		if(current.isLeaf()){
			return;
		}
		else if(current.left.key<current.key&&current.right==null){
			current.swap(current.left);
		}
		else if(current.right.key<current.key&&current.left==null){
			current.swap(current.right);
		}
		else{
			if(current.right.key<current.left.key&&current.key<current.right.key){
				current.swap(current.right);
				topHeapify(current.right);
			}
			else if(current.left.key<current.right.key&&current.key<current.left.key){
				current.swap(current.left);
				topHeapify(current.left);
			}
			else{
				return;
			}
		}
		
	}

	@Override
	public void insert(int key, int value) {
		if(root==null){
			root = new Node(key,value);
		}
		else{
			Node current = root;
			while(!current.hasEmptyChildSpace()){
				if(current.left.size>current.right.size){
					current.size++;
					current = current.right;
				}
				else{
					current.size++;
					current = current.left;
				}
			}
			if(current.left==null){
				current.left = new Node(key,value);
				current.left.parent = current;
				bottomHeapify(current.left);
			}
			else if(current.right==null){
				current.right = new Node(key,value);
				current.right.parent = current;
				bottomHeapify(current.right);
			}
		}
	}

	private void bottomHeapify(Node current) {
		if(current.parent==null){
			return;
		}
		else{
			if(current.key<current.parent.key){
				current.swap(current.parent);
				bottomHeapify(current.parent);
			}
			return;
		}
	}
	
	public void print(){
		if(root==null){
			System.out.println("empty");
		}
		else{
			printPreorder(root);
			printInorder(root);
		}
	}

	private void printInorder(Node current) {
		
	}

	private void printPreorder(Node current) {
		
	}
	

}
