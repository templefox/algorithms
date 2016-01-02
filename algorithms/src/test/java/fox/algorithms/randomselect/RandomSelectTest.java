package fox.algorithms.randomselect;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomSelectTest {

	@Test
	public void MaxTest() {
		Integer[] a = { 2, 1, 4, 6, 7, 8, 9 };
		List<Integer> aList = Arrays.asList(Arrays.copyOf(a, a.length));
		aList.sort(Integer::compareTo);
		Collections.reverse(aList);

		RandomSelect<Integer> rs = new RandomSelect<Integer>(a);
		int max2 = rs.selectMax(2);
		int max5 = rs.selectMax(5);

		assertEquals(8, max2);
		assertEquals((int) aList.get(5 - 1), max5);
	}
	
	@Test
	public void MinTest(){
		Integer[] a = { 2, 1, 4, 6, 7, 8, 9 };
		List<Integer> aList = Arrays.asList(Arrays.copyOf(a, a.length));
		aList.sort(Integer::compareTo);

		RandomSelect<Integer> rs = new RandomSelect<Integer>(a);
		int max2 = rs.selectMin(2);
		int max5 = rs.selectMin(5);

		assertEquals(2, max2);
		assertEquals((int) aList.get(5 - 1), max5);
		
	}
}
