package edu.wmich.cs3310.hw2.Gray.trees;
import edu.wmich.cs3310.hw2.Gray.node.*;

/**
 * This is the implementation of a size balanced binary tree.
 * It utilizes my node class, along with following
 * the ITree interface.
 * Javadocs for overriden ITree methods are in ITree.
 * @author john
 */
public class SizeBalancedTree implements ITree {
	
	Node root; //holder of root node, the top of the tree
	
	/**
	 * Basic constructor creates empty tree
	 */
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

	/**
	 * Top heapify, utilized by deleteMin.
	 * Uses recursion.
	 * @param current - holds current recursive step node
	 */
	private void topHeapify(Node current) {
		//if node is a leaf, heapify is done
		if(current.isLeaf()){
			return;
		} // if no right, check left
		else if(current.left!=null&&current.left.key<current.key&&current.right==null){
			current.swap(current.left);
		} //if no left, check right
		else if(current.right!=null&&current.right.key<current.key&&current.left==null){
			current.swap(current.right);
		}
		else if(!current.hasEmptyChildSpace()){ //if both children there, check both in comparison to index.
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

	/**
	 * Recursive bottom heapify
	 * Utilized by insertion.
	 * @param current recursive step holder
	 */
	private void bottomHeapify(Node current) {
		//if root, bottom heapify is done
		if(current.parent==null){
			return;
		}
		else{ //if parent is less, swap
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
			return; //else, return
		}
	}
	
	/**
	 * Prints out the preorder and
	 * inorder versions of the SBT
	 */
	public void print(){
		if(root==null){
			System.out.println("empty");
		}
		else{
			System.out.print("SBT Preorder: {");
			printPreorder(root);
			System.out.print("}\nSBT Inorder:{");
			printInorder(root);
			System.out.println("}");
		}
	}

	/**
	 * prints inorder SBT
	 * @param current starting point of print
	 */
	private void printInorder(Node current) {
		if(current.left!=null){
			printInorder(current.left);
		}
		System.out.print("[<"+current.key+","+current.data+">"+","+current.size+"],");
		if(current.right!=null){
			printInorder(current.right);
		}
	}

	/**
	 * prints preorder SBT
	 * @param current starting point of print
	 */
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
