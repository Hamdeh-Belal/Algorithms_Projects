/**
 * 
 */
package huffmanCoding;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;



public class Driver extends Application {

    @Override
    public void start(Stage stage) {
    	FXMLLoader starting = new FXMLLoader(getClass().getResource("Screen.fxml"));
		try {
			Scene scene = new Scene(starting.load());
			stage.setScene(scene);
			stage.setTitle("Huffman Code");
			stage.show();
			stage.setResizable(false);

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Open Error");
			alert.setContentText("Error:" + e.getMessage());
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        launch(args);
    }
}