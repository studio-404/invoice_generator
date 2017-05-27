package Layouts;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import functions.alertBox;
import config.variables;
import Layouts.welcomeLayout;

public class LoginLayout extends Application{
	
	
	public static void loginWindow(String[] args){
		launch(args);
	}

	public void start(Stage window) throws Exception {
		
		window.setTitle(variables.PROJECT_NAME + " :: სისტემაში შესვლა");
		window.setWidth(450);
		window.setHeight(300);		
		
		ImageView logo = new ImageView();
		final Image image2 = new Image("file:resources/logo.png");
		logo.setImage(image2);
		
		Label username = new Label("მომხმარებლის სახელი:");
		Label password = new Label("პაროლი:");
		
		TextField userText = new TextField();
		PasswordField userPassword = new PasswordField();
		
		Button submitButton = new Button("შესვლა");
		submitButton.setId("mySubmitButton");
		
		submitButton.setOnAction(e -> {
			String messageTitle = variables.PROJECT_NAME + " :: Message";
			String inputUser = userText.getText().trim();
			String inputPass = userPassword.getText().trim();			
			
			if(inputUser.isEmpty() || inputPass.isEmpty()){
				alertBox.showMe(messageTitle, "მომხმარებლის სახელი ან პაროლი ცარიელია !");
			}else if(!inputUser.equals(variables.ADMIN_USERNAME) || !inputPass.equals(variables.ADMIN_PASSWORD)){
				alertBox.showMe(messageTitle, "მომხმარებლის სახელი ან პაროლი არასწორია !"); 
			}else{
				window.close();
				welcomeLayout welcome = new welcomeLayout();
				welcome.mainWindow();
			}
		});
		
		GridPane grid = new GridPane();
		grid.setVgap(15);
		grid.setHgap(10);
		grid.add(username, 0, 0);
		grid.add(userText, 1, 0);
		grid.add(password, 0, 1);
		grid.add(userPassword, 1, 1);
		grid.add(submitButton, 1, 2);
		grid.setAlignment(Pos.CENTER);
		
		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(30,10,10,10));
		layout.setTop(logo);
		BorderPane.setAlignment(logo, Pos.CENTER);
		layout.setCenter(grid);
		BorderPane.setAlignment(grid, Pos.CENTER);

		
		layout.setStyle("-fx-background-color: #fffff1");
		
		Scene scene = new Scene(layout, 350, 250);
		scene.getStylesheets().add("file:resources/style.css");
		
		window.setScene(scene);
		window.resizableProperty().setValue(Boolean.FALSE);
		window.show();
	}
}
