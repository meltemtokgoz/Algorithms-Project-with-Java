import java.util.ArrayList;
class Heap<E extends Comparable<? super E>> {
	static final int INIT_CAP = 100;
	ArrayList<E> list;

	public Heap() {
		list = new ArrayList<E>(INIT_CAP);
	}
	public int size() {
		return list.size();
	}
	public boolean isEmpty() {
		return list.isEmpty();
	}
	public void insert(E element) {
		list.add(element);
		swim();
	}
	private void swap(int parent, int child) {
		E tmp = list.get(parent);
		list.set(parent, list.get(child));
		list.set(child, tmp);
	}

	public E getMin() {
		return list.get(0);
	}

	public E removeMin() {
		E minElem = list.get(0);
		list.set(0, list.get(list.size() - 1)); 
		list.remove(list.size() - 1); 
		if (!list.isEmpty())
			sink(0); 
		return minElem;
	}

	private void swim() {
		int child = list.size() - 1;
		int parent;
		while (child > 0) {
			parent = (child - 1) / 2;
			if (list.get(child).compareTo(list.get(parent)) >= 0)
				break;
			swap(parent, child);
			child = parent;
		}
	}
	
	private void sink(int start) {
		if (start < 0 || start >= list.size())
			throw new RuntimeException("start < 0 or >= n");
		int parent = start;
		int child = 2 * parent + 1;
		while (child < list.size()) {
			if (child + 1 < list.size() && list.get(child).compareTo(list.get(child + 1)) > 0)
				child++; 
			if (list.get(child).compareTo(list.get(parent)) >= 0)
				break; 
			swap(parent, child);
			parent = child;
			child = 2 * parent + 1;
		}
	}

}