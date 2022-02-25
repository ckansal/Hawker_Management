module Paper {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens paperMaster to javafx.graphics,javafx.fxml;
	opens hawker to javafx.graphics,javafx.fxml;
	opens customerManager to javafx.graphics,javafx.fxml;
	opens billGenerator to javafx.graphics,javafx.fxml;
	opens billCollector to javafx.graphics,javafx.fxml;
	opens hawkerTable to javafx.graphics,javafx.fxml,javafx.base;
	opens billingGoogler to javafx.graphics,javafx.fxml,javafx.base;
	opens customerTable to javafx.graphics,javafx.fxml,javafx.base;
	opens adminDasboard to javafx.graphics,javafx.fxml;
	opens areaMaster to javafx.graphics,javafx.fxml;

}
