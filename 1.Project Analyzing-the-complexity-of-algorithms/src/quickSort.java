import java.util.ArrayList;

public class quickSort {

	public static void doQuickSort(ArrayList<Double> arr, ArrayList<String> rFile, String isSave) {
		int s = arr.size();
		QuickSort(arr, rFile, 0, s - 1, isSave);
	}

	public static void QuickSort(ArrayList<Double> arr, ArrayList<String> rFile, int lo, int hi, String isSave) {

		int i = lo;
		int j = hi;
		double pivot = arr.get(lo + (hi - lo) / 2);

		while (i <= j) {
			while (arr.get(i) < pivot) {
				i++;
			}
			while (arr.get(j) > pivot) {
				j--;
			}
			if (i <= j) {
				double temp = arr.get(i);
				arr.set(i, arr.get(j));
				arr.set(j, temp);

				if (isSave.equals("T")) {
					String temp2 = rFile.get(i);
					rFile.set(i, rFile.get(j));
					rFile.set(j, temp2);
				}
				i++;
				j--;
			}
		}

		if (lo < j)
			QuickSort(arr, rFile, lo, j, isSave);
		if (i < hi)
			QuickSort(arr, rFile, i, hi, isSave);
	}
}
