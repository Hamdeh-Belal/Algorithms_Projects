/**
 * 
 */
package dijkstra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * @author Belal Hamdeh
 *
 */
public class Driver extends Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {

		Graph graph = new Graph();
		readFile(graph);
		FXMLLoader starting = new FXMLLoader(getClass().getResource("ScreenFX.fxml"));
		try {
			
			Scene scene = new Scene(starting.load());
			FXController controller = starting.getController();
			controller.setGraph(graph);
			stage.setScene(scene);
			stage.setTitle("Maps");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Open Error");
			alert.setContentText("Error:" + e.getMessage());
			e.printStackTrace();
		}

	}

	private boolean readFile(Graph graph) {

		try {
			File file = new File("C:\\BelalWorkSpace\\Algorithms_Course_Projects\\src\\dijkstra\\Capital_Adjecent.txt");
			if (!file.exists()) {
				return false;
			}
			Scanner input = new Scanner(file);
			String[] dataLine = null;
			int numVertex = -Integer.MAX_VALUE;
			int numEdges = -Integer.MAX_VALUE;

			if (input.hasNext()) {
				dataLine = input.nextLine().split(",");
				numVertex = Integer.parseInt(dataLine[0]);
				numEdges = Integer.parseInt(dataLine[1]);

				if (numEdges > 0 && numVertex > 0) {
					for (int i = 0; i < numVertex; i++) {

						dataLine = input.nextLine().split(",");

						if (dataLine.length != 3) {
							System.out.println(" Error Capital has problem !");
							break;
						}

						String name = dataLine[0].trim();
						double y_Latitude = Double.parseDouble(dataLine[1].trim());
						double x_Longitude = Double.parseDouble(dataLine[2].trim());

						graph.addVertex(name, y_Latitude, x_Longitude);
					}
					for (int i = 0; i < numEdges; i++) {

						dataLine = input.nextLine().split(",");

						if (dataLine.length > 2) {
							System.out.println(" Error Capital Adj  has problem !");
							break;
						}

						String sourceName = dataLine[0].trim();
						String destinationName = dataLine[1].trim();
						graph.addAdjacent(sourceName, destinationName);

					}
				}

			}
			input.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
}
