import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment2 {
	public static void main(String[] args) throws IOException {

		ArrayList<String> charArray = new ArrayList<String>();
		String fileName = args[0];
		int n = 0, nFrames = 0;
		String algName = null;

		FileReader in = new FileReader(fileName);
		BufferedReader br = new BufferedReader(in);
		String line;
		while ((line = br.readLine()) != null) {
			String[] column = line.split(" ");
			if (column[0].equals("SetMemory")) {
				int a = Integer.parseInt(column[1]);
				nFrames = a;
			}
			if (column[0].equals("setReplacement")) {
				algName = column[1];
			}
			if (column[0].startsWith("Read")) {
				charArray.add(column[1]);
				n++;
			}
		}

		String[] pageString = new String[n];

		for (int i = 0; i < n; i++) {
			pageString[i] = charArray.get(i);
		}

		File f = new File("output.txt");
		FileWriter wrt = new FileWriter(f, true);
		BufferedWriter outFile = new BufferedWriter(wrt);

		if (algName.equals("FIFO")) {
			double start0 = System.currentTimeMillis();
			fifo.fifoAlg(algName, pageString, n, nFrames, outFile);
			double stop0 = System.currentTimeMillis();
			double time0 = ((stop0 - start0) / 1000);
			outFile.write("\nFIFO Algorithm Execution time : " + (time0) + "  second");
		}
		if (algName.equals("SecondChance")) {
			double start1 = System.currentTimeMillis();
			secondChance.secondChanceAlg(algName, pageString, n, nFrames, outFile);
			double stop1 = System.currentTimeMillis();
			double time1 = ((stop1 - start1) / 1000);
			outFile.write("\nSecond Chance Algorithm Execution time : " + (time1) + "  second");
		}
		if (algName.equals("PriorityQueue")) {
			double start2 = System.currentTimeMillis();
			priorityQueue.pQueue(algName, pageString, n, nFrames, outFile);
			double stop2 = System.currentTimeMillis();
			double time2 = ((stop2 - start2) / 1000);
			outFile.write("\nPriority Queue Algorithm Execution time : " + (time2) + "  second");
		}

		in.close();
		outFile.close();

	}
}
