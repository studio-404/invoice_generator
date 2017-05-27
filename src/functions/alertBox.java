package functions;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class alertBox{
	public static void showMe(String title, String message){
		Stage window = new Stage();
		
		window.setTitle(title);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMaxWidth(350);
		window.setMinHeight(150);
		
		
		Label label = new Label(message);
		label.setStyle("-fx-text-fill: #555555");
		label.setWrapText(true);
		
		VBox layout = new VBox();
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.getChildren().addAll(label);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #fffff1");
		
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("file:resources/style.css");
		
		window.setScene(scene);
		window.showAndWait();
	}
}

