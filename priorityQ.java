// priorityQ.java
// demonstrates priority queue
// to run this program: C>java PriorityQApp
////////////////////////////////////////////////////////////////
class PriorityQ
{
	// array in sorted order, from max at 0 to min at size-1
	private int maxSize = 28;
	private Tree[] queArray;
	private int nItems;
	//-------------------------------------------------------------
	public PriorityQ(int s) // constructor
	{
		maxSize = s;
		queArray = new Tree[maxSize];
		nItems = 0;
	}
	//-------------------------------------------------------------
	public void insert(Tree item) // insert item
	{
		System.out.println(item);
		int j;
		if(nItems==0) // if no items,
			queArray[nItems++] = item; // insert at 0
		else // if items,
		{
			for(j=nItems-1; j>=0; j--) // start at end,
			{
				if( item.getFrequency() > queArray[j].getFrequency() ) // if new item larger,
					queArray[j+1] = queArray[j]; // shift upward
				else // if smaller,
					break; // done shifting
			} // end for
			queArray[j+1] = item; // insert it
			nItems++;
		} // end else (nItems > 0)
	} // end insert()
	//-------------------------------------------------------------
	public Tree remove() // remove minimum item
	{ return queArray[--nItems]; }
	//-------------------------------------------------------------
	public Tree peekMin() // peek at minimum item
	{ return queArray[nItems-1]; }
	//-------------------------------------------------------------
	public boolean isEmpty() // true if queue is empty
	{ return (nItems==0); }
	//-------------------------------------------------------------
	public int get_nItems(){
	   return nItems; 
	}
	public boolean isFull() // true if queue is full
	{ return (nItems == maxSize); }
	//-------------------------------------------------------------
} // end class PriorityQ
////////////////////////////////////////////////////////////////
// class PriorityQApp
// {
// 	public static void main(String[] args) throws IOException
// 	{
// 		PriorityQ thePQ = new PriorityQ(5);
// 		thePQ.insert(30);
// 		thePQ.insert(50);
// 		thePQ.insert(10);
// 		thePQ.insert(40);
// 		thePQ.insert(20);
// 		while( !thePQ.isEmpty() )
// 		{
// 			long item = thePQ.remove();
// 			System.out.print(item + " "); // 10, 20, 30, 40, 50
// 		} // end while
// 		System.out.println("");
// 	} // end main()
// 	//-------------------------------------------------------------
// } // end class PriorityQApp
