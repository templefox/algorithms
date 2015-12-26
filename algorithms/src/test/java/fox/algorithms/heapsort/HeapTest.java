package fox.algorithms.heapsort;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HeapTest {
	@Test
	public void largetPow2Test() {
		assertEquals(1, ArrayMaxHeap.largestPow2(1));
		assertEquals(2, ArrayMaxHeap.largestPow2(2));
		assertEquals(2, ArrayMaxHeap.largestPow2(3));
		assertEquals(4, ArrayMaxHeap.largestPow2(5));
		assertEquals(8, ArrayMaxHeap.largestPow2(10));
		assertEquals(8, ArrayMaxHeap.largestPow2(15));
		assertEquals(16, ArrayMaxHeap.largestPow2(20));
	}

	@Test
	public void heapMaxify() {
		Integer[] ts = { 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		ArrayMaxHeap<Integer> heap = new ArrayMaxHeap<Integer>(ts);
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
		ArrayMaxHeap<Integer> heap = new ArrayMaxHeap<Integer>(ts);

		boolean f = Arrays.equals(heap.heapEntries, new Integer[] { null, 10, 9, 6, 5, 1, 3, 0, 2, 4 });
		assertTrue(f);
	}

	@Test
	public void heapSort() {
		Integer[] ts = { 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		ArrayMaxHeap<Integer> heap = new ArrayMaxHeap<Integer>(ts);

		Integer[] actual = heap.sort();
		
		boolean f = Arrays.equals(actual, new Integer[] { 0, 1, 2, 3, 4, 5, 6, 9, 10 });
		assertTrue(f);
	}
	
	@Test
	public void heapExtractTest(){
		Integer[] ts = { 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		ArrayMaxHeap<Integer> heap = new ArrayMaxHeap<Integer>(ts);
		
		assertEquals(10, (int)heap.topAndPop());
		boolean f = Arrays.equals(heap.heapEntries, new Integer[] { null, 9, 5, 6, 4, 1, 3, 0, 2, 4 });
		assertTrue(f);
	}
	
	@Test
	public void heapIncreaseKey(){
		Integer[] ts = { 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		ArrayMaxHeap<Integer> heap = new ArrayMaxHeap<Integer>(ts);

		heap.increaseKey(7, 5);
		boolean f = Arrays.equals(heap.heapEntries, new Integer[] { null, 10, 9, 6, 5, 1, 3, 5, 2, 4 });
		assertTrue(f);
	}
	
	@Test
	public void heapMaxInsertTest(){
		Integer[] ts = { 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		ArrayMaxHeap<Integer> heap = new ArrayMaxHeap<Integer>(ts);
		heap.insert(99);
		
		assertEquals(99, (int)heap.top());
		

		heap.insert(98);
		assertEquals(99, (int)heap.top());
	}
	
	@Test
	public void recoverHeapLength(){
		Integer[] ts = { 5, 4, 6, 9, 1, 3, 0, 2, 10 };
		ArrayMaxHeap<Integer> heap = new ArrayMaxHeap<Integer>(ts);

		Integer[] actual = heap.sort();
		
		boolean f = Arrays.equals(actual, new Integer[] { 0, 1, 2, 3, 4, 5, 6, 9, 10 });
		assertTrue(f);
		
	}
}
