package fox.algorithms.quicksort;

import fox.algorithms.heapsort.ArrayMaxHeap;
import fox.algorithms.util.ArrayUtil;

public abstract class QuickSort {

	public static <T extends Comparable<T>> void sort(T[] array) {
		quicksort(array, 0, array.length - 1);
	}

	public static <T extends Comparable<T>> void quicksort(T[] array, int start, int end) {
		if (start < end) {
			int q = ArrayUtil.partition(array, start, end);
			quicksort(array, start, q - 1);
			quicksort(array, q + 1, end);
		}
	}
}
