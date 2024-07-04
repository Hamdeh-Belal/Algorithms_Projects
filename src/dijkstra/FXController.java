package dijkstra;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class FXController implements Initializable {
	// constants 
	private static final double Window_Max_X = 1000.0;
	private static final double Window_Max_Y = 570.0;
	private static final double Real_Min_X = -130.00841812115695;
	private static final double Real_Max_X = 153.87828308616503;
	private static final double Real_Min_Y = 69.6747178217249;
	private static final double Real_Max_Y = -53.51014265616152;
	private static final double Window_Min_X = 0.0;
	private static final double Window_Min_Y = 0.0;
	  @FXML
	    private Button clearBT;
	@FXML
	private Pane imagePane;
	private ArrayList<LineAro> listLine;
	@FXML
	private TextArea distanceTextArea;

	@FXML
	private TextArea pathTextArea;

	@FXML
	private ImageView runImage;
	
	@FXML
	private ComboBox<String> sourceCombo;
	private boolean sourceUse=false;

	@FXML
	private ComboBox<String> targetCombo;
	private boolean targetUse=false;
	private Graph graph;

	@FXML
	void RunNow(MouseEvent event) {
		sourceUse=false;
		targetUse=false;
		if(listLine!=null&&listLine.size()>0) {
			for(int i =0;i<listLine.size();i++) {
				imagePane.getChildren().remove(listLine.get(i).getLine());
				imagePane.getChildren().remove(listLine.get(i).getPoly());
			}
		
			listLine= new ArrayList<>();
			pathTextArea.setText("");
			distanceTextArea.setText("");
		}
		if(sourceCombo.getValue()!=null&& targetCombo.getValue()!=null) {
		DijkstraAlgorithm dj = new DijkstraAlgorithm(graph, sourceCombo.getValue(), targetCombo.getValue(),imagePane);
		
		distanceTextArea.setText( String.format("%.2f", dj.getCost()));
		pathTextArea.setText(dj.getPath());
		listLine= new ArrayList<>();
		dj.drowPathLine(targetCombo.getValue(),listLine);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pathTextArea.setEditable(false);
		distanceTextArea.setEditable(false);

	}

	private void fillCombo() {
		if (graph != null) {
			sourceCombo.getItems().addAll(graph.getVertexName());
			targetCombo.getItems().addAll(graph.getVertexName());
			
		}
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
		fillCombo();
		setPoints();

	}

	private void setPoints() {
		ArrayList<String> points = new ArrayList<>(graph.getVertexName());
		for (int i = 0; i < points.size(); i++) {
			double latitude = graph.getNameMap().get(points.get(i)).getY_Latitude();
			double longitude = graph.getNameMap().get(points.get(i)).getX_Longitude();

			double center_X = getX(longitude);

			double center_Y = getY(latitude);

			Circle circle = new Circle(center_X, center_Y, 4);

			circle.setFill(Color.RED);
			Text text = new Text(center_X - 3, center_Y + 15, graph.getNameMap().get(points.get(i)).getName());
			circle.setOnMouseClicked(e->{
				if(!sourceUse) {
					sourceCombo.setValue(text.getText());
					sourceUse=true;
				}
				else if(sourceUse&&!targetUse) {
					targetCombo.setValue(text.getText());
					targetUse=true;
				}
			});
			imagePane.getChildren().addAll(circle, text);

		}
	}
	// find the y to the map image accroding to the read data and window data 
	public static double getY(double latitude) {
		if (latitude > 20 && latitude < 57) {
			return (((latitude - 12.5 - Real_Min_Y) * (Window_Max_Y - Window_Min_Y)) / (Real_Max_Y - Real_Min_Y))
					- Window_Min_Y;
		}
		if (latitude > 0 && latitude < 20) {
			return (((latitude - 8 - Real_Min_Y) * (Window_Max_Y - Window_Min_Y)) / (Real_Max_Y - Real_Min_Y))
					- Window_Min_Y;
		}
		if (latitude > 57 && latitude < 70) {
			return (((latitude - 5 - Real_Min_Y) * (Window_Max_Y - Window_Min_Y)) / (Real_Max_Y - Real_Min_Y))
					- Window_Min_Y;
		}
		return (((latitude - Real_Min_Y) * (Window_Max_Y - Window_Min_Y)) / (Real_Max_Y - Real_Min_Y)) - Window_Min_Y;

	}
	// find x to the map image
	public static double getX(double longitude) {

		return ((Window_Max_X * longitude - Window_Max_X * Real_Min_X)
				- (Window_Min_X * longitude - Window_Min_X * Real_Max_X)
				+ (Window_Min_X * Real_Max_X - Window_Min_X * Real_Min_X)) / (Real_Max_X - Real_Min_X);
	}
	   @FXML
	    void clear(ActionEvent event) {
		   sourceUse=false;
			targetUse=false;
			if(listLine!=null&&listLine.size()>0) {
				for(int i =0;i<listLine.size();i++) {
					imagePane.getChildren().remove(listLine.get(i).getLine());
					imagePane.getChildren().remove(listLine.get(i).getPoly());
				}
			
				listLine= new ArrayList<>();
				pathTextArea.setText("");
				distanceTextArea.setText("");
				sourceCombo.setValue(null);
				targetCombo.setValue(null);
			}
	    }
}
