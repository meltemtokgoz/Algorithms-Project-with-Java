class Edge<V, C extends Comparable<? super C>> implements Comparable<Edge<V, C>> {
	private V vertices;
	private C totalweight;

	public Edge(V vertex, C weight) {
		this.vertices = vertex;
		this.totalweight = weight;
	}

	public V getVertex() {
		return vertices;
	}

	public C getWeighted() {
		return totalweight;
	}

	public int compareTo(Edge<V, C> other) {
		return totalweight.compareTo(other.getWeighted());
	}

}