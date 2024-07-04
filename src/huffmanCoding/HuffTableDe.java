/**
 * 
 */
package huffmanCoding;

/**
 * @author Belal Hamdeh This class for the table in HuffmanCode
 */
public class HuffTableDe implements Comparable<HuffTableDe> {
	private Byte data;
	private String huffmanCode;
	

	/**
	 * @param data
	 * @param huffmanCode
	 * @param freq
	 */
	public HuffTableDe(Byte data, String huffmanCode) {
		super();
		this.data = data;
		this.huffmanCode = huffmanCode;
		
	}
	public HuffTableDe(Byte data) {
		super();
		this.data = data;
		
		
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
	public void setData(Byte data) {
		this.data = data;
	}

	/**
	 * @return the huffmanCode
	 */
	public String getHuffmanCode() {
		return huffmanCode;
	}

	/**
	 * @param huffmanCode the huffmanCode to set
	 */
	public void setHuffmanCode(String huffmanCode) {
		this.huffmanCode = huffmanCode;
	}

	@Override
	public int compareTo(HuffTableDe o) {
		if (this.data > o.getData())
			return 1;
		else if (this.data < o.getData())
			return -1;
		return 0;
	}

}
