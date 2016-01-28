// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp

import java.io.*;
import java.util.*; // for Stack class

class huffmanTree{
	//Instance Variables
	String input, inputUppercase;
	int maxSize = 28;
	int freqTable[maxSize];
	priorityQ queue; 
	Tree huffTree;
	String encode, decode;
	String codeTable[maxSize];

	
	
	
	//Constructor
	public huffmanTree(String input){
		self.input = input.toUppercase();

		//initialize the frequency table
		for(int i = 0; i < maxSize; i++){
			freqTable[i] = 0;
		}

		makeFreqTable();
		queueTree();
		makeHuffmanTree();

		
	}
	
	//Methods

	//Public Variables
	public void makeCodeTable(){}
	public void code(){}
	public void decode(){}
	
	//Private Methods
	private void makeFreqTable(){
		char currentChar;
		int index; 
		for(int i = 0; i < input.length(); i++){
			currentChar = input.charAt(i);
			if(currentChar.equals(' ')){
				index = (int)currentChar - 6; //subtract 6 to get space (32) to an index of 26
			}
			else if(currentChar.equals('\n')){
				index = (int)currentChar + 17; //add 17 to get lf (10) to an index of 27
			}
			else{

			index = (int)currentChar - 65; //ascii valuve for A is 65. convert char to ascii then 
										   //subtract the value of A to get an index for the frequency table
			freqTable[index]++;
			}
		}
	}

	private void queueTree(){}
	private void makeHuffmanTree(){}
	
}
	