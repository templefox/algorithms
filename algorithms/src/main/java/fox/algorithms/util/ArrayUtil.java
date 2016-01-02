package fox.algorithms.util;

import java.util.Random;

public abstract class ArrayUtil {
	private static Random random = new Random();
	
	public static <T> void exchange(T[] array ,int i, int j){
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
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
	public static <T extends Comparable<T>> int partition(T[] array, int start, int end) {
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
	
	public static <T extends Comparable<T>> int randomizedPartition(T[] array, int start, int end) {
		int p = random.nextInt(end - start) + start;
		ArrayUtil.exchange(array, end, p);
		return ArrayUtil.partition(array, start, end);
	}
}
