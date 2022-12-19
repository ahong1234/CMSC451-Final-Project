/*
 *  Name: Alex Hong
 *  Class: CMSC 451
 *  Professor: Prof. Potolea
 *  Due Date: 11/15/22
 *  Description: A class containing iterative and recursive merge sort algorithms 
 *  from https://levelup.gitconnected.com/sorting-algorithms-merge-sort-top-down-bottom-up-for-arrays-linked-lists-2426dcc39611.
 *  Implements SortInterface and times the recursive and iterative sorts in nanoseconds.
 *  Both recursive and iterative sorts use the same merge() method.
 */

public class MergeSort implements SortInterface {
	long time;
	int count;

	@Override
	public void recursiveSort(int[] list) throws UnsortedException {
		count = 0;
        time = 0;
        long startTime = System.nanoTime();
        MergeSortIter(list, 0, list.length-1);
        long endTime = System.nanoTime();
        time = (endTime - startTime);
        //System.out.println("recurs: " + count);
        if (isNotSorted(list)) {
            throw new UnsortedException();
        }      
	}

	public void MergeSortIter(int[] arr, int low, int high) {
		if (low < high) {
			//count++; 
			var mid = (low + high) / 2;
			MergeSortIter(arr, low, mid);
			MergeSortIter(arr, mid + 1, high);
			Merge(arr, low, mid, high);
		}
	}

	@Override
	public void iterativeSort(int[] list) throws UnsortedException {
		count = 0;
		time = 0;
		long startTime = System.nanoTime();
		MergeSortIterative(list);
		long endTime = System.nanoTime();
		time = (endTime - startTime);
		//System.out.println(count);
		if (isNotSorted(list)) {
			throw new UnsortedException();
		}
	}

	public void MergeSortIterative(int[] arr) {
		int p, low, mid, high, i;

		for (p = 2; p <= arr.length; p = p * 2) {
			for (i = 0; i + p - 1 < arr.length; i = i + p) {
				
				low = i;
				high = i + p - 1;
				mid = (low + high) / 2;
				Merge(arr, low, mid, high);
			}
		}
		if (p / 2 < arr.length) {
			Merge(arr, 0, p / 2 - 1, arr.length - 1);
		}
	}

	public void Merge(int[] arr, int low, int mid, int high) {
		var i = low;
		var j = mid + 1;
		var k = low;

		var auxiliaryArr = new int[high + 1];

		while (i <= mid && j <= high) {
			if (arr[i] < arr[j]) {
				count++; // compare and assign to auxiliary array
				auxiliaryArr[k++] = arr[i++];
			} else {
				count++; // compare and assign to auxiliary array
				auxiliaryArr[k++] = arr[j++];
			}
		}
		while (i <= mid) {
			//count++;
			auxiliaryArr[k++] = arr[i++];
		}
		while (j <= high) {
			//count++;
			auxiliaryArr[k++] = arr[j++];
		}

		for (var r = low; r <= high; r++) {
			//count++;
			arr[r] = auxiliaryArr[r];
		}
		//System.out.println(count);
	}

	private boolean isNotSorted(int[] list) {
		for (int i = 0; i < list.length - 1; i++) {
			if (list[i] <= list[i + 1]) {
				
				return false;
			}
		}
		System.out.println("list not sorted");
		return true;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public long getTime() {
		return time;
	}

	// print the array
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}
