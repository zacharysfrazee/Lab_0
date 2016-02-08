// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp

import java.io.*;
import java.util.*; // for Stack class

class huffmanTree{
	//Instance Variables
	String input;
	int maxSize = 28;
	int[] freqTable = new int[maxSize];;
	PriorityQ queue =  new PriorityQ(maxSize); 
	Tree huffTree;
	String encode = null;
	String decode = null;
	String[] codeTable = new String[maxSize];



	
	
	
	//Constructor
	public huffmanTree(String input){
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

	public void displayTree(){
		huffTree.displayTree();
	}

	private void makeCodeTable(Node n, String code){

		System.out.println(n.data_char + " " + Integer.toString((int)n.data_char));

		if (n.data_char == '+'){
			makeCodeTable(n.leftChild, code+"0");
			makeCodeTable(n.rightChild, code+"1");
		}
		//I'm not convinced we need the n.data_char != 0. we we're getting a 0 in
		//ascii, which is a null, so I added this check. However, the ascii null
		//might be a symptom of something else. We aren't successfully getting
		//any linefeed characters into the huffman tree, it seems.
		else if (n.data_char != 0){ // hit a leaf node
			if(n.data_char == ' '){ //char is space
				System.out.println("char is a space");
				codeTable[(int)n.data_char - 6] = code;
			}
			else if(n.data_char == '\n'){ //char is linefeed
				System.out.println("char is a linefeed");
				codeTable[(int)n.data_char + 17] = code;
			}
			else{ //char is a normal letter
				System.out.println("char is a normal letter");
				codeTable[(int)n.data_char - 65] = code;		
			}
			
		} 
	}



	//code goes through the input string character by character,
	//then finds the code in the code table corresponding to the
	//current character and adds the code to encode string
	public void code(){
		char currentChar;
		if (encode != null){


			for (int i = 0; i < input.length(); i++){
				currentChar = input.charAt(i);
				System.out.println(currentChar);

				if (currentChar == ' '){
					encode += codeTable[(int)currentChar - 6];
					System.out.println(codeTable[(int)currentChar - 6] + " " + Integer.toString((int)currentChar - 6));
				} 
				else if (currentChar == '\n'){
					encode += codeTable[(int)currentChar + 17];
					System.out.println(codeTable[(int)currentChar + 17] + " " + Integer.toString((int)currentChar + 17));
				}
				else{
					encode += codeTable[(int)currentChar - 65];
					System.out.println(codeTable[(int)currentChar - 65] + " " + Integer.toString((int)currentChar - 65));
				}
			}
		}

		System.out.println(encode);
		
	}
	
	public void decode(){
		if (decode != null){
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

		System.out.println(decode);
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
			}
			freqTable[index]++; //increase the frequency count of the current character
		}
	}

	private void queueTree(){
		for(int i = 0; i < maxSize; i++){
			if(freqTable[i] != 0){
					Tree character = new Tree();
					char currentChar;

					if(i < 26){ 
						//if index is < 26, we are in the normal characters 
						//of the freqTable. Thus we add 65 as the ascii offset.
						currentChar = (char)(i + 65);
						//System.out.println(currentChar);
					}
					else if(i == 26){
						//i == 26 implies we have a space, so we add 6 to get the correct ascii
						currentChar = (char)(i + 6);
						System.out.print("made it to index 26 ");
						System.out.println(currentChar);
					}
					else{
						//i == 27 implies we have a linefeed, so we subtract 17
						currentChar = (char)(i - 27);
						System.out.print("made it to index 27 ");
						System.out.println(currentChar);
					}

					character.insert(freqTable[i], currentChar);
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
	