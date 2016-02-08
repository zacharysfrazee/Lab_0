
import java.io.*;
import java.util.*; // for Stack class

class huffmanTree{
	//Instance Variables
	String input;
	int maxSize = 28;
	int[] freqTable = new int[maxSize];
	PriorityQ queue = new PriorityQ(maxSize); 
	Tree huffTree;
	String encode = "";
	String decode = "";
	String[] codeTable = new String[maxSize];;



	
	
	
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


	public void displayTree(){
		huffTree.displayTree();
	}


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
		if (encode == ""){ //ensure we don't append the encoded message
						   //onto an already encoded message
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
		
        //Print out the code table
        for(int i = 0; i < 28; i++){
            if(codeTable[i] != null)
            	if (i < 26){
                	System.out.println((char)(i + 65) + " " + codeTable[i]);
                }
                else if(i == 26){ //we reached the space char
                	System.out.println("space" + " " + codeTable[i]);
                }
                else{ // i == 27 implies we reached the linefeed 
                	System.out.println("LF" + " " + codeTable[i]);
                }
        }
        
        //Print the coded message
        System.out.println("Coded msg: ");
        System.out.println(encode);
	}
	


	public void decode(){
		//reset encode for saftey
		decode = "";
		Node currentNode = huffTree.getRoot();
                
		for(int i = 0; i < encode.length(); i++){
            String value = encode.substring(i,i+1);
            
            //Traverse the tree
            if (value.equals("1")){
                    currentNode = currentNode.rightChild;
            } else {
                    currentNode = currentNode.leftChild;
            }
            
            //Check if we are at a leaf node
            if(currentNode.data_char != '+'){
                decode += currentNode.data_char;
                currentNode = huffTree.getRoot();
            }
		}
                
	    //Print decoded message
	    System.out.println("Decoded msg: ");
	    System.out.println(decode);
	}
	


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
            System.out.print(currentChar);  //print the character
		}
                
        //Print out Freq Table
        System.out.print("\n"); //new line
        
        //print characters in freq table
        for(int i = 0; i < 28; i++){
        	if (i < 26){
        		System.out.print((char)(i + 65) + " ");
	        }
	        else if(i == 26){ //we reached the space char
	        	System.out.print((char)(i + 6) + " ");
	        }
	        else{ // i == 27 implies we reached the linefeed 
	        	System.out.print("LF" + " ");
	        }        
    	}
        
        System.out.println(); //new line
        
        //print frequiencies in freq table
        for(int i = 0; i < 28; i++){
            System.out.print(freqTable[i] + " ");
        }
                
        System.out.print("\n\n\n"); //new line
	}

	private void queueTree(){
		for(int i = 0; i < 28; i++){
			if(freqTable[i] != 0){
                Tree character = new Tree();
                char currentChar;

                if(i < 26){ 
					//if index is < 26, we are in the normal characters 
					//of the freqTable. Thus we add 65 as the ascii offset.
					currentChar = (char)(i + 65);
				}
				else if(i == 26){
					//i == 26 implies we have a space, so we add 6 to get the correct ascii
					currentChar = (char)(i + 6);
				}
				else{
					//i == 27 implies we have a linefeed, so we subtract 17
					currentChar = (char)(i - 17); 
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
									//make it the huffman tree
	}
	
}
	