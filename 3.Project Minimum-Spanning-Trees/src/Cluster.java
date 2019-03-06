import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Cluster<V> {

	private static final int ArrayList = 0;

	public static <V> void ClusterMST(CreateGraph<V> G, V root, HashMap<V, V> connectedEdgeMST,
			HashMap<V, Double> edgeWeightMST, String cFileName, int numCluster) throws IOException {
		ArrayList<String> c1 = new ArrayList<String>();
		ArrayList<String> c2 = new ArrayList<String>();

		for (int t = 0; t < numCluster - 1; t++) {
			Double minEdge = 1.0;
			V a = null;
			V b = null;
			for (V w : G.vertices())
				if (!w.equals(root) && connectedEdgeMST.get(w) != null) {
					if (edgeWeightMST.get(w) < minEdge) {
						minEdge = edgeWeightMST.get(w);
						b = connectedEdgeMST.get(w);
						a = w;
					}
				}
			//System.out.println("\nSmallest Edge : " + a + " " + b + " " + minEdge + "\n");

			for (V w : G.vertices()) {
				if (w.equals(a) && connectedEdgeMST.get(w).equals(b)) {
					connectedEdgeMST.remove(a);
					edgeWeightMST.remove(a);
				}
			}
			//System.out.println(connectedEdgeMST);
			printMap(connectedEdgeMST, a, b, G, numCluster, c1, c2, cFileName);

		}
	}

	public static <V> void printMap(Map mp, V a, V b, CreateGraph<V> G, int numCluster, ArrayList<String> c1,
			ArrayList<String> c2, String cFileName) throws IOException {

		Set<String> set = new HashSet<String>();
		ArrayList<V> h = new ArrayList<V>();
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getKey().equals(a) || pair.getValue().equals(a)) {
				h.add((V) pair.getKey());
				h.add((V) pair.getValue());
			}
		}
		for (V is : h) {
			for (V node : G.adjList.keySet()) {
				if (is.equals(node)) {
					for (Edge<V, Double> edg : G.adjList.get(node)) {
						if (!edg.getVertex().equals(b)) {
							String s = String.valueOf(edg.getVertex());
							set.add(s);
						}
					}
				}

			}
		}
		for (String f : set) {
			c1.add(f);
		}
		for (V d : G.vertices()) {
			if (!set.contains(d)) {
				String di = String.valueOf(d);
				c2.add(di);
			}

		}
		Collections.sort(c1);
		Collections.sort(c2);
		File f = new File(cFileName);
		FileWriter wrt = new FileWriter(f, true);
		BufferedWriter outFile = new BufferedWriter(wrt);	
		for(String k : c1) {
			//System.out.print(k+" ");
			outFile.write(k+" ");;
		}
		//System.out.print("\n");
		outFile.write("\n");
		for (String t : c2) {
			//System.out.print(t+" ");
			outFile.write(t+" ");
		}
		outFile.close();
		
	}

}
