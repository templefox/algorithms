package fox.algorithms.heapsort;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HeapTest {
	@Test
	public void largetPow2Test() {
		assertEquals(1, Heap.largestPow2(1));
		assertEquals(2, Heap.largestPow2(2));
		assertEquals(2, Heap.largestPow2(3));
		assertEquals(4, Heap.largestPow2(5));
		assertEquals(8, Heap.largestPow2(10));
		assertEquals(8, Heap.largestPow2(15));
		assertEquals(16, Heap.largestPow2(20));
	}

	@Test
	public void heapMaxify() {
		Integer[] ts = { 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		Heap<Integer> heap = new Heap<Integer>(ts);
		// 5
		// 4 6
		// 9 1 3 0
		// 2 10 7
		Integer[] ts2 = { null, 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		heap.heapEntries = ts2;

		List<Integer> t = Arrays.asList(heap.heapEntries);
		int index = t.indexOf(9);
		heap.maxHeapify(index);

		assertEquals(10, (int) heap.heapEntries[index]);

		int index2 = t.indexOf(6);
		heap.maxHeapify(index2);

		assertEquals(6, (int) heap.heapEntries[index2]);
	}

	@Test
	public void buildMaxHeap() {
		Integer[] ts = { 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		Heap<Integer> heap = new Heap<Integer>(ts);

		boolean f = Arrays.equals(heap.heapEntries, new Integer[] { null, 10, 9, 6, 5, 1, 3, 0, 2, 4 });
		assertTrue(f);
	}

	@Test
	public void heapSort() {
		Integer[] ts = { 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		Heap<Integer> heap = new Heap<Integer>(ts);

		Integer[] actual = heap.sort().toArray(new Integer[]{});
		
		boolean f = Arrays.equals(actual, new Integer[] { 0, 1, 2, 3, 4, 5, 6, 9, 10 });
		assertTrue(f);
	}
	
	@Test
	public void heapExtractTest(){
		Integer[] ts = { 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		Heap<Integer> heap = new Heap<Integer>(ts);
		
		assertEquals(10, (int)heap.maxHeadExtractMax());
		boolean f = Arrays.equals(heap.heapEntries, new Integer[] { null, 9, 5, 6, 4, 1, 3, 0, 2, 4 });
		assertTrue(f);
	}
	
	@Test
	public void heapIncreaseKey(){
		Integer[] ts = { 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		Heap<Integer> heap = new Heap<Integer>(ts);

		heap.heapIncreaseKey(7, 5);
		boolean f = Arrays.equals(heap.heapEntries, new Integer[] { null, 10, 9, 6, 5, 1, 3, 5, 2, 4 });
		assertTrue(f);
	}
}
