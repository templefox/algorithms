package fox.algorithms.quicksort;

import java.util.Arrays;
import static org.junit.Assert.*;

import org.junit.Test;

public class QuickSortTest {

	@Test
	public void quicksortTest() {
		Integer[] array = { 3, 1, 5, 8, 7, 4, 9, 2, 0, 6 };
		Integer[] expect = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		QuickSort.sort(array);

		boolean f = Arrays.equals(array, expect);
		assertTrue(f);
	}
}
