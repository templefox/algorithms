package fox.algorithms.util;

public abstract class ArrayUtil {
	public static <T> void exchange(T[] array ,int i, int j){
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
