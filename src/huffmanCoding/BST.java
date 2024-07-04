
package huffmanCoding;


public class BST<T extends Comparable<T>> {

	private TNode root;
//	private String postOrderString;

	public void inOrderTrav() {
		inOrderTrav(root);
	}

	private void inOrderTrav(TNode node) {
		if (node != null) {
			inOrderTrav(node.getLeft());
			System.out.println(node);
			inOrderTrav(node.getRight());
		}
	}
	
	public TNode largest() {
		return largest(root);
	}

	public TNode largest(TNode node) {
		if (node != null) {
			if (!node.hasRight())
				return (node);
			return largest(node.getRight());
		}
		return null;
	}

	public String findHuffmanCode(Byte data) {
		StringBuilder stringBuild = new StringBuilder();
		dfs(root, data, "", stringBuild);
		return stringBuild.toString();
	}
	
	private void dfs(TNode node, Byte data, String path, StringBuilder stringBuild) {
		if (node == null) {
			return; // Target frequency not found
		}

		if (node.getData() != null && node.getData().equals(data)&& node.isLeaf()) {
			if (!path.isEmpty()) {
				stringBuild.append(path);
				
				return;
			}
			// Don't return here, as there might be multiple nodes with the same frequency
		}

		// Recursively search left subtree
		if (node.hasLeft()) {

			dfs(node.getLeft(), data, path + "0", stringBuild);
			if (!stringBuild.isEmpty())
				return;
		}

		// Recursively search right subtree
		if (node.hasRight()) {

			dfs(node.getRight(), data, path + "1", stringBuild);
			if (!stringBuild.isEmpty())
				return;
		}
	}

	public TNode smallest() {
		return smallest(root);
	}

	public TNode smallest(TNode node) {
		if (node != null) {
			if (!node.hasLeft())
				return (node);
			return smallest(node.getLeft());
		}
		return null;
	}

	public int height() {
		return height(root);
	}

	private int height(TNode node) {
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 1;
		int left = 0;
		int right = 0;
		if (node.hasLeft())
			left = height(node.getLeft());
		if (node.hasRight())
			right = height(node.getRight());
		return (left > right) ? (left + 1) : (right + 1);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(TNode node) {
		if (isEmpty())
			root = node;
		else
			insert(node, root);
	}

	private void insert(TNode Nnode, TNode node) {
		if (Nnode.compareTo(node) >= 0) { // insert into right subtree
			if (!node.hasRight())
				node.setRight(Nnode);
			else
				insert(Nnode, node.getRight());
		} else { // insert into left subtree
			if (!node.hasLeft())
				node.setLeft(Nnode);
			else
				insert(Nnode, node.getLeft());
		}
	}

	public TNode delete(TNode data) {
		TNode current = root;
		TNode parent = root;
		boolean isLeftChild = false;
		if (isEmpty())
			return null; // tree is empty
		while (current != null && !current.equals(data)) {
			parent = current;
			if (data.compareTo(current) < 0) {
				current = current.getLeft();
				isLeftChild = true;
			} else {
				current = current.getRight();
				isLeftChild = false;
			}
		}
		if (current == null)
			return null; // node to be deleted not found
		// case 1: node is a leaf
		if (!current.hasLeft() && !current.hasRight()) {
			if (current == root) // tree has one node
				root = null;
			else {
				if (isLeftChild)
					parent.setLeft(null);
				else
					parent.setRight(null);
			}
		}
		// other cases
		return current;
	}

	public int size() {
		return size(root);
	}

	private int size(TNode node) {
		if (node == null)
			return 0;
		int leftSize = size(node.getLeft());
		int rightSize = size(node.getRight());
		return 1 + leftSize + rightSize;

	}

	public int countLeaves() {
		return countLeaves(root);

	}

	private int countLeaves(TNode node) {
		if (node == null)
			return 0;
		if (node.getLeft() == null && node.getRight() == null)
			return 1;
		return countLeaves(node.getLeft()) + countLeaves(node.getRight());

	}

	/**
	 * @return the root
	 */
	public TNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(TNode root) {
		this.root = root;
	}

	public String postOrderTrav() {
		StringBuilder stringBuilder = new StringBuilder();
		postOrderTrav(root, stringBuilder);
		return stringBuilder.toString();
	}

	private void postOrderTrav(TNode node, StringBuilder stringBuilder) {
		if (node != null) {
			postOrderTrav(node.getLeft(), stringBuilder);
			postOrderTrav(node.getRight(), stringBuilder);
			if (node.isLeaf()) {
				String asciiBinary = Integer.toBinaryString(node.getData()& 0xFF); //we can remove &FF without change output
				// Ensure 8-bit representation
				asciiBinary = String.format("%8s", asciiBinary).replace(' ', '0');
				stringBuilder.append("1" + asciiBinary);

			} else if(!node.isLeaf())
				stringBuilder.append("0");
		}
		return;
	}

}
