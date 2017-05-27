package Layouts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Iterator;
import org.json.simple.JSONObject;
import config.variables;
import functions.addInvoice;
import functions.icons;
import functions.invoiceView;
import functions.webRequest;

public class welcomeLayout{
	Stage window;
	public static TableView<invoices> table;
	public static BorderPane layout;
	
	@SuppressWarnings("static-access")
	public void mainWindow() {
		
		window = new Stage();
		window.setTitle(variables.PROJECT_NAME + " :: ინვოისები");
		window.setMinWidth(1200);
		window.setMinHeight(650);	
		
		layout = new BorderPane();
		
		/* Page Title */
		Label pageTitle = new Label("ინვოისები >> ნახვა");
		pageTitle.setId("pageTitle");
		pageTitle.setAlignment(Pos.TOP_LEFT);
		pageTitle.setPadding(new Insets(10,5,10,5));
		
		// invoice menu
		Menu invoices = new Menu("ინვოისები");
		MenuItem viewInvoice = new MenuItem("ნახვა");
		viewInvoice.setOnAction(e -> {
			invoiceView in = new invoiceView();
			in.loadTable(table, "viewAll", layout, "1");
			pageTitle.setText("ინვოისები >> ნახვა");
		});
		
		MenuItem addInvoice = new MenuItem("დამატება...");
		addInvoice.setOnAction(e ->{
			addInvoice add = new addInvoice();
			add.showMe();
		});
		MenuItem payedInvoice = new MenuItem("გადახდილი");
		payedInvoice.setOnAction(e -> {
			invoiceView in = new invoiceView();
			in.loadTable(table, "payed", layout, "1");
			pageTitle.setText("ინვოისები >> გადახდილი");
		});
		MenuItem unpayedInvoice = new MenuItem("გადასახდელი");
		unpayedInvoice.setOnAction(e -> {
			invoiceView in = new invoiceView();
			in.loadTable(table, "unpayed", layout, "1");
			pageTitle.setText("ინვოისები >> გადასახდელი");
		});
		invoices.getItems().addAll(viewInvoice, addInvoice, payedInvoice, unpayedInvoice);
		
		// user menu
		Menu serviceUsers = new Menu("მომხმარებლები");
		MenuItem viewUsers = new MenuItem("ნახვა");
		MenuItem addUser = new MenuItem("დამატება...");
		serviceUsers.getItems().addAll(viewUsers, addUser);
		
		// menu mar
		MenuBar menubar = new MenuBar();
		menubar.getMenus().addAll(invoices, serviceUsers);
		
		layout.setTop(menubar);
		
		
		
		/* Search */
		HBox searchLayout = new HBox();
		TextField search = new TextField();
		ObservableList<String> options = FXCollections.observableArrayList(
	        "ს/კ",
	        "მომხმარებლის სახელი"
	    );
		ComboBox<String> searchType = new ComboBox<>(options);
		searchType.setValue("ს/კ");
		
		Button searchButton = new Button("ძებნა");
		searchButton.setId("searchButton");
		searchLayout.setMargin(search, new Insets(0,5,10,5));
		searchLayout.setMargin(searchType, new Insets(0,5,10,5));
		searchLayout.setMargin(searchButton, new Insets(0,5,10,5));
		searchLayout.getChildren().addAll(search, searchType, searchButton);	
		
		/* TABLE */
		TableColumn<invoices, String> idColumn = new TableColumn<invoices, String>("ს/კ");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<invoices, String> createTimeColumn = new TableColumn<invoices, String>("შექმნის დრო");
		createTimeColumn.setCellValueFactory(new PropertyValueFactory<>("createTime"));
		
		TableColumn<invoices, String> serviceStartColumn = new TableColumn<invoices, String>("დაწყება");
		serviceStartColumn.setCellValueFactory(new PropertyValueFactory<>("serviceStart"));
		
		TableColumn<invoices, String> serviceEndColumn = new TableColumn<invoices, String>("დასრულება");
		serviceEndColumn.setCellValueFactory(new PropertyValueFactory<>("serviceEnd"));
		
		TableColumn<invoices, String> userNameColumn = new TableColumn<invoices, String>("მომხმარებლის სახელი");
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
		
		TableColumn<invoices, String> serviceDescriptionColumn = new TableColumn<invoices, String>("აღწერა");
		serviceDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("serviceDescription"));
		
		TableColumn<invoices, String> servicePriceColumn = new TableColumn<invoices, String>("ფასი");
		servicePriceColumn.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
		
		TableColumn<invoices, String> serviceDiscountColumn = new TableColumn<invoices, String>("ფასდაკლება (%)");
		serviceDiscountColumn.setCellValueFactory(new PropertyValueFactory<>("serviceDiscount"));
		
		TableColumn<invoices, String> payStatusColumn = new TableColumn<invoices, String>("სტატუსი");
		payStatusColumn.setCellValueFactory(new PropertyValueFactory<>("payStatus"));
		
		TableColumn<invoices, String> actionColumn = new TableColumn<invoices, String>("მოქმედება");
		actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
		
		
		table = new TableView<>();
		table.setItems(getProduct("viewAll", layout, "1"));
		table.getColumns().add(idColumn);
		table.getColumns().add(createTimeColumn);
		table.getColumns().add(serviceStartColumn);
		table.getColumns().add(serviceEndColumn);
		table.getColumns().add(userNameColumn);
		table.getColumns().add(serviceDescriptionColumn);
		table.getColumns().add(servicePriceColumn);
		table.getColumns().add(serviceDiscountColumn);
		table.getColumns().add(payStatusColumn);
		table.getColumns().add(actionColumn);
		
		table.setPadding(new Insets(0,0,0,0));
		
		VBox vLayout = new VBox();
		vLayout.getChildren().addAll(pageTitle, searchLayout, table);
		
		layout.setCenter(vLayout);
		
		Scene scene = new Scene(layout);
		scene.getStylesheets().add("file:resources/style.css"); 
		window.setScene(scene);
		window.resizableProperty().setValue(Boolean.FALSE);
		window.show();
	}
	
	@SuppressWarnings("static-access")
	public ObservableList<invoices> getProduct(String invoiceType, BorderPane layout, String p){
		ObservableList<invoices> invoices = FXCollections.observableArrayList();
		int fl = 1;
		int allCount = 1;
		// get Server Data
		webRequest jsonData = new webRequest();
		Iterator<?> theData = jsonData.json("http://hosting.404.ge/invoice/" + invoiceType + "/?p="+p);
     	
		while (theData.hasNext()) {
         	JSONObject objx = (JSONObject) theData.next();
         	ImageView image;
         	if(objx.get("emailStatus").toString().equals("1")){
         		image = icons.getIcon("resources/envelop_red.png");
         	}else{
         		image = icons.getIcon("resources/envelop_green.png");
         	}
    		ImageView image2 = icons.getIcon("resources/pdf.png");
    		ImageView image3 = icons.getIcon("resources/edit.png");
    		ImageView image4;
    		if(objx.get("payStatus").toString().equals("1")){
    			image4 = icons.getIcon("resources/credit_card_red.png");
    		}else{
    			image4 = icons.getIcon("resources/credit_card_green.png");
    		}
    		ImageView image5 = icons.getIcon("resources/remove.png");
    		
    		Tooltip.install(image, new Tooltip("წერილის მიწერა"));
    		Tooltip.install(image2, new Tooltip("PDF ფაილი"));
    		Tooltip.install(image3, new Tooltip("რედაქტირება"));
    		Tooltip.install(image4, new Tooltip("გადახდის სტატუსის შეცვლა"));
    		Tooltip.install(image5, new Tooltip("წაშლა"));
    		
    		
    		image.setId(objx.get("id").toString());
    		image2.setId(objx.get("id").toString());
    		image3.setId(objx.get("id").toString());
    		image4.setId(objx.get("id").toString());
    		image5.setId(objx.get("id").toString());
    		
    		image.setOnMouseClicked((MouseEvent e) -> {
    			String id = e.getPickResult().getIntersectedNode().getId();
    			System.out.println("View Object id " + id);
            });
    		
    		image2.setOnMouseClicked((MouseEvent e) -> {
    			String id = e.getPickResult().getIntersectedNode().getId();
    			System.out.println("PDF Object id " + id);
            });
    		
    		image3.setOnMouseClicked((MouseEvent e) -> {
    			String id = e.getPickResult().getIntersectedNode().getId();
    			System.out.println("Edit Object id " + id);
            });
    		
    		image4.setOnMouseClicked((MouseEvent e) -> {
    			String id = e.getPickResult().getIntersectedNode().getId();
    			System.out.println("Pay Object id " + id);
            });
    		
    		image5.setOnMouseClicked((MouseEvent e) -> {
    			String id = e.getPickResult().getIntersectedNode().getId();
    			System.out.println("Remove Object id " + id);
            });
         	
         	HBox actionLayout = new HBox(); 
    		actionLayout.getChildren().addAll(image, image2, image3, image4, image5);
    		actionLayout.setMargin(image, new Insets(0,5,0,0));
    		actionLayout.setMargin(image2, new Insets(0,5,0,0));
    		actionLayout.setMargin(image3, new Insets(0,5,0,0));
    		actionLayout.setMargin(image4, new Insets(0,5,0,0));
    		actionLayout.setMargin(image5, new Insets(0,5,0,0));
         	
    		allCount = Integer.parseInt(objx.get("allx").toString());
    		fl = Math.round(allCount / 10) + 1;
 
         	invoices.add(new invoices(
         			Integer.parseInt(objx.get("id").toString()), // int
         			objx.get("createDate").toString(), // string
         			objx.get("startDate").toString(), // string
         			objx.get("endDate").toString(), // string
         			objx.get("companyName").toString() + " " + objx.get("personName").toString(), //string
         			objx.get("description").toString(), // string
         			Integer.parseInt(objx.get("price").toString()), // double
         			Integer.parseInt(objx.get("sale").toString()), // int
         			(objx.get("payStatus").toString().equals("1")) ? "გადასახდელი" : "გადახდილი", // string
         			actionLayout // hbox
         	));
        }	
		layout.setBottom(pagex(fl, Integer.parseInt(p), invoiceType));
		
		return invoices;
	}
	
	@SuppressWarnings("static-access")
	public static HBox pagex(int all, int active, String invoiceType){
		HBox hbox = new HBox();
		
		
		for(int x = 1; x <= all; x++) {
			Button p = new Button();
			p.setText(""+x);
			p.setPadding(new Insets(5,5,5,5));
			if(x==active){
				p.setStyle("-fx-background-color: red; -fx-text-fill: white");
			}			
			hbox.getChildren().add(p);
			hbox.setMargin(p, new Insets(0,0,10,5));
			p.setOnAction(e -> {
				
				invoiceView in = new invoiceView();
				in.loadTable(table, invoiceType, layout, p.getText());
				
			});
	    }
		hbox.getStylesheets().add("file:resources/style.css");
		
		return hbox;
	}
}
