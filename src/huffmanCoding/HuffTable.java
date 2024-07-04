/**
 * 
 */
package huffmanCoding;

/**
 * @author Belal Hamdeh This class for the table in HuffmanCode
 */
public class HuffTable implements Comparable<HuffTable> {
	private Byte data;
	private String huffmanCode;
	private int freq;
	private int length;

	/**
	 * @param data
	 * @param huffmanCode
	 * @param freq
	 */
	public HuffTable(Byte data, String huffmanCode, int freq) {
		super();
		this.data = data;
		this.huffmanCode = huffmanCode;
		this.freq = freq;
	}
	public HuffTable(Byte data, String huffmanCode) {
		super();
		this.data = data;
		this.huffmanCode = huffmanCode;
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
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public int compareTo(HuffTable o) {
		if (this.data > o.getData())
			return 1;
		else if (this.data < o.getData())
			return -1;
		return 0;
	}

	@Override
	public String toString() {
		return "HuffTable [data=" + data + ", huffmanCode=" + huffmanCode + ", freq=" + freq + "]";
	}

}
