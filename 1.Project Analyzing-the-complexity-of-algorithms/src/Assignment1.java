import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment1 {

	public static void main(String[] args) throws IOException {
		ArrayList<Double> sortArray = new ArrayList<Double>();
		ArrayList<String> fileArray = new ArrayList<String>();

		String fName = args[0];
		String featureIndex = args[1];
		int workIndex = (Integer.parseInt(featureIndex)-1);
		String isSave = args[2];

		Scanner f1 = new Scanner(new File(fName));
		String x = f1.nextLine();
		while (f1.hasNextLine()) {
			String row = f1.nextLine();
			fileArray.add(row);
			String[] split = row.split(",");
			String j_i = split[workIndex];
			double j = Double.parseDouble(j_i);
			sortArray.add(j);
		}
		f1.close();

		double start0 = System.currentTimeMillis();
		heapSort.doHeapSort(sortArray, fileArray, isSave);
		double stop0 = System.currentTimeMillis();
		double time = ((stop0 - start0) / 1000);
		System.out.println(fName + " j = " + (workIndex+1) + " Execution time : " + (time) + "  second");
		
		

		/* IMPORTANT NOTE
		 * 
		 * I did not measure time of the all algorithms because you said that you would run a
		 * single one. But if you want to look at one of my two algorithms, you can
		 * write it instead of heapSort. 
		 * 
		 */
		
		selectionSort.doSelectionSort(sortArray,fileArray, isSave);
		quickSort.doQuickSort(sortArray,fileArray,isSave);

		if (isSave.equals("T")) {
			BufferedWriter f2 = new BufferedWriter(new FileWriter(new File(fName)));
			f2.write(x);
			for (String eachLine : fileArray) {
				f2.newLine();
				f2.write(eachLine);
			}
			f2.close();
		}
	}
}
