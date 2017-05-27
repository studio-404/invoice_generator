package functions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.json.simple.JSONObject;

import config.variables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class addInvoice {
	private static StringConverter<LocalDate> converter;

	@SuppressWarnings("static-access")
	public static void showMe(){
		Stage window = new Stage();
		
		window.setTitle(variables.PROJECT_NAME + " :: ინვოისის დამატება");
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(400);		
		
		Label createDateLabel = new Label("შექმნის თარიღი:");
		createDateLabel.setStyle("-fx-text-fill: #555555");		
		
		converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = 
                DateTimeFormatter.ofPattern("yyyy-MM-dd");
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        }; 	
        
        DatePicker createDatePicker = new DatePicker();
        createDatePicker.setValue(LocalDate.now());
        createDatePicker.setConverter(converter);
		
		Label startDate = new Label("დაწყების თარიღი:");
		startDate.setStyle("-fx-text-fill: #555555");	
		
		DatePicker startDatePicker = new DatePicker();
		startDatePicker.setValue(LocalDate.now());
		startDatePicker.setConverter(converter);
		
		Label endDate = new Label("დასრულების  თარიღი:");
		endDate.setStyle("-fx-text-fill: #555555");	
		
		DatePicker endDatePicker = new DatePicker();
		endDatePicker.setValue(LocalDate.now());
		endDatePicker.setConverter(converter);
		
		Label userListLabel = new Label("აირჩიეთ მომხმარებელი:");
		userListLabel.setStyle("-fx-text-fill: #555555");
		webRequest jsonData = new webRequest();
		Iterator<?> theData = jsonData.json("http://hosting.404.ge/users/viewAll");     	
		ObservableList<String> options = FXCollections.observableArrayList();		
		while (theData.hasNext()) {
         	JSONObject objx = (JSONObject) theData.next();
         	String userId = objx.get("id").toString();
         	String userCompanyName = objx.get("companyName").toString();
         	String userPersonName = objx.get("personName").toString();
         	options.add(userId+"::"+userCompanyName+"::"+userPersonName);         	
         }
		ComboBox<String> usersList = new ComboBox<String>(options);
		
		Label descriptionLabel = new Label("აღწერა:");
		descriptionLabel.setStyle("-fx-text-fill: #555555");
		
		TextArea descriptionText = new TextArea();
		
		Label PriceLabel = new Label("ფასი:");
		PriceLabel.setStyle("-fx-text-fill: #555555");
		
		TextField priceText = new TextField();
		
		Label saleLabel = new Label("ფასდაკლება (%):");
		saleLabel.setStyle("-fx-text-fill: #555555");
		
		TextField saleText = new TextField();
		
		Button submitButton = new Button("დამატება");
		submitButton.setId("searchButton");
		
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(20));
		layout.getChildren().addAll(createDateLabel, createDatePicker, startDate, startDatePicker, endDate, endDatePicker, userListLabel, usersList, descriptionLabel, descriptionText, PriceLabel, priceText, saleLabel, saleText, submitButton);
		
		GridPane.setConstraints(createDateLabel, 0, 0);
		GridPane.setConstraints(createDatePicker, 1, 0);
		GridPane.setConstraints(startDate, 0, 1);
		GridPane.setConstraints(startDatePicker, 1, 1);
		GridPane.setConstraints(endDate, 0, 2);
		GridPane.setConstraints(endDatePicker, 1, 2);
		GridPane.setConstraints(userListLabel, 0, 3);
		GridPane.setConstraints(usersList, 1, 3);
		GridPane.setConstraints(descriptionLabel, 0, 4);
		GridPane.setConstraints(descriptionText, 1, 4);
		GridPane.setConstraints(PriceLabel, 0, 5);
		GridPane.setConstraints(priceText, 1, 5);
		GridPane.setConstraints(saleLabel, 0, 6);
		GridPane.setConstraints(saleText, 1, 6);
		GridPane.setConstraints(submitButton, 1, 7);
		
		layout.setAlignment(Pos.CENTER);
		layout.setMargin(createDateLabel, new Insets(5));
		layout.setMargin(createDatePicker, new Insets(5));
		layout.setMargin(startDate, new Insets(5));
		layout.setMargin(startDatePicker, new Insets(5));
		layout.setMargin(endDate, new Insets(5));
		layout.setMargin(endDatePicker, new Insets(5));
		layout.setMargin(userListLabel, new Insets(5));
		layout.setMargin(usersList, new Insets(5));
		layout.setMargin(descriptionLabel, new Insets(5));
		layout.setMargin(descriptionText, new Insets(5));
		layout.setMargin(PriceLabel, new Insets(5));
		layout.setMargin(priceText, new Insets(5));
		layout.setMargin(saleLabel, new Insets(5));
		layout.setMargin(saleText, new Insets(5));
		layout.setMargin(submitButton, new Insets(5));
		
		
		layout.setStyle("-fx-background-color: #fffff1");
		
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("file:resources/style.css");
		
		window.setScene(scene);
		window.showAndWait();
	}
}
