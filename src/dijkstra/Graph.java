package dijkstra;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	private HashMap<String, Vertex> nameMap;
	private HashMap<String, LinkedList<Vertex_Distance>> adjMap;

	/**
	 * 
	 */
	public Graph() {
		nameMap = new HashMap<>();
		adjMap = new HashMap<>();

	}

	public void addVertex(String name, double y_Latitude, double x_Longitude) {
		nameMap.put(name, new Vertex(name, y_Latitude, x_Longitude));
		adjMap.put(name, new LinkedList<>());
	}

	public void addAdjacent(String sourceVertex, String destinationVertex) {
		if (!adjMap.isEmpty()) {
			Vertex source = nameMap.get(sourceVertex);
			Vertex destination = nameMap.get(destinationVertex);
			double distance=calculateDistance(source, destination);
			adjMap.get(sourceVertex).insertSorted(new Vertex_Distance(destination,distance));
			adjMap.get(destinationVertex).insertSorted(new Vertex_Distance(source, distance));

		}
	}

	private double calculateDistance(Vertex sources, Vertex destinations) {
		return haversine(sources.getY_Latitude(), sources.getX_Longitude(), destinations.getY_Latitude(), destinations.getX_Longitude());
	}


    private double haversine(double source_Latitude, double source_Longitude, double dest_Latitude, double dest_Longitude) {
    	// Radius of the Earth in kilometers
    	final double R = 6371.0;
        // Convert latitude and longitude from degrees to radians
        source_Latitude = Math.toRadians(source_Latitude);
        source_Longitude = Math.toRadians(source_Longitude);
        dest_Latitude = Math.toRadians(dest_Latitude);
        dest_Longitude = Math.toRadians(dest_Longitude);

        // Compute the differences
        double totalLatitude = dest_Latitude - source_Latitude;
        double totalLongitude = dest_Longitude - source_Longitude;

        // Haversine formula
        double a = Math.sin(totalLatitude / 2) * Math.sin(totalLatitude / 2)
                + Math.cos(source_Latitude) * Math.cos(dest_Latitude)
                + Math.sin(totalLongitude / 2) * Math.sin(totalLongitude / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the distance
        double distance = R * c;

        return distance;
    }
	public Vertex getVertex (String vertexName) {
		return nameMap.get(vertexName);
	}
	public LinkedList<Vertex_Distance> getAdjacents(String vertexName) {
		return adjMap.get(vertexName);
	}
	public ArrayList<String> getVertexName(){
		ArrayList<String > list = new ArrayList<>(nameMap.keySet());
		return list;
	}

	/**
	 * @return the nameMap
	 */
	public HashMap<String, Vertex> getNameMap() {
		return nameMap;
	}

	
}
