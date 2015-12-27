package fox.algorithms.heapsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import fox.algorithms.util.ArrayUtil;

public class ArrayMaxHeap<T extends Comparable<T>> implements Heap<T> {
	private List<T> heap = new ArrayList<T>();
	
	T[] heapEntries;
	int heapLength;
	
	boolean modified = false;

	/**
	 * Suppose left[i] and right[i] is the max heap But the i may be less than
	 * left[i] or right[i] Correct the heap recursively.
	 */
	public ArrayMaxHeap(T[] ts) {
		heapEntries = Arrays.copyOf(ts, ts.length + 1);
		for(int i = heapEntries.length - 1;i>0;i--){
			heapEntries[i] = heapEntries[i-1];
		}
		heapEntries[0] = null;
		heapLength = ts.length;
		buildMaxHeap();
	}

	public T[] sort() {
		//buildMaxHeap();
		int heapLength = this.heapLength;
		for (int i = heapLength; i >= 2; i--) {
			exchange(1, i);
			--this.heapLength;
			maxHeapify(1);
		}
		
		this.heapLength = heapLength;
		return Arrays.copyOfRange(heapEntries, 1, heapEntries.length);
	}
	
	public void insert(T t) {
		heapEntries = Arrays.copyOf(heapEntries,heapEntries.length + 1);
		heapLength ++;
		heapEntries[heapLength] = t;
		
		int i = heapLength;
		
		popup(i);
		
	}
	
	public T topAndPop() {
		T max = heapEntries[1];
		heapEntries[1] = heapEntries[heapLength];
		heapLength--;
		maxHeapify(1);
		return max;
	}

	public void increaseKey(int i, T newkey) {
		if(heapEntries[i].compareTo(newkey)>=0){
			throw new RuntimeException();
		}
		
		heapEntries[i] = newkey;
		
		popup(i);
	}

	public T top() {
		return heapEntries[1];
	}

	void maxHeapify(int subroot) {
		int leftroot = left(subroot);
		int rightroot = right(subroot);
		int heapsize = heapLength;
	
		int largestroot = 0;
	
		if (leftroot <= heapsize && heapEntries[leftroot].compareTo(heapEntries[subroot]) > 0) {
			// Exist leftroot and it is bigger
			largestroot = leftroot;
		} else {
			largestroot = subroot;
		}
	
		if (rightroot <= heapsize && heapEntries[rightroot].compareTo(heapEntries[largestroot]) > 0) {
			largestroot = rightroot;
		}
		// No need to else.
	
		if (largestroot != subroot) {
			exchange(largestroot, subroot);
			maxHeapify(largestroot);
		}
	}

	void buildMaxHeap() {
		for (int i = Math.floorDiv(heapFullLength(heapLength), 2); i > 0; i--) {
			maxHeapify(i);
		}
	}

	void popup(int i) {
		while (i>1 && heapEntries[parent(i)].compareTo(heapEntries[i])<0 ) {
			exchange(i, parent(i));
			i = parent(i);
		}
	}

	private void exchange(int i, int j){
		ArrayUtil.exchange(heapEntries, i, j);
	}

	private int left(int i) {
		return 2 * i;
	}

	private int right(int i) {
		return 2 * i + 1;
	}

	private int parent(int i) {
		return Math.floorDiv(i, 2);
	}

	int fullLength() {
		return heapFullLength(heapLength);
	}

	static int largestPow2(int heapLength) {
		int i = 1;
		while (heapLength >> 1 != 0) {
			i = i << 1;
			heapLength = heapLength >> 1;
		}
		return i;
	}

	static int heapFullLength(int heaplength) {
		int lastLeaves = largestPow2(heaplength);
		return lastLeaves * 2 - 1;
	}
}

