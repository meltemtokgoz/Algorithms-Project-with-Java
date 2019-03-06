import java.util.HashMap;
import java.util.HashSet;

public class CreateGraph<V> {
	HashMap<V, HashSet<Edge<V, Double>>> adjList;
	int edgeNumber;

	public CreateGraph() {
		adjList = new HashMap<V, HashSet<Edge<V, Double>>>();
	}

	public void addEdge(V v, V w, Double weight) {
		if (!hasEdge(v, w))
			edgeNumber++;
		if (!hasVertex(v))
			addVertex(v);
		adjList.get(v).add(new Edge<V, Double>(w, weight));
		if (!hasVertex(w))
			addVertex(w);
		adjList.get(w).add(new Edge<V, Double>(v, weight));
	}

	public void addVertex(V v) {
		if (!hasVertex(v))
			adjList.put(v, new HashSet<Edge<V, Double>>());
	}

	public Iterable<V> vertices() {
		return adjList.keySet();
	}

	public Iterable<Edge<V, Double>> adjacentTo(V vertex) {
		if (!hasVertex(vertex))
			return new HashSet<Edge<V, Double>>();
		else
			return adjList.get(vertex);
	}

	public boolean hasVertex(V v) {
		return adjList.containsKey(v);
	}

	public boolean hasEdge(V v, V w) {
		if (!hasVertex(v))
			return false;
		for (Edge<V, Double> e : adjList.get(v)) {
			if (w.equals(e.getVertex()))
				return true;
		}
		return false;
	}

	public String toString() {
		String adjListLine = "";
		for (V node : adjList.keySet()) {
			adjListLine += node + "-> ";
			for (Edge<V, Double> edg : adjList.get(node)) {
				adjListLine += "[" + edg.getVertex() + ", " + edg.getWeighted() + "] ";
			}
			adjListLine += "\n";
		}
		return adjListLine;
	}
}