import java.util.ArrayList;
import java.util.Collections;

public class function {

	public static void maintain(String x, ArrayList<vertex> vertexList, doGraph Graph) {
		for (vertex v : vertexList) {
			if (v.name.equals(x)) {
				v.underMaintenance = true;
				System.out.println("COMMAND IN PROCESS >> MAINTAIN " + v.name);
				System.out.println("	Command \"MAINTAIN " + v.name + "\"  has been executed successfully!");
			}
		}
	}

	public static void service(String x, ArrayList<vertex> vertexList, doGraph Graph) {
		for (vertex v : vertexList) {
			if (v.name.equals(x)) {
				v.underMaintenance = false;
				System.out.println("COMMAND IN PROCESS >> SERVICE " + v.name);
				System.out.println("	Command \"SERVICE " + v.name + "\"  has been executed successfully!");
			}
		}
	}

	public static void bre(String x, String y, ArrayList<vertex> vertexList, doGraph Graph) {
		for (vertex v : vertexList) {
			if (v.name.equals(x)) {
				for (edge edg : Graph.adjList.get(v)) {
					if (edg.destination.name.equals(y)) {
						edg.isBroken = true;
						System.out.println("COMMAND IN PROCESS >> BREAK " + x + ">" + y);
						System.out
								.println("	Command \"BREAK " + x + ">" + y + "\"  has been executed successfully!");
					}
				}
			}
		}
	}

	public static void repair(String x, String y, ArrayList<vertex> vertexList, doGraph Graph) {
		for (vertex v : vertexList) {
			if (v.name.equals(x)) {
				for (edge edg : Graph.adjList.get(v)) {
					if (edg.destination.name.equals(y)) {
						edg.isBroken = false;
						System.out.println("COMMAND IN PROCESS >> REPAIR " + x + ">" + y);
						System.out
								.println("	Command \"REPAIR " + x + ">" + y + "\"  has been executed successfully!");
					}
				}
			}
		}
	}

	public static void add(String x, ArrayList<vertex> vertexList, doGraph Graph) {
		vertex a = new vertex(x, false, 0);
		vertexList.add(a);
		Graph.adjList.put(a, new ArrayList<edge>());
		System.out.println("COMMAND IN PROCESS >> ADD " + x);
		System.out.println("	Command \"ADD " + x + "\"  has been executed successfully!");
	}

	public static void link(String x, String y, double z, String t, ArrayList<vertex> vertexList, doGraph Graph) {
		for (vertex v : vertexList) {
			if (v.name.equals(x)) {
				for (vertex w : vertexList) {
					if (w.name.equals(y)) {
						if (y.equals(t)) {
							Graph.adjList.get(v).add(new edge(v, w, true, false, z));
							Graph.adjList.get(w).add(new edge(w, v, false, false, z));
						} else {
							Graph.adjList.get(v).add(new edge(v, w, false, false, z));
							Graph.adjList.get(w).add(new edge(w, v, false, false, z));
						}
					}
				}
			}
		}
	}

	public static void route(String x, String y, int z, ArrayList<vertex> vertexList, doGraph Graph, int timeChange) {
		calculateTime.newTime(vertexList, Graph, z, timeChange);
		for (vertex v : vertexList) {
			if (v.name.equals(x)) {
				for (vertex w : vertexList) {
					if (w.name.equals(y)) {
						if (v.underMaintenance == false && w.underMaintenance == false) {
							int total = 0;
							for (vertex vr : Graph.adjList.keySet()) {
								vr.number = total;
								total = total + 1;
							}
							int a = 0;
							for (vertex vr : vertexList) {
								vr.number = a;
								a = a + 1;
							}
							dijkstra sp = new dijkstra(v, w, z, vertexList, Graph, total);
							if (!sp.hasPathTo(w.number)) {
								System.out.println("COMMAND IN PROCESS >> ROUTE " + x + ">" + y + " " + z);
								System.out.println("	No route from " + x + " to " + y + " found currently!");
								System.out.println("	Command \"ROUTE " + x + ">" + y + " " + z
										+ "\"  has been executed successfully!");
							} else {

								calculateTime.time(sp, v, w, vertexList, Graph, z, timeChange);

							}

						} else {
							System.out.println("COMMAND IN PROCESS >> ROUTE " + x + ">" + y + " " + z);
							System.out.println("	No route from " + x + " to " + y + " found currently!");
							System.out.println("	Command \"ROUTE " + x + ">" + y + " " + z
									+ "\"  has been executed successfully!");
						}

					}
				}
			}
		}
	}

	public static void listRoutesFrom(String x, ArrayList<vertex> vertexList, doGraph Graph) {
		String l = "";
		ArrayList<String> order = new ArrayList<>();
		for (vertex v : vertexList) {
			if (v.name.equals(x)) {
				for (edge edg : Graph.adjList.get(v)) {
					order.add(edg.destination.name);
				}
			}
		}
		Collections.sort(order);
		for (int p = 0; p < order.size(); p++) {
			if (p == order.size() - 1) {
				l += order.get(p);
			} else {
				l += order.get(p) + " ";
			}
		}
		System.out.println("COMMAND IN PROCESS >> LISTROUTESFROM " + x);
		System.out.println("	Routes from " + x + ": " + l);
		System.out.println("	Command \"LISTROUTESFROM " + x + "\"  has been executed successfully!");

	}

	public static void listMaintains(ArrayList<vertex> vertexList, doGraph Graph) {
		String l = "";
		ArrayList<String> order = new ArrayList<>();
		for (vertex v : vertexList) {
			if (v.underMaintenance == true) {
				order.add(v.name);
			}
		}
		Collections.sort(order);
		for (int p = 0; p < order.size(); p++) {
			if (p == order.size() - 1) {
				l += order.get(p);
			} else {
				l += order.get(p) + " ";
			}
		}
		System.out.println("COMMAND IN PROCESS >> LISTMAINTAINS");
		System.out.println("	Intersections under maintenance: " + l);
		System.out.println("	Command \"LISTMAINTAINS\"  has been executed successfully!");

	}

	public static void listActiveRails(ArrayList<vertex> vertexList, doGraph Graph) {
		String l = "";
		ArrayList<String> order = new ArrayList<>();
		for (vertex v : vertexList) {
			for (edge edg : Graph.adjList.get(v)) {
				if (edg.isActive == true) {
					order.add(edg.source.name + ">" + edg.destination.name);
				}
			}
		}
		Collections.sort(order);
		for (int p = 0; p < order.size(); p++) {
			if (p == order.size() - 1) {
				l += order.get(p);
			} else {
				l += order.get(p) + " ";
			}
		}
		System.out.println("COMMAND IN PROCESS >> LISTACTIVERAILS");
		System.out.println("	Active Rails: " + l);
		System.out.println("	Command \"LISTACTIVERAILS\"  has been executed successfully!");

	}

	public static void listBrokenRails(ArrayList<vertex> vertexList, doGraph Graph) {
		String l = "";
		ArrayList<String> order = new ArrayList<>();
		for (vertex v : vertexList) {
			for (edge edg : Graph.adjList.get(v)) {
				if (edg.isBroken == true) {
					order.add(edg.source.name + ">" + edg.destination.name);
				}
			}
		}
		Collections.sort(order);
		for (int p = 0; p < order.size(); p++) {
			if (p == order.size() - 1) {
				l += order.get(p);
			} else {
				l += order.get(p) + " ";
			}
		}
		System.out.println("COMMAND IN PROCESS >> LISTBROKENRAILS");
		System.out.println("	Broken rails: " + l);
		System.out.println("	Command \"LISTBROKENRAILS\"  has been executed successfully!");

	}

	public static void listCrossTimes(ArrayList<vertex> vertexList, doGraph Graph) {
		String l = "";
		ArrayList<String> order = new ArrayList<>();
		for (vertex v : vertexList) {
			if (v.totalPasses > 0) {
				order.add(v.name + ":" + v.totalPasses);
			}
		}
		Collections.sort(order);
		for (int p = 0; p < order.size(); p++) {
			if (p == order.size() - 1) {
				l += order.get(p);
			} else {
				l += order.get(p) + " ";
			}
		}
		System.out.println("COMMAND IN PROCESS >> LISTCROSSTIMES");
		System.out.println("	# of cross times: " + l);
		System.out.println("	Command \"LISTCROSSTIMES\"  has been executed successfully!");

	}

	public static void totalNumberOfJunctions(ArrayList<vertex> vertexList) {
		int totalVertex = 0;
		for (vertex v : vertexList) {
			totalVertex += 1;
		}
		System.out.println("COMMAND IN PROCESS >> TOTALNUMBEROFJUNCTIONS");
		System.out.println("	Total # of junctions: " + totalVertex);
		System.out.println("	Command \"TOTALNUMBEROFJUNCTIONS\"  has been executed successfully!");

	}

	public static void totalNumberOfRails(ArrayList<vertex> vertexList, doGraph Graph) {
		int totalRails = 0;
		for (vertex v : vertexList) {
			for (edge edg : Graph.adjList.get(v)) {
				totalRails += 1;

			}
		}
		System.out.println("COMMAND IN PROCESS >> TOTALNUMBEROFRAILS");
		System.out.println("	Total # of rails: " + totalRails);
		System.out.println("	Command \"TOTALNUMBEROFRAILS\"  has been executed successfully!");
	}

	public static void dummyCommand(String x) {
		System.out.println("COMMAND IN PROCESS >> " + x);
		System.out.println("	Unrecognized command \"" + x + "\"!");

	}
}
