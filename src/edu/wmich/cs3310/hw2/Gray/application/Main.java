package edu.wmich.cs3310.hw2.Gray.application;

import java.util.Random;
import java.util.Scanner;

import edu.wmich.cs3310.hw2.Gray.trees.*;

/**
 * This class deals with all user input, timing,
 * testing, and generation for 
 * the required binary trees.
 * @author John
 *
 */
public class Main {
	public static void main(String[] args){
		//Initialize objects
		Scanner scnr = new Scanner(System.in);
		SizeBalancedTree SBT = new SizeBalancedTree();	
		CompleteBinaryTree CBT = new CompleteBinaryTree();
		Random rand = new Random();
		
		//Declare array/loop variables
		int sequence;
				
				
		//Gets user input for array specifications
		sequence = getUserInt("Enter length of test sequence: ", scnr);
		if(sequence<2)
			sequence = 2;
		
		//creates the array that will contain the operations.
		int[] operations;
		try{ //try/catch to avoid weird bug that an array can't be the size of Integer.MAX
			operations = new int[sequence];
		}catch(Error e){
			System.out.println("Sorry. Number was too big. Defaulting to large number that will work.");
			operations = new int[100000000];
			sequence = 100000000;
		}
		
		//Generates random sequence
		for(int i = 0; i < sequence; i++){
			operations[i] = rand.nextInt(10000)+1;
		}
		
		//Initialize all variables for testing/timing
		int key, value;
		long[] times = new long[8];
		long before, after;
		boolean printed = false;
		
		//for each operation..
		for(int x: operations){
			//check which operation it is, do it to both trees, time them.
			if(x<2001){
				key = rand.nextInt(1000)+1;
				value = rand.nextInt(15001)+5000;
				
				before = System.nanoTime();
				SBT.insert(key, value);
				after = System.nanoTime();
				times[0] += after-before;
				times[6] += after-before;
				
				before = System.nanoTime();
				CBT.insert(key, value);
				after = System.nanoTime();
				times[1] += after-before;
				times[7] += after-before;
			} 
			else if(x<4001){
				before = System.nanoTime();
				SBT.deleteMin();
				after = System.nanoTime();
				times[2] += after-before;
				times[6] += after-before;

				before = System.nanoTime();
				CBT.deleteMin();
				after = System.nanoTime();
				times[3] += after-before;
				times[7] += after-before;
			}
			else{
				before = System.nanoTime();
				SBT.findMin();
				after = System.nanoTime();
				times[4] += after-before;
				times[6] += after-before;

				before = System.nanoTime();
				CBT.findMin();
				after = System.nanoTime();
				times[5] += after-before;
				times[7] += after-before;
			}
			
			//prints all information when size hits 30
			if(CBT.size()==30&&!printed){
				key = rand.nextInt(1000)+1;
				value = rand.nextInt(15001)+5000;
				System.out.println("BEFORE <1,1> INSERT -----------------------------------------------");
				SBT.print();
				CBT.print();
				SBT.insert(1, 1);
				CBT.insert(1, 1);
				System.out.println("\nAFTER <1,1> INSERT. BEFORE DELETEMIN ---------------------------------------");
				SBT.print();
				CBT.print();
				SBT.deleteMin();
				CBT.deleteMin();
				System.out.println("\nAFTER DELETEMIN, BEFORE FIDNMIN -----------------------------------------");
				SBT.print();
				CBT.print();
				System.out.println("\nAFTER FINDMIN ---------------------------------------------");
				System.out.println("SBT MIN: "+SBT.findMin());
				System.out.println("CBT MIN: "+CBT.findMin()+"\n");
				SBT.print();
				CBT.print();
				printed = true;
			}
		}
		
		//if the array didn't hit 30, apologize.
		if(!printed){
			System.out.println("Sorry. The trees never hit a size of 30. \nTry again with a larger sequence (5,000 usually works).");
		}
		
		//print out times of each operation + total tree
		System.out.printf("\nSBT Insert: %d ns\n"
						+ "CBT Insert: %d ns\n\n"
						+ "SBT DeleteMin: %d ns\n"
						+ "CBT DeleteMin: %d ns\n\n"
						+ "SBT FindMin: %d ns\n"
						+ "CBT FindMin: %d ns\n\n"
						+ "SBT Total Time: %d ns\n"
						+ "CBT Total Time: %d ns\n", times[0]/(sequence-1), times[1]/(sequence-1),
						times[2]/(sequence-1), times[3]/(sequence-1), times[4]/(sequence-1),
						times[5]/(sequence-1), times[6], times[7]);
	
		scnr.close();
	}
	
	/**
	 * When called, it will validate and accept user input.
	 * Only accepts a valid integer. Continues until it gets one.
	 * @param question A string containing the question to print.
	 * @param scnr A scanner object from main. 
	 * @return Returns valid user input to store in variable.
	 */
	public static int getUserInt(String question, Scanner scnr){
		//Asks user for what is to be inputed
		System.out.println(question);
		
		//Attempts to get input
		try{
			String input = scnr.nextLine();
			int output = Integer.parseInt(input.trim());
			return output;
		}catch(NumberFormatException e){		//if not valid, retry
			return getUserInt("Sorry. Please enter valid Integer: ",scnr);
		}
		
	}	
}
