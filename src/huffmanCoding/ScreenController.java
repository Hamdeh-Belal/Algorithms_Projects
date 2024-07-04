package huffmanCoding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * @author Belal Hamdeh
 *
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ScreenController {

	@FXML
	private Tab compTab;

	@FXML
	private Tab compTab1;

	@FXML
	private Pane dropPane;

	@FXML
	private Pane dropPane_De;

	@FXML
	private Pane exploerPane;

	@FXML
	private Pane exploerPane_De;

	@FXML
	private Button headerBt;

	@FXML
	private Button headerBt_De;

	@FXML
	private Label headerTxt;

	@FXML
	private Label headerTxt_De;

	@FXML
	private Label infoImage;

//	@FXML
//	private Label infoImage_De;

	@FXML
	private ImageView startImage;

	@FXML
	private ImageView startImage_De;

	@FXML
	private ImageView statImage;

	@FXML
	private ImageView statImage_De;

	@FXML
	private Button tableBt;

	@FXML
	private Button tableBt_De;

	@FXML
	private TableView<String> tableView;

	@FXML
	private TableView<String> tableView_De;

	HuffmanCodeComp compHuff;
	File compFile;
	HuffmanCodeDeComp deCompHuff;
	File deCompFile;

	@FXML
	void DropFile(DragEvent event) {
		if (event.getGestureSource() != dropPane && event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

		}
		event.consume();
	}

	@FXML
	void DropFileFinal(DragEvent event) {
		statImage.setImage(
				new Image("C:\\BelalWorkSpace\\Algorithms_Course_Projects\\src\\huffmanCoding\\redImage.png"));
		tableView.setVisible(false);
		headerTxt.setVisible(false);
		tableBt.setVisible(false);
		headerBt.setVisible(false);
		Dragboard dragboard = event.getDragboard();
		boolean success = false;
		File compDropFile = null;
		if (dragboard.hasFiles()) {
			List<File> files = dragboard.getFiles();
			StringBuilder stringBuilder = new StringBuilder();
			for (File file : files) {
				stringBuilder.append(file.getAbsolutePath()).append("\n");
			}
			compDropFile = new File(stringBuilder.toString());

			success = true;
		}
		event.setDropCompleted(success);
		// event.consum() ==> mean that the event has been fully handled and should not
		// be processed any further by other event handlers or event targets.
		event.consume();
		compFile = compDropFile;

		statImage.setImage(
				new Image("C:\\BelalWorkSpace\\Algorithms_Course_Projects\\src\\huffmanCoding\\greenImage.png"));

	}

	@FXML
	void DropFile_De(DragEvent event) {
		if (event.getGestureSource() != dropPane && event.getDragboard().hasFiles()) {
			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

		}
		event.consume();
	}

	@FXML
	void DropFile_DeFinal(DragEvent events) {
		statImage_De.setImage(
				new Image("C:\\BelalWorkSpace\\Algorithms_Course_Projects\\src\\huffmanCoding\\redImage.png"));
		tableView_De.setVisible(false);
		headerTxt_De.setVisible(false);
		tableBt_De.setVisible(false);
		headerBt_De.setVisible(false);
		Dragboard dragboard = events.getDragboard();
		boolean success = false;
		File deCompDropFile = null;
		if (dragboard.hasFiles()) {
			List<File> files = dragboard.getFiles();
			StringBuilder stringBuilder = new StringBuilder();
			for (File file : files) {
				stringBuilder.append(file.getAbsolutePath()).append("\n");
			}
			deCompDropFile = new File(stringBuilder.toString());
			success = true;

		}
		events.setDropCompleted(success);
		// event.consum() ==> mean that the event has been fully handled and should not
		// be processed any further by other event handlers or event targets.
		events.consume();
		deCompFile = deCompDropFile;
		statImage_De.setImage(
				new Image("C:\\BelalWorkSpace\\Algorithms_Course_Projects\\src\\huffmanCoding\\greenImage.png"));

	}

	@FXML
	void ExplorFile(MouseEvent event) {
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
		if (selectedFile != null) {
			compFile = selectedFile;
			statImage.setImage(
					new Image("C:\\BelalWorkSpace\\Algorithms_Course_Projects\\src\\huffmanCoding\\greenImage.png"));

		}
	}

	@FXML
	void ExplorFile_De(MouseEvent event) {
		// Create a FileChooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");

		// Set an extension filter for .huf files
		fileChooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter("Huffman Compressed Files (*.huf)", "*.huf"));

		Stage stageCho = new Stage();

		// Show the file chooser dialog
		File selectedFile = fileChooser.showOpenDialog(stageCho);

		// Process the selected file
		if (selectedFile != null) {
			deCompFile = selectedFile;
			statImage_De.setImage(
					new Image("C:\\BelalWorkSpace\\Algorithms_Course_Projects\\src\\huffmanCoding\\greenImage.png"));
		}

	}

	@FXML
	void ShowHeader(ActionEvent event) {
		headerTxt.setVisible(true);
		if (compHuff != null) {
			headerTxt.setText(compHuff.getHeader());
		}

	}

	@FXML
	void ShowHeader_De(ActionEvent event) {
		headerTxt_De.setVisible(true);
		if (deCompHuff != null) {
			headerTxt_De.setText(deCompHuff.getHeader());
		}

	}

	@FXML
	void Strating(MouseEvent event) {
		compHuff = new HuffmanCodeComp(compFile);
		/*
		 * ArrayList<HuffTable> huffTables = compHuff.getTable(); for(int i
		 * =0;i<huffTables.size();i++) {
		 * System.out.println(huffTables.get(i).toString()); }
		 */
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("File Read Successful");
		alert.setContentText("The file has been compressed successfully.");
		alert.showAndWait();

		headerBt.setVisible(true);
		tableBt.setVisible(true);
		infoImage.setVisible(true);
		if (compHuff != null) {
			infoImage.setText(compHuff.getInfo());
		}
	}

	@FXML
	void Strating_De(MouseEvent event) {
//		headerBt_De.setVisible(true);
//		tableBt_De.setVisible(true);
//		infoImage_De.setVisible(true);

		deCompHuff = new HuffmanCodeDeComp(deCompFile);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("File Read Successful");
		alert.setContentText("The file has been Decompressed successfully.");
		alert.showAndWait();

//		if (deCompHuff != null) {
//			infoImage_De.setText(deCompHuff.getInfo());
//		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	void showTable(ActionEvent event) {
		tableView.setVisible(true);

		// Create columns for the TableView
		TableColumn<String, String> dataColumn = new TableColumn<>("Byte");
		TableColumn<String, String> huffmanCodeColumn = new TableColumn<>("Huffman Code");
		TableColumn<String, String> freqColumn = new TableColumn<>("Frequency");

		// Add columns to the TableView
		tableView.getColumns().clear();
		tableView.getColumns().addAll(dataColumn, huffmanCodeColumn, freqColumn);

		// Create sample data (replace this with your actual ArrayList<HuffTable>)
		ArrayList<HuffTable> huffTables = compHuff.getTable();

		// Convert HuffTable objects to strings and add them to the TableView
		ObservableList<String> tableData = FXCollections.observableArrayList();
		for (HuffTable huffTable : huffTables) {
			tableData.add(huffTable.getData() + "\t" + huffTable.getHuffmanCode() + "\t" + huffTable.getFreq());
		}
		tableView.setItems(tableData);

	}

	@FXML
	void showTable_De(ActionEvent event) {
		tableView_De.setVisible(true);
		// Create columns for the TableView
		TableColumn<String, String> dataColumn = new TableColumn<>("Byte");
		TableColumn<String, String> huffmanCodeColumn = new TableColumn<>("Huffman Code");

		// Add columns to the TableView
		tableView_De.getColumns().clear();
		tableView_De.getColumns().addAll(dataColumn, huffmanCodeColumn);

		// Create sample data (replace this with your actual ArrayList<HuffTable>)
		ArrayList<HuffTableDe> huffTables = deCompHuff.getTable();

		// Convert HuffTable objects to strings and add them to the TableView
		ObservableList<String> tableData = FXCollections.observableArrayList();
		for (HuffTableDe huffTable : huffTables) {
			tableData.add(huffTable.getData() + "\t" + huffTable.getHuffmanCode());
		}
		tableView_De.setItems(tableData);
	}

}
