import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class PrimMST {
	public static <V> void Prim(CreateGraph<V> G, V root, String cFileName, int numCluster) throws IOException {
		HashMap<V, Double> edgeWeightMST = new HashMap<V, Double>();
		HashMap<V, V> connectedEdgeMST = new HashMap<V, V>();
		Heap<Edge<V, Double>> priq = new Heap<Edge<V, Double>>();
		HashSet<V> allVertex = new HashSet<V>();

		for (V wrtx : G.vertices()) {
			edgeWeightMST.put(wrtx, Double.MAX_VALUE);
			connectedEdgeMST.put(wrtx, null);
			connectedEdgeMST.remove("father");
		}
		edgeWeightMST.put(root, 0.0);
		priq.insert(new Edge<V, Double>(root, 0.0));
		while (!priq.isEmpty()) {
			Edge<V, Double> pair = priq.removeMin();
			V u = pair.getVertex();
			if (!allVertex.contains(u)) {
				allVertex.add(u);
				for (Edge<V, Double> tup : G.adjacentTo(u)) {
					V v = tup.getVertex();
					Double weit = tup.getWeighted();
					if (!allVertex.contains(v) && edgeWeightMST.get(v) > weit) {
						edgeWeightMST.put(v, weit);
						connectedEdgeMST.put(v, u);
						priq.insert(new Edge<V, Double>(v, weit));
					}
				}
			}
		}

		/*System.out.println("\nMinimum-cost spanning tree 	The root: " + root);
		double totalmin_cost = 0.0;
		for (V w : G.vertices())
			if (!w.equals(root) && connectedEdgeMST.get(w) != null) {
				System.out.println(connectedEdgeMST.get(w) + "->" + w + " cost: " + edgeWeightMST.get(w));
				totalmin_cost += edgeWeightMST.get(w);
			}
		System.out.println("Minimum cost of the tree: " + totalmin_cost);*/
		Cluster.ClusterMST(G, root, connectedEdgeMST, edgeWeightMST, cFileName, numCluster);

	}

}
