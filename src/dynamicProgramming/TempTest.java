package dynamicProgramming;

import java.util.ArrayList;

public class TempTest {

	public static void main(String[] args) {
		// Create an ArrayList
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        // Convert ArrayList to an array
        Integer[] array = arrayList.toArray(new Integer[0]);

        // Print the elements of the array
        for (Integer element : array) {
            System.out.println(element);
        }
	}

}
