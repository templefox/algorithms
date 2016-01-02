package fox.algorithms.randomselect;

import java.lang.reflect.Array;
import java.util.Random;

import fox.algorithms.util.ArrayUtil;

public class RandomSelect<T extends Comparable<T>> {
	private T[] array;
	private Random random = new Random();

	public RandomSelect(T[] array) {
		this.array = array;
	}

	public T selectMax(int i) {
		return select(array.length - i + 1, 0, array.length - 1);
	}

	public T selectMin(int i) {
		return select(i, 0, array.length - 1);
	}

	private T select(int i, int start, int end) {
		if (start == end) {
			return array[start];
		}
		
		int pivotIndex = ArrayUtil.randomizedPartition(array, start, end);
		
		int smallLength = pivotIndex - start + 1;

		if (i == smallLength) {
			return array[pivotIndex];
		} else if (i < smallLength) {
			return select(i, start, pivotIndex - 1);
		} else {
			return select(i - smallLength, pivotIndex + 1, end);
		}
	}

}
