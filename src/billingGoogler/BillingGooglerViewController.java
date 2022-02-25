package billingGoogler;

import jdbcc.MysqlConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import hawkerTable.HawkerBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class BillingGooglerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton radpaid;

    @FXML
    private ToggleGroup rad;

    @FXML
    private RadioButton radunpaid;

    @FXML
    private TableView<BillingBean> tblview;
    
    Connection con;
    PreparedStatement pst;
    ObservableList<BillingBean> list;

    @FXML
    void doFetchAll(ActionEvent event) {
    	list=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("select * from bills");
		
			ResultSet records=pst.executeQuery();
			while(records.next())
		    {
		    	String m=records.getString("mobile");
		    	String d=records.getString("dos");
		    	String dup=records.getString("dupto");
		    	float b=records.getFloat("bill");
		    	System.out.println(m+" "+d+" "+dup+" "+b);
		    	BillingBean obj=new BillingBean(m,d,dup,b);
		    	list.add(obj);
		    	
		    }
		    tblview.setItems(list);
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doget(ActionEvent event) {

    	list=FXCollections.observableArrayList();
    	int r;
    	if(radpaid.isSelected()==true)
    		r=1;
    	else
    		r=0;
    	try {
			pst=con.prepareStatement("select * from bills where status=?");
			pst.setInt(1,r);
			ResultSet records=pst.executeQuery();
			while(records.next())
		    {
		    	String m=records.getString("mobile");
		    	String d=records.getString("dos");
		    	String dup=records.getString("dupto");
		    	float b=records.getFloat("bill");
		    	System.out.println(m+" "+d+" "+dup+" "+b);
		    	BillingBean obj=new BillingBean(m,d,dup,b);
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
    	System.out.println("Creating table");
    	TableColumn<BillingBean, String> mobileCol=new TableColumn<BillingBean, String>("Mobile No.");
    	mobileCol.setCellValueFactory(new PropertyValueFactory<BillingBean, String>("mobile"));
    	mobileCol.setMinWidth(100);
    	
    	TableColumn<BillingBean, String> dosCol=new TableColumn<BillingBean, String>("Date of Start");
    	dosCol.setCellValueFactory(new PropertyValueFactory<BillingBean, String>("dos"));
    	dosCol.setMinWidth(100);
    	
    	TableColumn<BillingBean, String> duptoCol=new TableColumn<BillingBean, String>("Date of End");
    	duptoCol.setCellValueFactory(new PropertyValueFactory<BillingBean, String>("dupto"));
    	duptoCol.setMinWidth(100);
    	
    	TableColumn<BillingBean, Float> billCol=new TableColumn<BillingBean, Float>("Bill");
    	billCol.setCellValueFactory(new PropertyValueFactory<BillingBean, Float>("bill"));
    	billCol.setMinWidth(100);
    	
    	
    
    	tblview.getColumns().addAll(mobileCol,dosCol,duptoCol,billCol);
    }

    @FXML
    void initialize() {
       con=MysqlConnection.getConnection();
  	 addColumns();
    }
}
