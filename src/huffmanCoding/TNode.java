/**
 * 
 */
package  huffmanCoding;


/**
 * @author Belal
 *
 */
public class TNode implements Comparable<TNode> {
	private TNode left;
	private boolean leaf;
	private TNode right;
	private Byte data;
	private int freq;

	/**
	 * @param data
	 */
	public TNode(byte data,int freq) {
		super();
		setData(data);
		setLeaf(true);
		setFreq(freq);
	}
	public TNode () {
		super();
		setLeaf(false);
	}
	/**
	 * @return the freq
	 */
	public int getFreq() {
		return freq;
	}

	/**
	 * @param freq the freq to set
	 */
	public void setFreq(int freq) {
		this.freq = freq;
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
	public TNode getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(TNode left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public TNode getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(TNode right) {
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
	public int compareTo(TNode o) {
		 if (this.freq < o.getFreq())
		        return -1;
		    else if (this.freq > o.getFreq())
		        return 1;
		    return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TNode))
			return false;
		TNode other = (TNode) obj;
		return freq == other.freq;
	}
	
}
