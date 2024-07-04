/**
 * 
 */
package huffmanCoding;

/**
 * @author Belal Hamdeh
 *	This class is a class for Huffman tree node
 *	the class has a boolean leaf to see if this node is leaf or not by defult it is not
 *	also has the data in it as byte and left and right
 *
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
	private boolean leaf;
	private byte data;
	private int freq;
	
	/**
	 * 
	 */
	public HuffmanNode() {
		super();
		setLeaf(false);
		
	}
	/**
	 * @param data
	 */
	public HuffmanNode(byte data,int freq) {
		super();
		setData(data);
		setLeaf(true);
		setFreq(freq);
		
	}
	/**
	 * @return the leaf
	 */
	public boolean isLeaf() {
		return leaf;
	}
	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	/**
	 * @return the data
	 */
	public byte getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(byte data) {
		this.data = data;
	}
	@Override
	public int compareTo(HuffmanNode o) {
	    if (this.freq < o.getFreq())
	        return -1;
	    else if (this.freq > o.getFreq())
	        return 1;
	    return 0;
	}
	@Override
	public String toString() {
		Byte b = data;
		return b.toString();
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

}
