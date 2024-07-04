
package huffmanCoding;

import java.util.ArrayList;

public class BST_De<T extends Comparable<T>> {

	private DNode root;

	public void inOrderTrav() {
		inOrderTrav(root);
	}

	private void inOrderTrav(DNode node) {
		if (node != null) {
			inOrderTrav(node.getLeft());
			System.out.println(node);
			inOrderTrav(node.getRight());
		}
	}

	public String findHuffmanCode(Byte data) {
		StringBuilder stringBuild = new StringBuilder();
		dfs(root, data, "", stringBuild);
		return stringBuild.toString();
	}

	private void dfs(DNode node, Byte data, String path, StringBuilder stringBuild) {
		if (node == null) {
			return; // Target frequency not found
		}

		if (node.getData() != null && node.getData().equals(data)) {
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

	public DNode largest() {
		return largest(root);
	}

	public DNode largest(DNode node) {
		if (node != null) {
			if (!node.hasRight())
				return (node);
			return largest(node.getRight());
		}
		return null;
	}

	public DNode smallest() {
		return smallest(root);
	}

	public DNode smallest(DNode node) {
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

	private int height(DNode node) {
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

	public void insert(DNode node) {
		if (isEmpty())
			root = node;
		else
			insert(node, root);
	}

	private void insert(DNode Nnode, DNode node) {
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

	public DNode delete(DNode data) {
		DNode current = root;
		DNode parent = root;
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

	private int size(DNode node) {
		if (node == null)
			return 0;
		int leftSize = size(node.getLeft());
		int rightSize = size(node.getRight());
		return 1 + leftSize + rightSize;

	}

	public int countLeaves() {
		return countLeaves(root);

	}

	private int countLeaves(DNode node) {
		if (node == null)
			return 0;
		if (node.getLeft() == null && node.getRight() == null)
			return 1;
		return countLeaves(node.getLeft()) + countLeaves(node.getRight());

	}

	/**
	 * @return the root
	 */
	public DNode getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(DNode root) {
		this.root = root;
	}

	public String postOrderTrav() {
		StringBuilder stringBuilder = new StringBuilder();
		postOrderTrav(root, stringBuilder);
		return stringBuilder.toString();
	}

	private void postOrderTrav(DNode node, StringBuilder stringBuilder) {
		if (node != null) {
			postOrderTrav(node.getLeft(), stringBuilder);
			postOrderTrav(node.getRight(), stringBuilder);
			if (node.isLeaf()) {
				String asciiBinary = Integer.toBinaryString(node.getData() & 0xFF); // this method may by not 8 bit
				// Ensure 8-bit representation
				asciiBinary = String.format("%8s", asciiBinary).replace(' ', '0');
				stringBuilder.append("1" + asciiBinary);

			} else
				stringBuilder.append("0");
		}
		return;
	}

	public byte[] getAllData(String data) {
		ArrayList<Byte> arr = new ArrayList<>();
		getAllDataTrav(arr, data, 0);
		byte[] dataArr= new byte[arr.size()];
		for(int i =0;i<arr.size();i++) {
			dataArr[i]=arr.get(i);
		}
		return dataArr;

	}

	private void getAllDataTrav(ArrayList<Byte> arr, String data, int fIndex) {
		int counter=0;
		if (root != null) {
			DNode node = root;
			while (fIndex != data.length()) {
				if (node != null) {
					if (node.isLeaf()) {
						
						arr.add(node.getData());
						
						node = root;
						fIndex++;
						counter ++;
						continue;
					}
					else if (data.charAt(fIndex) == '0') {

						if (node.hasLeft()) {
							node = node.getLeft();
							fIndex++;
							continue;
						} else {
							System.out.println("-- we get 0 and don't get left ");
						}
						
					} else if (data.charAt(fIndex) == '1') {

						if (node.hasRight()) {
							node = node.getRight();
							fIndex++;
						} else {
							System.out.println("-- we get 1 and don't get right ");
						}
						
					}
				}
			}

		}

	}
}
