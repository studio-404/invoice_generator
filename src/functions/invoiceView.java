package functions;

import Layouts.invoices;
import Layouts.welcomeLayout;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class invoiceView {
	public static void loadTable(TableView<invoices> table, String type, BorderPane layout, String p){
		for ( int i = 0; i<table.getItems().size(); i++) {
			table.getItems().clear();
		}
		
		welcomeLayout w = new welcomeLayout();
		table.setItems(w.getProduct(type, layout, p));
	}
}
