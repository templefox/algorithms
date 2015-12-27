package fox.algorithms.quicksort;

import fox.algorithms.heapsort.ArrayMaxHeap;
import fox.algorithms.util.ArrayUtil;

public abstract class QuickSort {

	public static <T extends Comparable<T>> void sort(T[] array) {
		quicksort(array, 0, array.length - 1);
	}

	public static <T extends Comparable<T>> void quicksort(T[] array, int start, int end) {
		if (start < end) {
			int q = partition(array, start, end);
			quicksort(array, start, q - 1);
			quicksort(array, q + 1, end);
		}
	}

	/**
	 * Small collection [start .. smallindex] Large collection (smallindex ..
	 * largeIndex]
	 * 
	 * @param array
	 * @param start
	 * @param end
	 * @return new position of pivot value
	 */
	private static <T extends Comparable<T>> int partition(T[] array, int start, int end) {
		T pivotValue = array[end];
		int smallIndex = start - 1;

		for (int largeIndex = start; largeIndex < end; largeIndex++) {
			if (array[largeIndex].compareTo(pivotValue) <= 0) {
				smallIndex++;
				ArrayUtil.exchange(array, smallIndex, largeIndex);
			}
		}
		ArrayUtil.exchange(array, smallIndex + 1, end);
		return smallIndex + 1;
	}
}
