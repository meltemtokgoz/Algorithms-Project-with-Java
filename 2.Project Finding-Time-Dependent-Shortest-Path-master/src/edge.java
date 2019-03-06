
public class edge {

	vertex source;
	vertex destination;
	boolean isActive;
	boolean isBroken;
	double distance;
	double timeDis;

	public edge() {

	}

	public edge(vertex a, vertex b, boolean c, boolean d, double e) {
		this.source = a;
		this.destination = b;
		this.isActive = c;
		this.isBroken = d;
		this.distance = e;
	}

	public int from() {
		return source.number;
	}

	public int to() {
		return destination.number;
	}

	public double weight() {
		return timeDis;
	}
}
