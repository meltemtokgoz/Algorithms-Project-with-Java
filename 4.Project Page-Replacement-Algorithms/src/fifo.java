import java.io.BufferedWriter;
import java.io.IOException;

public class fifo {

	public static void fifoAlg(String algName, String pages[], int p, int f, BufferedWriter outFile)
			throws IOException {
		outFile.write("\nMemory " + f + "\n");
		outFile.write(algName + " Page Replacement" + "\n");
		outFile.write("Binary Search Tree" + "\n");

		int num = 0, pageHit = 0;
		String frame[] = new String[f];

		for (int i = 0; i < f; i++) {
			frame[i] = " ";
		}

		for (int i = 0; i < p; i++) {

			String page = pages[i];
			if (num == f)
				num = 0;
			int x = binarySearchTree.bstree(frame, page);
			if (x == -1) {
				frame[num] = page;
				num++;
				pageHit++;
				outFile.write("Page Fault");
			} else if (x != -1) {
				outFile.write("          ");
			}
			for (int k = 0; k < f; k++) {
				outFile.write(" " + frame[k]);
			}
			outFile.write("\n");
		}
		outFile.write(pageHit + "");
	}
}
