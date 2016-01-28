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
		self.input = input;

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
	private void makeFreqTable(){}
	
	private void queueTree(){
		for(int i = 0; i < 28; i++){
			if(freqTable[i] != 0){
					Tree character = new Tree();
					character.insert(freqTable[i], i);
				
					queue.insert(character);
			}
		}
	}
	
	private void makeHuffmanTree(){}
	
}
	