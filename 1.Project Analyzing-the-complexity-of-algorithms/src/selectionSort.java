import java.util.ArrayList;

public class selectionSort {
	public static void doSelectionSort(ArrayList<Double> arr, ArrayList<String> rFile, String isSave) {
		int N = arr.size();
		for (int i = 0; i < N - 1; i++) {
			int min = i;
			for (int j = i + 1; j < N; j++) {
				if (arr.get(j) < arr.get(min))
					min = j;
			}
			double swap = arr.get(min);
			arr.set(min, arr.get(i));
			arr.set(i, swap);

			if (isSave.equals("T")) {
				String swap1 = rFile.get(min);
				rFile.set(min, rFile.get(i));
				rFile.set(i, swap1);
			}
		}
	}
}
