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
				if(current.left==null){
					current.size--;
					current = current.right;
				}
				else if(current.right==null){
					current.size--;
					current = current.left;
				}
				else{
					if(current.left.size>=current.right.size){
						current.size--;
						current = current.left;
					}
					else{
						current.size--;
						current = current.right;
					}
				}
			}
			root.key = current.key;
			root.data = current.data;
			if(current.parent.left == current){
				current.parent.left = null;
			}
			else{
				current.parent.right = null;
			}
			current.parent = null;
			topHeapify(root);
		}
	}

	private void topHeapify(Node current) {
		if(current.isLeaf()){
			return;
		}
		else if(current.left!=null&&current.left.key<current.key&&current.right==null){
			current.swap(current.left);
		}
		else if(current.right!=null&&current.right.key<current.key&&current.left==null){
			current.swap(current.right);
		}
		else if(!current.hasEmptyChildSpace()){
			if(current.right.key<=current.left.key&&current.key>current.right.key){
				current.swap(current.right);
				topHeapify(current.right);
			}
			else if(current.left.key<=current.right.key&&current.key>current.left.key){
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
					current.size = current.size+1;
					current = current.right;
				}
				else{
					current.size = current.size+1;
					current = current.left;
				}
			}
			if(current.left==null){
				current.size++;
				current.left = new Node(key,value);
				current.left.parent = current;
				bottomHeapify(current.left);
			}
			else if(current.right==null){
				current.size++;
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
				int key, data;
				key = current.key;
				data = current.data;
				current.key = current.parent.key;
				current.data = current.parent.data;
				current.parent.key = key;
				current.parent.data = data;	
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
			System.out.print("{");
			printPreorder(root);
			System.out.print("},{");
			printInorder(root);
			System.out.println("}");
		}
	}

	private void printInorder(Node current) {
		if(current.left!=null){
			printInorder(current.left);
		}
		System.out.print("[<"+current.key+","+current.data+">"+","+current.size+"],");
		if(current.right!=null){
			printInorder(current.right);
		}
	}

	private void printPreorder(Node current) {
		System.out.print("[<"+current.key+","+current.data+">"+","+current.size+"],");
		if(current.left!=null){
			printInorder(current.left);
		}
		if(current.right!=null){
			printInorder(current.right);
		}		
	}
	

}
