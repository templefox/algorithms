package fox.algorithms.heapsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Heap<T extends Comparable<T>> {
	private List<T> heap = new ArrayList<T>();
	T[] heapEntries;
	int heapLength;

	/**
	 * Suppose left[i] and right[i] is the max heap But the i may be less than
	 * left[i] or right[i] Correct the heap recursively.
	 */
	public Heap(T[] ts) {
		List<T> array = Arrays.asList(ts);
		List<T> list = new ArrayList<T>(array);
		list.add(0, null);
		heapEntries = list.toArray(ts);
		heapLength = ts.length;

		buildMaxHeap();
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

	public List<T> sort() {
		//buildMaxHeap();
		for (int i = heapLength; i >= 2; i--) {
			exchange(1, i);
			--heapLength;
			maxHeapify(1);
		}
		return Arrays.asList(this.heapEntries).subList(1, heapEntries.length);
	}
	
	void exchange(int i, int j){
		T temp = heapEntries[i];
		heapEntries[i] = heapEntries[j];
		heapEntries[j] = temp;
	}

	public void maxHeatInsert(T t) {

	}

	public T maxHeadExtractMax() {
		T max = heapEntries[1];
		heapEntries[1] = heapEntries[heapLength];
		heapLength--;
		maxHeapify(1);
		return max;
	}

	public void heapIncreaseKey(int i, T newkey) {
		if(heapEntries[i].compareTo(newkey)>=0){
			throw new RuntimeException();
		}
		
		heapEntries[i] = newkey;
		
		while (i>1 && heapEntries[parent(i)].compareTo(heapEntries[i])<0 ) {
			exchange(i, parent(i));
			i = parent(i);
		}
	}

	public T headMaximum() {
		return heapEntries[1];
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
