package dynamicProgramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ScreenFXController implements Initializable {

	@FXML
	private VBox altVBox;
	@FXML
	private Tab otherPathTab;
	
	@FXML
	private Label bestPathLabel;

	@FXML
	private ImageView startImage;
	@FXML
	private TableView<int[]> tableViewCost;
	@FXML
	private Tab costTab;
	@FXML
	private Tab DPTab;
	@FXML
	private TableView<int[]> tableViewDP;
	@FXML
	private Tab showFileTab;

	@FXML
	private TextArea textArea;
	private File file;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void strarting(MouseEvent event) {
		// Create a FileChooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");

		// Set a custom file filter
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Select files", "*"));

		// Exclude files with .huf extension
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Exclude .huf files", "*.*|*.huf"));
		Stage stageCho = new Stage();

		// Show the file chooser dialog
		File selectedFile = fileChooser.showOpenDialog(stageCho);

		// Process the selected file
		if (selectedFile == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("File Error");
			alert.setContentText("Reading file unsuccessfully.");
			alert.showAndWait();

		} else {
			try {
			this.file = selectedFile;
			appendFile();
			DynamicClass dynamic = new DynamicClass(file);
			fillCostTable(dynamic.getCostsTable(),dynamic.getNames());
			fillDpTable(dynamic.getDpTable(),dynamic.getNames());
			bestPathLabel.setText(dynamic.getOptimalpath());
			fillAltPathLablels(dynamic.getAltPath());
			}
			catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Runtime Error");
				alert.setContentText("Error:" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private void fillAltPathLablels(String[] altPath) {
		String style = "-fx-background-color :white ;-fx-border-color : black ;-fx-border-width : 3px";
		altVBox.setSpacing(15);
		if (altPath != null) {
			for (int i = 0; i < altPath.length; i++) {
				Label label = new Label(altPath[i]);
				label.setStyle(style);
				altVBox.getChildren().add(label);
			}
		}
	}

	private void appendFile() {
		if (file != null) {
			StringBuilder stringData = new StringBuilder();
			try {
				Scanner input = new Scanner(file);
				while (input.hasNext()) {
					stringData.append(input.nextLine() + "\n");
				}
				input.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			textArea.setText(stringData.toString());
			textArea.setEditable(false);
		}
	}

	private void fillDpTable(int[][] dpTable,String[]names) {
		if (dpTable != null) {
			// Add columns dynamically based on the array size
			for (int i = 0; i < dpTable[0].length; i++) {
				int columnIndex = i;
				
				TableColumn<int[], Integer> column = new TableColumn<>(names[i]);
				column.setCellValueFactory(cellData -> {
					int[] row = cellData.getValue();
					return new javafx.beans.property.SimpleObjectProperty<>(row[columnIndex]);
				});
				tableViewDP.getColumns().add(column);
			}

			// Add rows to the TableView
			for (int i = 0; i < dpTable.length; i++) {
				tableViewDP.getItems().add(dpTable[i]);
			}
		}
	}

	private void fillCostTable(int[][] costTable,String[]names) {
		if (costTable != null&&names!=null) {
			
			// Add columns dynamically based on the array size
			for (int i = 0; i < costTable[0].length; i++) {
				int columnIndex = i;
				TableColumn<int[], Integer> column = new TableColumn<>(names[i]);
				column.setCellValueFactory(cellData -> {
					int[] row = cellData.getValue();
					return new javafx.beans.property.SimpleObjectProperty<>(row[columnIndex]);
				});
				tableViewCost.getColumns().add(column);
			}

			// Add rows to the TableView
			for (int i = 0; i < costTable.length; i++) {
				tableViewCost.getItems().add(costTable[i]);
			}
		}
	}

}
