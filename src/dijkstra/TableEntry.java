/**
 * 
 */
package dijkstra;

/**
 * this class has the array data obj use in dijkstra algortithms table
 */
public class TableEntry {
	private String V_Name;
	private boolean known;
	private double distType;
	private Vertex path;
	/**
	 * @param source_Vertex
	 * @param target_Vertex
	 * @param known
	 * @param distType
	 * @param path
	 */
	public TableEntry(String v_name, boolean known, double distType, Vertex path) {
		this.V_Name=v_name;
		this.known = known;
		this.distType = distType;
		this.path=path;
	}
	
	/**
	 * @return the source_Vertex
	 */
	public String getVertex_Name() {
		return this.V_Name;
	}
	/**
	 * @param source_Vertex the source_Vertex to set
	 */
	public void setVertex_Name(String Name_Vertex) {
		this.V_Name = Name_Vertex;
	}
	/**
	 * @return the known
	 */
	public boolean isKnown() {
		return known;
	}
	/**
	 * @param known the known to set
	 */
	public void setKnown(boolean known) {
		this.known = known;
	}
	/**
	 * @return the distType
	 */
	public double getDistType() {
		return distType;
	}
	/**
	 * @param distType the distType to set
	 */
	public void setDistType(double distType) {
		this.distType = distType;
	}

	/**
	 * @return the v_Name
	 */
	public String getV_Name() {
		return V_Name;
	}

	/**
	 * @param v_Name the v_Name to set
	 */
	public void setV_Name(String v_Name) {
		V_Name = v_Name;
	}

	/**
	 * @return the path
	 */
	public Vertex getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(Vertex path) {
		this.path = path;
	}
	

}
