/**
 * 
 */
package huffmanCoding;


/**
 * @author Belal Hamdeh
 *
 */
public class MinHeap<T extends Comparable<T>> implements MinHeapInterface<T> {
	private int size; // heap size
	private T[] heap;
	private int n;// count

	/**
	 * @param size
	 */
	public MinHeap(int size) {
		size++;
		heap = (T[]) new Comparable[size];
		this.size = size;
		n = 0;
	}

	public MinHeap(T[] list) {
		size = list.length + 1;
		heap = (T[]) new Comparable[size];
		for (int i = 0; i < list.length; i++)
			heap[i + 1] = list[i];
		n = list.length;

		heapIfY();
	}

	public void heapIfY() { // n/2 * log n
		for (int i = n / 2; i >= 1; i--)
			sink(i);
	}

	@Override
	public String toString() {
		String data = "Data:\t";
		String index = "Index:\t";
		for (int i = 1; i < size; i++) {
			data += heap[i] + "\t";
			index += i + "\t";
		}
		return index + "\n" + data;
	}

	private boolean greater(int i, int j) {
		return heap[i].compareTo(heap[j]) > 0;

	}

	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k / 2, k);
			k = k / 2;
		}
	}

	private void exch(int i, int j) {
		T data = heap[i];
		heap[i] = heap[j];
		heap[j] = data;
	}

	@Override
	public void add(T newEntry) {
		if (n + 1 < size) {
			heap[++n] = newEntry;
			swim(n);
		} else
			System.out.println("the Heap is full !");

	}

	private void sink(int k) {
		while (2 * k <= n) {
			int j = k * 2;
			if (j < n && greater(j, j + 1))
				j++;
			if (!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	@Override
	public T removeMin() {
		if (!isEmpty()) {
			T minTemp = heap[1];
			exch(1, n--);
			sink(1);
			heap[n + 1] = null;

			return minTemp;
		}
		System.out.println("Heap is Empty !");
		return null;
	}

	@Override
	public T getMin() {
		if (!isEmpty())
			return heap[1];
		return null;
	}

	@Override
	public boolean isEmpty() {
		return n == 0;
	}

	@Override
	public int getSize() {
		return n;
	}

	@Override
	public void clear() {
		n = 0;

	}

	public void heapSort() { // n log n

		for (int i = 1; i < heap.length; i++) // n log n
			removeMin();

		for (int i = 1, j = size - 1; i < size / 2; i++, j--) { // n/2
			exch(i, j);
		}
	}

	
}
