package adminDasboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void doareamaster(ActionEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getResource("/areaMaster/areaMasterView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dobillcollector(ActionEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getResource("/billCollector/BillCollectorView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void dobillgenerator(ActionEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getResource("/billGenerator/BillGeneratorView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dobilltable(ActionEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getResource("/billingGoogler/BillingGooglerView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void docustomermanager(ActionEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getResource("/customerManager/CustomerManagerView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void docustomertable(ActionEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getResource("/customerTable/CustomerTableView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dohawker(ActionEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getResource("/hawker/HawkerView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dohawkertable(ActionEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getResource("/hawkerTable/HawkerTableView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dopapermaster(ActionEvent event) {
try{
    		
			Parent root=FXMLLoader.load(getClass().getResource("/paperMaster/PaperMasterView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }
}
