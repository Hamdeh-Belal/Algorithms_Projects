/**
 * 
 */
package dijkstra;

/**
 * This class for Graph LinkedList Node Data contain The vertex and the Distance
 * between source and this vertex
 */
public class Vertex_Distance implements Comparable<Vertex_Distance> {
	private Vertex vertex;
	private double distance;

	/**
	 * @return the vertex
	 */
	public Vertex getVertex() {
		return vertex;
	}

	/**
	 * @param vertex the vertex to set
	 */
	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		if (distance < 0)
			this.distance = 0;
		else
			this.distance = distance;
	}

	/**
	 * @param vertex
	 * @param distance
	 */
	public Vertex_Distance(Vertex vertex, double distance) {
		
		this.vertex = vertex;
		this.distance = distance;
	}

	@Override
	public int compareTo(Vertex_Distance o) {
		return getVertex().compareTo(o.getVertex());
	}

}
