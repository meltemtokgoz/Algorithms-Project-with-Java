import java.util.NoSuchElementException;

public class binaryHeap {

	public static int getIndexOfMax(String array[]) {
		String m = getIndexMax(array);
		int max = 0;
		for(int i = 1; i < array.length; i++) {
			if(array[i].equals(m)){
				max= i;
			}
		}
		return max;
	}

	public static String getIndexMax(String frame[]) {
		binaryHeap tree = new binaryHeap(frame.length);		
		for (int t = 0; t < frame.length; t++) {
			tree.insert(frame[t]);
		}
		String max = tree.findMax();
		return max;
	}

	private static final int d = 2;
	private String[] heap;
	private int heapSize;

	public binaryHeap(int capacity) {
		heapSize = 0;
		heap = new String[capacity + 1];
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	public boolean isFull() {
		return heapSize == heap.length;
	}

	private int parent(int i) {
		return (i - 1) / d;
	}

	private int kthChild(int i, int k) {
		return d * i + k;
	}

	public void insert(String x) {
		if (!isFull()) {

			heap[heapSize++] = x;
			heapifyUp(heapSize - 1);
		}
	}

	public String delete(int x) {
		String key = heap[x];
		if (!isEmpty()) {
			heap[x] = heap[heapSize - 1];
			heapSize--;
			heapifyDown(x);
		}
		return key;
	}

	private void heapifyUp(int i) {
		String temp = heap[i];
		int c = temp.compareTo(heap[parent(i)]);
		while (i > 0 && c>0) {
			heap[i] = heap[parent(i)];
			i = parent(i);
		}
		heap[i] = temp;
	}

	private void heapifyDown(int i) {
		int child;
		String temp = heap[i];
		while (kthChild(i, 1) < heapSize) {
			child = maxChild(i);
			int c = temp.compareTo( heap[child]);
			if (c>0) {
				heap[i] = heap[child];
			} else
				break;

			i = child;
		}
		heap[i] = temp;
	}

	private int maxChild(int i) {
		int leftChild = kthChild(i, 1);
		int rightChild = kthChild(i, 2);
		int c = heap[leftChild].compareTo(heap[rightChild]);
		if (c>0)
			return leftChild;
		else
			return rightChild;
	}

	public void printHeap() {
		System.out.print("\nHeap = ");
		for (int i = 0; i < heapSize; i++)
			System.out.print(heap[i] + " ");
		System.out.println();
	}

	public String findMax() {
		if (isEmpty())
			throw new NoSuchElementException("Heap is empty.");
		return heap[0];
	}
}