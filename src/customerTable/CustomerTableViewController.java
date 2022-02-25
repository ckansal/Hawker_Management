package customerTable;

import jdbcc.MysqlConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import hawkerTable.HawkerBean;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerTableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbarea;

    @FXML
    private ComboBox<String> cmbpaper;

    @FXML
    private TableView<CustomerBean> tblview;
    
    Connection con;
    PreparedStatement pst;
    ObservableList<CustomerBean> list;


    @FXML
    void GetByPaper(ActionEvent event) {
    	list=FXCollections.observableArrayList();
    	try {
    		pst=con.prepareStatement("select * from customers where sel_paper like ?" );
    		pst.setString(1, "%"+cmbpaper.getSelectionModel().getSelectedItem()+"%");
    		ResultSet records=	pst.executeQuery();
    		
    		while(records.next())
    		{
    			String n=records.getString("cname");
    			String add=records.getString("address");
    			String a=records.getString("area");
    			String h=records.getString("hawker");
    			String m=records.getString("mobile");
    			String spa=records.getString("sel_paper");
    			String spr=records.getString("sel_price");
    			String d=records.getString("dos");
    			
    		
    			CustomerBean obj=new CustomerBean(n,add,a,h,m,spa,spr,d);
    			
    			list.add(obj);
    		}
    		//System.out.println(list.size());
    		
    		
    		tblview.setItems(list);
    		
    		
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @FXML
    void getAll(ActionEvent event) {
    	list=FXCollections.observableArrayList();
    	try {
    		pst=con.prepareStatement("select * from customers" );
    		ResultSet records=	pst.executeQuery();
    		
    		while(records.next())
    		{
    			String n=records.getString("cname");
    			String add=records.getString("address");
    			String a=records.getString("area");
    			String h=records.getString("hawker");
    			String m=records.getString("mobile");
    			String spa=records.getString("sel_paper");
    			String spr=records.getString("sel_price");
    			String d=records.getString("dos");
    			
    		
    			CustomerBean obj=new CustomerBean(n,add,a,h,m,spa,spr,d);
    			
    			list.add(obj);
    		}
    		//System.out.println(list.size());
    		
    		
    		tblview.setItems(list);
    		
    		
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    }

    @FXML
    void getByArea(ActionEvent event) {
    	list=FXCollections.observableArrayList();
    	try {
    		pst=con.prepareStatement("select * from customers where area like ?" );
    		pst.setString(1, "%"+cmbarea.getSelectionModel().getSelectedItem()+"%");
    		ResultSet records=	pst.executeQuery();
    		
    		while(records.next())
    		{
    			String n=records.getString("cname");
    			String add=records.getString("address");
    			String a=records.getString("area");
    			String h=records.getString("hawker");
    			String m=records.getString("mobile");
    			String spa=records.getString("sel_paper");
    			String spr=records.getString("sel_price");
    			String d=records.getString("dos");
    			
    		
    			CustomerBean obj=new CustomerBean(n,add,a,h,m,spa,spr,d);
    			
    			list.add(obj);
    		}
    		//System.out.println(list.size());
    		
    		
    		tblview.setItems(list);
    		
    		
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    }
    void addColumns()
    {
    	TableColumn<CustomerBean, String> nameCol=new TableColumn<CustomerBean, String>("Customer Name");
    	nameCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("cname"));//field name in bean
    	nameCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> addressCol=new TableColumn<CustomerBean, String>("Address");
    	addressCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("address"));//field name in bean
    	addressCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> areaCol=new TableColumn<CustomerBean, String>("Area");
    	areaCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("area"));//field name in bean
    	areaCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> hawkerCol=new TableColumn<CustomerBean, String>("Hawker");
    	hawkerCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("hawker"));//field name in bean
    	hawkerCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> mobileCol=new TableColumn<CustomerBean, String>("Mobile");
    	mobileCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("mobile"));//field name in bean
    	mobileCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> paperCol=new TableColumn<CustomerBean, String>("Papers");
    	paperCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("sel_paper"));//field name in bean
    	paperCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> priceCol=new TableColumn<CustomerBean, String>("Price");
    	priceCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("sel_price"));//field name in bean
    	priceCol.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> dCol=new TableColumn<CustomerBean, String>("Date of Start");
    	dCol.setCellValueFactory(new PropertyValueFactory<CustomerBean, String>("dos"));//field name in bean
    	dCol.setMinWidth(100);
    	
    	
    	tblview.getColumns().addAll(nameCol,addressCol,areaCol,hawkerCol,mobileCol,paperCol,priceCol,dCol);
    	
    	
    	
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
    void fillPaper()
    {
    	try {
    		ArrayList<String> arr=new ArrayList<String>();
			pst=con.prepareStatement("select * from papers");
			ResultSet records=pst.executeQuery();
			while(records.next())
			{
				String ar=records.getString("paper");
				arr.add(ar);
			}
			cmbpaper.getItems().addAll(arr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void initialize() {
        con=MysqlConnection.getConnection();
        fillAreas();
        fillPaper();
    	addColumns();
    }
}
