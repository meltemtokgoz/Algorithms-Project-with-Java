public class binarySearchTree {

	public static int bstree(String frame[], String key) {
		binarySearchTree tree = new binarySearchTree();
		tree.root = null;
		for (int t = 0; t < frame.length; t++) {
			tree.put(frame[t]);
		}
		if (tree.get(key).equals("false")) {
			return -1;

		} else
			return 0;
	}

	public class Node {
		String key;
		Node left, right;

		public Node(String item) {
			key = item;
			left = right = null;
		}
	}

	binarySearchTree() {
		root = null;
	}

	Node root;

	public void put(String key) {
		root = insertRec(root, key);
	}

	public Node insertRec(Node root, String key) {

		if (root == null) {
			root = new Node(key);
			return root;
		}

		int cmp = key.compareTo(root.key);
		if (cmp < 0)
			root.left = insertRec(root.left, key);
		else if (cmp > 0)
			root.right = insertRec(root.right, key);
		return root;
	}

	public String get(String key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else if (cmp == 0)
				return x.key;
		}
		return "false";
	}

	public static int findIndex(String frame[], String key) {
		for (int i = 0; i < frame.length; i++) {
			if (frame[i].equals(key)) {
				return i;
			}
		}
		return -1;
	}

}