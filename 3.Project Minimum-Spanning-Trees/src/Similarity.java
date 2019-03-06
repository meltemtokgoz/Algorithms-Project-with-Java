import java.util.ArrayList;

public class Similarity {
	public static void cosSim(ArrayList<String> wordVecList, ArrayList<String> wordVecList2,
			ArrayList<String> pairSimList, ArrayList<String> pairsList) {

		for (String word : wordVecList) {
			String wordItem[] = word.split(" ");
			int y = wordItem.length;
			for (String in : pairsList) {
				String pairsNum[] = in.split("-");
				if ((pairsNum[0].equals(wordItem[0].substring(1, wordItem[0].length() - 1)))) {
					for (String word2 : wordVecList2) {
						String wordItem2[] = word2.split(" ");
						if (pairsNum[1].equals(wordItem2[0].substring(1, wordItem2[0].length() - 1))) {
							double total = 0.0;
							double total1 = 0.0;
							double total2 = 0.0;
							for (int x = 1; x < y; x++) {
								double number1 = Double.parseDouble(wordItem[x]);
								double number2 = Double.parseDouble(wordItem2[x]);
								double a = (number1 * number2);
								total = total + a;
								double b = Math.pow(number1, 2);
								total1 = total1 + b;
								double c = Math.pow(number2, 2);
								total2 = total2 + c;
							}
							double result = total / (Math.sqrt(total1) * Math.sqrt(total2));
							if (result < 0) {
								result = 1 - result;
							}
							String res = String.valueOf(result);
							pairSimList.add(wordItem[0].substring(1, wordItem[0].length() - 1) + ","
									+ wordItem2[0].substring(1, wordItem2[0].length() - 1) + "," + res);
						}

					}
				}

			}

		}

	}
}
