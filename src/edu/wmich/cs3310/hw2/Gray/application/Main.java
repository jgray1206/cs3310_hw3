package edu.wmich.cs3310.hw2.Gray.application;

import java.util.Random;
import java.util.Scanner;

import edu.wmich.cs3310.hw2.Gray.trees.*;

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
		
		int[] operations = new int[sequence];
		for(int i = 0; i < sequence; i++){
			operations[i] = rand.nextInt(10000)+1;
		}
		
		int key, value;
		boolean printed = false;
		for(int x: operations){
			if(x<2001){
				key = rand.nextInt(1000)+1;
				value = rand.nextInt(15001)+5000;
				SBT.insert(key, value);
				CBT.insert(key, value);
			}
			else if(x<4001){
				SBT.deleteMin();
				CBT.deleteMin();
			}
			else{
				SBT.findMin();
				CBT.findMin();
			}
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
		
		if(!printed){
			System.out.println("Sorry. The trees never hit a size of 30. \nTry again with a larger sequence (10,000).");
		}
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
