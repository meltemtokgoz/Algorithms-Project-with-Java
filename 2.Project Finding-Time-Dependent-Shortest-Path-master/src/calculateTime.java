import java.util.ArrayList;
import java.util.Collections;

public class calculateTime {
	public static void newTime(ArrayList<vertex> vertexList, doGraph Graph, int velocity, int c) {
		for (vertex v : vertexList) {
			for (edge e : Graph.adjList.get(v)) {
				if (e.isActive == true) {
					double newDis = (e.distance * 60) / velocity;
					e.timeDis = newDis;
				} else if (e.isActive == false) {
					double newDis = c + ((e.distance * 60) / velocity);
					e.timeDis = newDis;
				}
			}
		}
	}

	public static void updateActiveEdge(edge e, ArrayList<vertex> vertexList, doGraph Graph, int timeChange) {
		if (e.isActive == true) {
			e.isActive = true;
		}
		if (e.isActive == false) {
			for (edge k : Graph.adjList.get(e.source)) {
				if (k.isActive == true) {
					k.isActive = false;
				}
			}
			e.isActive = true;
		}
	}

	public static void updatePasses(ArrayList<String> nodeList, ArrayList<vertex> vertexList) {
		for (String s : nodeList) {
			for (vertex v : vertexList) {
				if (v.name.equals(s)) {
					v.totalPasses += 1;
				}
			}
		}
	}

	public static void time(dijkstra sp, vertex v, vertex w, ArrayList<vertex> vertexList, doGraph Graph, int z,
			int timeChange) {

		String l = "";
		ArrayList<String> reverseList = new ArrayList<>();
		for (edge e : sp.pathTo(w.number)) {
			reverseList.add(e.source.name);
		}
		Collections.reverse(reverseList);
		reverseList.add(w.name);
		for (int p = 0; p < reverseList.size(); p++) {
			if (p == reverseList.size() - 1) {
				l += reverseList.get(p);
			} else {
				l += reverseList.get(p) + " ";
			}
		}
		int totalSwitch = 0;
		for (edge e : sp.pathTo(w.number)) {
			if (e.isActive == false) {
				totalSwitch += 1;
			}
		}
		double t = sp.distTo(w.number);
		System.out.println("COMMAND IN PROCESS >> ROUTE " + v.name + ">" + w.name + " " + z);
		System.out.printf("	Time (in min): %.3f", t);
		System.out.println();
		System.out.println("	Total # of switch changes: " + totalSwitch);
		System.out.println("	Route from " + v.name + " to " + w.name + ": " + l);
		System.out.println(
				"	Command \"ROUTE " + v.name + ">" + w.name + " " + z + "\"  has been executed successfully!");
		for (edge e : sp.pathTo(w.number)) {
			updateActiveEdge(e, vertexList, Graph, timeChange);
		}
		updatePasses(reverseList, vertexList);
	}
}
