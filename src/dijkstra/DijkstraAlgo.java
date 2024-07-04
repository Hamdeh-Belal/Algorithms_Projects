/**
 * 
 */
package dijkstra;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 * 
 */
public class DijkstraAlgo {
	private Graph graph;
	private String source;
	private String target;
	private TableEntry[] table;
	private HashMap<String, Integer> map;
	private Pane pane;

	/**
	 * @param graph
	 * initalize dijkstral using graph , source and target 
	 */
	public DijkstraAlgo(Graph graph, String Source, String Target, Pane pane) {
		this.graph = graph;
		this.source = Source;
		this.target = Target;
		this.pane = pane;
		map = new HashMap<>();
		// intialize table
		intializeTable();
		// serach of the target 
		Dijkstra();

	}

	private void intializeTable() {
		if (graph != null) {
			ArrayList<String> names = graph.getVertexName();
			table = new TableEntry[names.size()];
			for (int i = table.length - 1; i >= 0; i--) {
				if (names.get(i).equals(source)) {
					table[i] = new TableEntry(names.get(i), false, 0.0, null);
				} else {
					table[i] = new TableEntry(names.get(i), false, Double.MAX_VALUE, null);
				}
				map.put(names.get(i), i);
			}
		}

	}

	private void Dijkstra() { // O(V^2)
		if (table != null && graph != null) {
			Integer v = null;
			Integer w = null;
			while (true) {
				String vertexSmall = get_Smallest_Unkown_Distance(); //O(V)
				if (vertexSmall == null) {
					break;
				}
				v = map.get(vertexSmall);

				table[v].setKnown(true);
				// stop when we found the target
				if (vertexSmall.equals(target))
					break;
				// list has all adj of some vertex and the the distance between this vetex and all other adj
				ArrayList<Vertex_Distance> list = graph.getAdjacents(vertexSmall).toArrayList();
				for (int i = 0; i < list.size(); i++) {
					w = map.get(list.get(i).getVertex().getName());
					if (!table[w].isKnown()) {
						if ((table[v].getDistType() + list.get(i).getDistance()) < table[w].getDistType()) {

							table[w].setDistType(table[v].getDistType() + list.get(i).getDistance());

							table[w].setPath(graph.getVertex(table[v].getVertex_Name()));
						}
					}
				}
			}
		}
	}
	// find min unknown distance between all vertex 
	private String get_Smallest_Unkown_Distance() {
		String vertex = null;
		double min = Double.MAX_VALUE;
		for (int i = 0; i < table.length; i++) {
			if (!table[i].isKnown() && table[i].getDistType() < min) {
				min = table[i].getDistType();
				vertex = table[i].getVertex_Name();
			}
		}
		return vertex;
	}
	// get path recursively
	private void printPath(String vertex, StringBuilder str) {
		if (table[map.get(vertex)].getPath() != null) {
			printPath(table[map.get(vertex)].getPath().getName(), str);

			str.append(" -> ");

		}

		str.append(graph.getVertex(vertex));
	}

	public String getPath() {
		StringBuilder str = new StringBuilder();
		printPath(target, str);
		return str.toString();
	}

	/**
	 * @return the table
	 */
	public TableEntry[] getTable() {
		return table;
	}

	public double getCost() {
		return table[map.get(target)].getDistType();
	}

	private LineAro drawArrow(String sourceCapital, String targetCapital) {
		Vertex sourceV = graph.getVertex(sourceCapital);
		Vertex targetV = graph.getVertex(targetCapital);

		
		double startX = FXController.getX(targetV.getX_Longitude());
		double startY = FXController.getY(targetV.getY_Latitude());

		double endX = FXController.getX(sourceV.getX_Longitude());
		double endY = FXController.getY(sourceV.getY_Latitude());
		double arrowLength = 10;

		Point2D toPoint = new Point2D(endX, endY);

		// Create the main line
		Line line = new Line(startX, startY, endX, endY);
		line.setStroke(Color.BLACK);
		line.setStrokeWidth(1);

		// Calculate the angle of the line
		double angle = Math.atan2(endY - startY, endX - startX);

		// Create the arrowhead triangle
		Point2D arrowTip1 = toPoint.add(new Point2D(-arrowLength * Math.cos(angle - Math.PI / 6),
				-arrowLength * Math.sin(angle - Math.PI / 6)));
		Point2D arrowTip2 = toPoint.add(new Point2D(-arrowLength * Math.cos(angle + Math.PI / 6),
				-arrowLength * Math.sin(angle + Math.PI / 6)));

		Polygon arrowHead = new Polygon();
		arrowHead.getPoints().addAll(toPoint.getX(), toPoint.getY(), arrowTip1.getX(), arrowTip1.getY(),
				arrowTip2.getX(), arrowTip2.getY());
		arrowHead.setFill(Color.BLACK);

		pane.getChildren().addAll(line, arrowHead);
		return new LineAro(arrowHead, line);

	}

	public void drowPathLine(String vertex, ArrayList<LineAro> list) {
		if (table[map.get(vertex)].getPath() != null) {
			drowPathLine(table[map.get(vertex)].getPath().getName(), list);
			list.add(drawArrow(vertex, table[map.get(vertex)].getPath().getName()));

		}

	}

}
