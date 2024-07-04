package dynamicProgramming;

public class DataTable implements Comparable<DataTable> {
	private int data;
	private int index;
	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(int data) {
		this.data = data;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @param data
	 * @param index
	 */
	public DataTable(int data, int index) {
		super();
		this.data = data;
		this.index = index;
	}
	@Override
	public int compareTo(DataTable o) {
		if(getData()>o.getData())
			return 1;
		else if(getData()<o.getData())
			return -1;
		return 0;
	}
	@Override
	public String toString() {
		return " [" + data + " , " + index + "] ";
	}
	

}
