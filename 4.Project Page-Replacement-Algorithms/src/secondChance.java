import java.io.BufferedWriter;
import java.io.IOException;

public class secondChance {

	public static void secondChanceAlg(String algName, String pages[], int p, int f, BufferedWriter outFile)
			throws IOException {
		outFile.write("\nMemory " + f + "\n");
		outFile.write(algName + " Page Replacement" + "\n");
		outFile.write("Binary Search Tree" + "\n");

		int num = 0, pageHit = 0;

		String frame[] = new String[f];
		int bit[] = new int[f];

		for (int i = 0; i < f; i++) {
			frame[i] = " ";
			bit[i] = 0;
		}

		for (int i = 0; i < p; i++) {

			String page = pages[i];

			if (num == f)
				num = 0;

			String temp = " ";
			String temp2 = " ";
			int x = binarySearchTree.bstree(frame, page);
			if (x == -1) {
				outFile.write("Page Fault  ");
				if (bit[num] == 1) {
					temp = "Second Chance";
					while (bit[num] == 1) {
						temp2 += frame[num];
						temp2 += " ";
						bit[num] = 0;
						num++;
						if (num == f)
							num = 0;
					}
				}

				if (bit[num] != 1) {
					frame[num] = page;
					num++;
					pageHit++;
				}

			} else if (x != -1) {
				outFile.write("            ");
				int index = binarySearchTree.findIndex(frame, page);
				bit[index] = 1;
			}

			for (int k = 0; k < f; k++) {
				outFile.write(frame[k] + " ");
			}
			outFile.write(temp + " " + temp2);

			outFile.write("\n");

		}
		outFile.write(pageHit + "");
	}

}
