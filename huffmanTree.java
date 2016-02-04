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
	public void makeCodeTable(){
		
	}
	
	public void code(){
		
	}
	
	public void decode(){
		
	}
	
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
	
	private void queueTree(){
		for(int i = 0; i < 28; i++){
			if(freqTable[i] != 0){
					Tree character = new Tree();
					character.insert(freqTable[i], i);
					queue.insert(character);
			}
		}
	}
	
	private void makeHuffmanTree(){
		while(queue.get_nItems() > 1){
			Tree firstItem = queue.remove();
			Tree secondItem = queue.remove();
			
			Tree newTree = new Tree();
			newTree.insert(firstItem.getRoot().data_freq + secondItem.getRoot().data_freq, "+");
			newTree.getRoot().leftChild = firstItem;
			newTree.getRoot().rightChild = secondItem;
			
			queue.insert(newTree);
		}
	}
	
}
	