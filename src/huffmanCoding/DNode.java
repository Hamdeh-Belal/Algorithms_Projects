/**
 * 
 */
package  huffmanCoding;


/**
 * @author Belal
 *
 */
public class DNode implements Comparable<DNode> {
	private DNode left;
	private boolean leaf;
	private DNode right;
	private Byte data;


	/**
	 * @param data
	 */
	public DNode(Byte data) {
		super();
		setData(data);
		setLeaf(true);
		
	}
	public DNode () {
		super();
		setLeaf(false);
	}


	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	/**
	 * @return the left
	 */
	public DNode getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(DNode left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public DNode getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(DNode right) {
		this.right = right;
	}

	/**
	 * @return the data
	 */
	public Byte getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(byte data) {
		this.data = data;
	}

	public boolean isLeaf() {
//		return ((left == null) && (right == null));
		return leaf;
	}

	@Override
	public String toString() {
		return "[ "+data + " ]";
	}

	public boolean hasLeft() {
		return (left != null);
	}

	public boolean hasRight() {
		return (right != null);
	}

	@Override
	public int compareTo(DNode o) {
		 if (this.data < o.getData())
		        return -1;
		    else if (this.data > o.getData())
		        return 1;
		    return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DNode))
			return false;
		DNode other = (DNode) obj;
		return data == other.getData();
	}
	
}
