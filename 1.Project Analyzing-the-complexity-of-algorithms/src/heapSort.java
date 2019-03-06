import java.util.ArrayList;

public class heapSort {

	public static void doHeapSort(ArrayList<Double> arr, ArrayList<String> rFile, String isSave) {
		int N = arr.size();
		for (int i = N / 2 - 1; i >= 0; i--)
			heapify(arr, rFile, N, i, isSave);

		for (int i = N - 1; i >= 0; i--) {
			double swap = arr.get(0);
			arr.set(0, arr.get(i));
			arr.set(i, swap);

			if (isSave.equals("T")) {
				String swap2 = rFile.get(0);
				rFile.set(0, rFile.get(i));
				rFile.set(i, swap2);
			}

			heapify(arr, rFile, i, 0, isSave);
		}
	}

	public static void heapify(ArrayList<Double> arr, ArrayList<String> rFile, int n, int i, String isSave) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		if (l < n && arr.get(l) > arr.get(largest))
			largest = l;
		if (r < n && arr.get(r) > arr.get(largest))
			largest = r;
		if (largest != i) {

			double swap3 = arr.get(i);
			arr.set(i, arr.get(largest));
			arr.set(largest, swap3);

			if (isSave.equals("T")) {
				String swap4 = rFile.get(i);
				rFile.set(i, rFile.get(largest));
				rFile.set(largest, swap4);
			}

			heapify(arr, rFile, n, largest, isSave);
		}
	}
}
