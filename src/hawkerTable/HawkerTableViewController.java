package hawkerTable;

import jdbcc.MysqlConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HawkerTableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbarea;

    @FXML
    private TableView<HawkerBean> tblview;
    
    Connection con;
    PreparedStatement pst;
    ObservableList<HawkerBean> list;

    @FXML
    void dofetch(ActionEvent event) {
    	list=FXCollections.observableArrayList();

        try {
    		pst=con.prepareStatement("select * from hawkers");
   
    	    ResultSet records=pst.executeQuery();
    	    while(records.next())
    	    {
    	    	String n=records.getString("name");
    	    	String add=records.getString("address");
    	    	String con=records.getString("contact");
    	    	String ar=records.getString("areas");
    	    	String adh=records.getString("adhaar");
    	    	HawkerBean obj=new HawkerBean(n,add,con,ar,adh);
    	    	list.add(obj);
    	    	
    	    }
    	    tblview.setItems(list);
    	    
    	    
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}	

    }

    @FXML
    void dofilter(ActionEvent event) {
    	list=FXCollections.observableArrayList();

    try {
		pst=con.prepareStatement("select * from hawkers where areas like ?");
		pst.setString(1,"%"+cmbarea.getSelectionModel().getSelectedItem()+"%");
	    ResultSet records=pst.executeQuery();
	    while(records.next())
	    {
	    	String n=records.getString("name");
	    	String add=records.getString("address");
	    	String con=records.getString("contact");
	    	String ar=records.getString("areas");
	    	String adh=records.getString("adhaar");
	    	HawkerBean obj=new HawkerBean(n,add,con,ar,adh);
	    	list.add(obj);
	    	
	    }
	    tblview.setItems(list);
	    
	    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
    }
    void addColumns()
    {
    	TableColumn<HawkerBean, String> nameCol=new TableColumn<HawkerBean, String>("Hawker Name");
    	nameCol.setCellValueFactory(new PropertyValueFactory<HawkerBean, String>("name"));
    	nameCol.setMinWidth(100);
    	
    	TableColumn<HawkerBean, String> addressCol=new TableColumn<HawkerBean, String>("Address");
    	addressCol.setCellValueFactory(new PropertyValueFactory<HawkerBean, String>("address"));
    	addressCol.setMinWidth(100);
    	
    	TableColumn<HawkerBean, String> contactCol=new TableColumn<HawkerBean, String>("Contact");
    	contactCol.setCellValueFactory(new PropertyValueFactory<HawkerBean, String>("contact"));
    	contactCol.setMinWidth(100);
    	
    	TableColumn<HawkerBean, String> areaCol=new TableColumn<HawkerBean, String>("Areas");
    	areaCol.setCellValueFactory(new PropertyValueFactory<HawkerBean, String>("areas"));
    	areaCol.setMinWidth(100);
    	
    	TableColumn<HawkerBean, String> adhaarCol=new TableColumn<HawkerBean, String>("Adhaar");
    	adhaarCol.setCellValueFactory(new PropertyValueFactory<HawkerBean, String>("adhaar"));
    	adhaarCol.setMinWidth(100);
    
    	tblview.getColumns().addAll(nameCol,addressCol,contactCol,areaCol,adhaarCol);
    }
    void fillAreas()
    {
    	try {
    		ArrayList<String> arr=new ArrayList<String>();
			pst=con.prepareStatement("select * from areas");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String ar=records.getString("area");
				arr.add(ar);
			}
			cmbarea.getItems().addAll(arr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @FXML
    void initialize() {
       con=MysqlConnection.getConnection();
       addColumns();
       fillAreas();
    }
}
