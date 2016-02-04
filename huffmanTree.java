// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp

import java.io.*;
import java.util.*; // for Stack class

class huffmanTree{
	//Instance Variables
	String input, inputUppercase;
	int maxSize = 28;
	int[] freqTable;
	PriorityQ queue; 
	Tree huffTree;
	String encode = "";
	String decode = "";
	String[] codeTable;



	
	
	
	//Constructor
	public huffmanTree(String input){
		freqTable = new int[maxSize];
		codeTable = new String[maxSize];
		this.input = input.toUpperCase();

		//initialize the frequency table
		for(int i = 0; i < maxSize; i++){
			freqTable[i] = 0;
		}

		makeFreqTable();
		queueTree();
		makeHuffmanTree();
		makeCodeTable(huffTree.getRoot(), "");


		
	}
	
	//Methods


	//Public Method

	private void makeCodeTable(Node n, String code){
		if (n.data_char == '+'){
			makeCodeTable(n.leftChild, code+"0");
			makeCodeTable(n.rightChild, code+"1");
		}
		else{ // hit a leaf node
			if(n.data_char == ' '){ //char is space
				codeTable[(int)n.data_char - 6] = code;
			}
			else if(n.data_char == '\n'){ //char is linefeed
				codeTable[(int)n.data_char + 17] = code;
			}
			else{ //char is a normal letter
				codeTable[(int)n.data_char - 65] = code;		
			}
			
		} 
	}



	//code goes through the input string character by character,
	//then finds the code in the code table corresponding to the
	//current character and adds the code to encode string
	public void code(){
		char currentChar;
		for (int i = 0; i < input.length(); i++){
			currentChar = input.charAt(i);

			if (currentChar == ' '){
				encode += codeTable[(int)currentChar - 6];
			} 
			else if (currentChar == '\n'){
				encode += codeTable[(int)currentChar + 17];
			}
			else{
				encode += codeTable[(int)currentChar - 65];
			}
		}
		
	}
	
	public void decode(){
		//reset encode for saftey
		encode = null;
		
		for(int i = 0; i < decode.length() - 1; i++){
			
			Node currentNode = huffTree.getRoot();
			
			while(currentNode.data_char != '+'){
				if (decode.substring(i,i+1) == "1"){
					currentNode = currentNode.rightChild;
				} else {
					currentNode = currentNode.leftChild;
				}
			}
			
			decode += currentNode.data_char;
		}
	}
	
	//Private Methods
	private void makeFreqTable(){
		char currentChar;
		int index; 
		for(int i = 0; i < input.length(); i++){
			currentChar = input.charAt(i);
			if(currentChar == ' '){
				index = (int)currentChar - 6; //subtract 6 to get space (32) to an index of 26
			}
			else if(currentChar == '\n'){
				index = (int)currentChar + 17; //add 17 to get lf (10) to an index of 27
			}
			else{

			index = (int)currentChar - 65; //ascii valuve for A is 65. convert char to ascii then 
										   //subtract the value of A to get an index for the frequency table
			freqTable[index]++; //increase the frequency count of the current character
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
			newTree.insert(firstItem.getRoot().data_freq + secondItem.getRoot().data_freq, '+');
			newTree.getRoot().leftChild = firstItem.getRoot();
			newTree.getRoot().rightChild = secondItem.getRoot();
			
			queue.insert(newTree);
		}

		huffTree = queue.remove();  //now that there is only one tree left in the queue,
	}
	
}
	