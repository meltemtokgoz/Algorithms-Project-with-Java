import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment3 {
	private static BufferedReader br1;
	private static BufferedReader br0;

	public static void main(String[] args) throws IOException {
		String wvFileName = args[0] + ".txt";
		String wpFileName = args[1] + ".txt";
		String cFileName = args[2] + ".txt";
		String numberCluster = args[3];
		int numCluster = Integer.parseInt(numberCluster);
		
		ArrayList<String> pairsList = new ArrayList<>();
		ArrayList<String> wordVecList = new ArrayList<>();
		ArrayList<String> wordVecList2 = new ArrayList<>();
		ArrayList<String> pairSimList = new ArrayList<>();

		FileReader in0 = new FileReader(wpFileName);
		br0 = new BufferedReader(in0);
		String line0;
		while ((line0 = br0.readLine()) != null) {
			pairsList.add(line0);
		}
		FileReader in1 = new FileReader(wvFileName);
		br1 = new BufferedReader(in1);
		String line;
		while ((line = br1.readLine()) != null) {
			wordVecList.add(line);
			wordVecList2.add(line);
		}
		Similarity.cosSim(wordVecList, wordVecList2, pairSimList, pairsList);
		CreateGraph<String> G = new CreateGraph<String>();
		for (String sim : pairSimList) {
			String element[] = sim.split(",");
			double dNumber = Double.parseDouble(element[2]);
			G.addEdge(element[0], element[1], dNumber);
		}
		//System.out.println(G);
		PrimMST.Prim(G, "father",cFileName,numCluster);

	}
}
