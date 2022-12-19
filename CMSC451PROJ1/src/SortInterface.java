/*
 *  Name: Alex Hong
 *  Class: CMSC 451
 *  Professor: Prof. Potolea
 *  Due Date: 11/15/22
 *  Description: The interface containing the recursive and iterative functions
 *  and functions to get the count and time
 */
public interface SortInterface {
	public void recursiveSort(int[] list) throws UnsortedException;
	
	public void iterativeSort(int[] list) throws UnsortedException;
	
	public int getCount();
	
	public long getTime();
}
