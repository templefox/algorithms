package fox.algorithms.heapsort;

import java.util.List;

public interface Heap<T extends Comparable<T>> {
	T[] sort();

	void insert(T key);

	T top();

	T topAndPop();

	void increaseKey(int i, T key);
}
