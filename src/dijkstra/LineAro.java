package dijkstra;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class LineAro {
	/*
	 * this class has the arrow objects to delete them after use another image n
	 */
	private Polygon poly;
	private Line line;
	/**
	 * @return the poly
	 */
	public Polygon getPoly() {
		return poly;
	}
	/**
	 * @param poly the poly to set
	 */
	public void setPoly(Polygon poly) {
		this.poly = poly;
	}
	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}
	/**
	 * @param line the line to set
	 */
	public void setLine(Line line) {
		this.line = line;
	}
	/**
	 * @param poly
	 * @param line
	 */
	public LineAro(Polygon poly, Line line) {
		super();
		this.poly = poly;
		this.line = line;
	}
	
}
