package edu.wmich.cs3310.hw2.Gray.trees;

public interface ITree {
	public Integer[] findMin();
	public void deleteMin();
	public void insert(int key, int value);
}	
