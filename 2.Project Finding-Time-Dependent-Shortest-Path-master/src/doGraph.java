import java.util.ArrayList;
import java.util.HashMap;

public class doGraph {
	HashMap<vertex, ArrayList<edge>> adjList;
	int edgeNumber;

	public doGraph() {
		adjList = new HashMap<vertex, ArrayList<edge>>();
	}

	public void addEdge(vertex a, vertex b, boolean c) {
		if (!hasAdjVertex(a))
			addVertex(a);
		if (!hasAdjVertex(b))
			addVertex(b);
		adjList.get(a).add(new edge(a, b, c, false, 0.0));
	}

	public boolean hasAdjVertex(vertex v) {
		if (adjList.containsKey(v)) {
			return true;
		} else {
			return false;
		}
	}

	public void addVertex(vertex v) {
		if (!hasAdjVertex(v))
			adjList.put(v, new ArrayList<edge>());
	}

	public boolean hasEdge(vertex v, vertex w) {
		if (!hasAdjVertex(v))
			return false;
		for (edge e : adjList.get(v)) {
			if (w.name.equals(e.destination.name))
				return true;
		}
		return false;
	}

	public String toString() {
		String adjListLine = "";
		for (vertex node : adjList.keySet()) {
			adjListLine += node.name + ", " + node.underMaintenance + ", " + node.totalPasses + "-> ";
			for (edge edg : adjList.get(node)) {
				adjListLine += "[" + edg.destination.name + " , " + edg.distance + " , " + edg.isActive + " , "
						+ edg.isBroken + "] ";
			}
			adjListLine += "\n";
		}
		return adjListLine;
	}
}
