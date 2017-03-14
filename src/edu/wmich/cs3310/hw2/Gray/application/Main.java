package edu.wmich.cs3310.hw2.Gray.application;

import edu.wmich.cs3310.hw2.Gray.trees.*;

public class Main {
	public static void main(String[] args){
		SizeBalancedTree SBT = new SizeBalancedTree();
		SBT.insert(100, 1);
		SBT.insert(4, 1);
		SBT.insert(1, 10);
		SBT.insert(101, 2);
		SBT.insert(1000, 1);
		SBT.print();
		SBT.deleteMin();
		SBT.print();
		SBT.deleteMin();
		SBT.print();
		SBT.deleteMin();
		SBT.print();
		SBT.deleteMin();
		SBT.print();
		SBT.deleteMin();
		SBT.print();
	}
}
