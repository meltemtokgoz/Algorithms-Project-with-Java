import java.util.ArrayList;
import java.util.Stack;

public class dijkstra {
	static edge[] edgeTo;
	static double[] distTo;
	static IndexMinPQ<Double> pq;

	public dijkstra(vertex x, vertex y, int z, ArrayList<vertex> vertexList, doGraph Graph, int total) {
		edgeTo = new edge[total];
		distTo = new double[total];
		pq = new IndexMinPQ<Double>(total);

		for (int v = 0; v < total; v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[x.number] = 0.0;

		pq.insert(x.number, 0.0);
		while (!pq.isEmpty()) {
			int v = pq.delMin();

			for (vertex vr : Graph.adjList.keySet()) {
				if (vr.number == v && vr.underMaintenance == false) {
					for (edge e : Graph.adjList.get(vr)) {
						if (e.isBroken == false) {
							relax(e);
						}
					}
				}
			}
		}
	}

	private static void relax(edge e) {
		int v = e.from(), w = e.to();
		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if (pq.contains(w))
				pq.decreaseKey(w, distTo[w]);
			else
				pq.insert(w, distTo[w]);
		}
	}

	public double distTo(int v) {
		validateVertex(v);
		return distTo[v];
	}

	public boolean hasPathTo(int v) {
		validateVertex(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	public Iterable<edge> pathTo(int v) {
		validateVertex(v);
		if (!hasPathTo(v))
			return null;
		Stack<edge> path = new Stack<edge>();
		for (edge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}

	private void validateVertex(int v) {
		int V = distTo.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}
}
