import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class readInputFile {
	private static BufferedReader br0;

	public static void readIn(String network, String distances, String commands, int timeChange) throws IOException {

		doGraph Graph = new doGraph();
		ArrayList<String> vertexNameList = new ArrayList<>();
		ArrayList<vertex> vertexList = new ArrayList<>();

		FileReader in0 = new FileReader(network);
		br0 = new BufferedReader(in0);
		String line0;
		while ((line0 = br0.readLine()) != null) {
			String[] value = line0.split(">");
			String valueSour[] = value[0].split(":");
			if (vertexNameList.contains(valueSour[0]) == false) {
				vertex a = new vertex(valueSour[0], false, 0);
				vertexNameList.add(valueSour[0]);
				vertexList.add(a);
			}
		}
		FileReader in1 = new FileReader(network);
		br0 = new BufferedReader(in1);
		String line1;
		while ((line1 = br0.readLine()) != null) {
			String[] value = line1.split(">");
			String valueSour[] = value[0].split(":");
			vertex a = null;
			for (vertex x : vertexList) {
				if (x.name.equals(valueSour[0])) {
					a = x;
				}
			}
			vertex b = null;
			String valueDest[] = valueSour[1].split(",");
			for (int i = 0; i < valueDest.length; i++) {
				for (vertex y : vertexList) {
					if (valueDest[i].equals(y.name)) {
						b = y;
						if (value[1].equals(b.name)) {
							Graph.addEdge(a, b, true);
						} else {
							Graph.addEdge(a, b, false);
						}

					}
				}
			}
		}
		FileReader in2 = new FileReader(distances);
		br0 = new BufferedReader(in2);
		String line2;
		while ((line2 = br0.readLine()) != null) {
			String[] dis = line2.split(" ");
			double cost = Double.parseDouble(dis[1]);
			String[] disVertex = dis[0].split("-");
			for (vertex v1 : vertexList) {
				if (disVertex[0].equals(v1.name)) {
					for (edge e : Graph.adjList.get(v1)) {
						if (e.destination.name.equals(disVertex[1])) {
							e.distance = cost;
						}
					}
				}
				if (disVertex[1].equals(v1.name)) {
					for (edge e : Graph.adjList.get(v1)) {
						if (e.destination.name.equals(disVertex[0])) {
							e.distance = cost;
						}
					}
				}
			}
		}

		FileReader in3 = new FileReader(commands);
		br0 = new BufferedReader(in3);
		String line3;
		while ((line3 = br0.readLine()) != null) {
			String[] lineCom = line3.split(" ");
			if (lineCom[0].equals("MAINTAIN")) {
				function.maintain(lineCom[1], vertexList, Graph);
			} else if (lineCom[0].equals("SERVICE")) {
				function.service(lineCom[1], vertexList, Graph);
			} else if (lineCom[0].equals("BREAK")) {
				String[] item = lineCom[1].split(">");
				function.bre(item[0], item[1], vertexList, Graph);
			} else if (lineCom[0].equals("REPAIR")) {
				String[] item = lineCom[1].split(">");
				function.repair(item[0], item[1], vertexList, Graph);
			} else if (lineCom[0].equals("ADD")) {
				function.add(lineCom[1], vertexList, Graph);
			} else if (lineCom[0].equals("LINK")) {
				String[] item = lineCom[1].split(">");
				String[] sourItem = item[0].split(":");
				String[] destItem = sourItem[1].split(",");
				String l = sourItem[0] + ":";
				for (int k = 0; k < destItem.length; k++) {
					String[] vertex = destItem[k].split("-");
					double dist = Double.parseDouble(vertex[1]);
					function.link(sourItem[0], vertex[0], dist, item[1], vertexList, Graph);
					int f = (int) dist;
					if (k == destItem.length - 1)
						l += vertex[0] + "-" + f;
					else
						l += vertex[0] + "-" + f + ",";
				}
				System.out.println("COMMAND IN PROCESS >> LINK " + l + ">" + item[1]);
				System.out.println("	Command \"LINK " + l + ">" + item[1] + "\"  has been executed successfully!");
			} else if (lineCom[0].equals("ROUTE")) {
				int velocity = Integer.parseInt(lineCom[2]);
				String[] item = lineCom[1].split(">");
				function.route(item[0], item[1], velocity, vertexList, Graph, timeChange);
			} else if (lineCom[0].equals("LISTROUTESFROM")) {
				function.listRoutesFrom(lineCom[1], vertexList, Graph);
			} else if (lineCom[0].equals("LISTMAINTAINS")) {
				function.listMaintains(vertexList, Graph);
			} else if (lineCom[0].equals("LISTACTIVERAILS")) {
				function.listActiveRails(vertexList, Graph);
			} else if (lineCom[0].equals("LISTBROKENRAILS")) {
				function.listBrokenRails(vertexList, Graph);
			} else if (lineCom[0].equals("LISTCROSSTIMES")) {
				function.listCrossTimes(vertexList, Graph);
			} else if (lineCom[0].equals("TOTALNUMBEROFJUNCTIONS")) {
				function.totalNumberOfJunctions(vertexList);
			} else if (lineCom[0].equals("TOTALNUMBEROFRAILS")) {
				function.totalNumberOfRails(vertexList, Graph);
			} else {
				function.dummyCommand(lineCom[0]);
			}
		}
	}
}
