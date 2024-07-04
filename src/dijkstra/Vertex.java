/**
 * 
 */
package dijkstra;

/**
 * @author Belal Hamdeh
 * This class has the name and eclidean data for vertex (Capital)
 */
public class Vertex implements Comparable<Vertex>{
	private String Name ;
	private double X_Longitude;
	private double Y_Latitude;
	
	/**
	 * @param name
	 * @param x_Longitude
	 * @param y_Latitude
	 */
	public Vertex(String name, double y_Latitude, double x_Longitude) {
		super();
		Name = name;
		X_Longitude = x_Longitude;
		Y_Latitude = y_Latitude;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the x_Longitude
	 */
	public double getX_Longitude() {
		return X_Longitude;
	}
	/**
	 * @param x_Longitude the x_Longitude to set
	 */
	public void setX_Longitude(double x_Longitude) {
		X_Longitude = x_Longitude;
	}
	/**
	 * @return the y_Latitude
	 */
	public double getY_Latitude() {
		return Y_Latitude;
	}
	/**
	 * @param y_Latitude the y_Latitude to set
	 */
	public void setY_Latitude(double y_Latitude) {
		Y_Latitude = y_Latitude;
	}

	@Override
	public String toString() {
		return   Name ;
	}

	@Override
	public int compareTo(Vertex o) {
		return getName().compareTo(o.getName());
	}
	

}
