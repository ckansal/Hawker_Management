package customerManager;

import jdbcc.MysqlConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class CustomerManagerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtaddress;

    @FXML
    private ComboBox<String> cmbarea;

    @FXML
    private ComboBox<String> cmbhawker;

    @FXML
    private TextField txtmobile;

    @FXML
    private ListView<String> lstpapers;

    @FXML
    private ListView<String> lstprices;

    @FXML
    private ListView<String> lstsellprice;

    @FXML
    private ListView<String> lstsellpaper;

    @FXML
    private DatePicker dtstart;
    
    Connection con;
    PreparedStatement pst;
    

    String sellpaper="",sellprice="";
    @FXML
    void dofillhawker(ActionEvent event) {
    	fillName();
    }

    @FXML
    void dohawker(ActionEvent event) {

    }

    @FXML
    void doleft(ActionEvent event) {
    	try {                                                
			pst=con.prepareStatement("delete from customers where mobile=?");
			pst.setString(1, txtmobile.getText());
			
			int count=pst.executeUpdate();
			if(count==0)
				System.out.println("Invalid mobile number ");
			else
				System.out.println("Record Deleted");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

    }

    @FXML
    void dosave(ActionEvent event) {
    	try
    	{
    	pst=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?)");
		pst.setString(1, txtname.getText());
		pst.setString(2, txtaddress.getText());
		pst.setString(3, cmbarea.getSelectionModel().getSelectedItem());
		pst.setString(4, cmbhawker.getSelectionModel().getSelectedItem());
		pst.setString(5, txtmobile.getText());
		pst.setString(6, sellpaper);
		pst.setString(7, sellprice);
		LocalDate ld=dtstart.getValue();
	
		pst.setDate(8,Date.valueOf(ld) );
		pst.executeUpdate();
		System.out.println("Record Updated");
			
	} catch (SQLException e) {
		
		e.printStackTrace();
	}


    }

    @FXML
    void dosearch(ActionEvent event) {
    	try {
			pst=con.prepareStatement("select * from customers where mobile=?" );
			pst.setString(1, txtmobile.getText());
			
			
			ResultSet records=	pst.executeQuery();
			
			if(records.next())
			{
				String n=records.getString("cname");
				String add=records.getString("address");
				
				String ar=records.getString("area");
				String h=records.getString("hawker");
				String sp=records.getString("sel_paper");
				String spr=records.getString("sel_price");
				String dt=records.getString("dos");
				
				LocalDate ld=LocalDate.parse(dt);
		    	
				
				txtname.setText(n);
				txtaddress.setText(add);
				cmbarea.getEditor().setText(ar);
				cmbhawker.getEditor().setText(h);
				txtmobile.setText(txtmobile.getText());
				lstsellpaper.getItems().setAll(sp);
				lstsellprice.getItems().setAll(spr);
				dtstart.setValue(ld);
				
			}
			else
				System.out.println("Invalid name");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

    @FXML
    void doupdate(ActionEvent event) {
    	try
    	{
    	pst=con.prepareStatement("update customers set cname=?,address=?,area=?,hawker=?,sel_paper=?,sel_price=?,dos=? where mobile=?");
		pst.setString(1, txtname.getText());
		pst.setString(2, txtaddress.getText());
		pst.setString(3, cmbarea.getSelectionModel().getSelectedItem());
		pst.setString(4, cmbhawker.getSelectionModel().getSelectedItem());
		pst.setString(8, txtmobile.getText());
		sellpaper="";
		sellprice="";
		//dofilllist(null);
		listvalues();
		pst.setString(5, sellpaper);
		pst.setString(6, sellprice);
		LocalDate ld=dtstart.getValue();
	
		pst.setDate(7,Date.valueOf(ld) );
		pst.executeUpdate();
		System.out.println("Record Updated");
			
	} catch (SQLException e) {
		
		e.printStackTrace();
	}


    }
    void listvalues()
    {
    	ObservableList<String> pap=	lstpapers.getSelectionModel().getSelectedItems();
    	ObservableList<Integer> inds=lstpapers.getSelectionModel().getSelectedIndices();
    	
    	
    	for (Integer i : inds) 
    	{
    		String s=lstprices.getItems().get(i);
    		r.add(s);
    		sellprice=sellprice+s+",";
		}
    	
    	
    	
    	
    	for (String str : pap) 
    	{
			p.add(str);
    		sellpaper+=str+",";
		}

    }
    ArrayList<String> r=new ArrayList<String>();
    ArrayList<String> p=new ArrayList<String>();
    
    @FXML
    void dofilllist(ActionEvent event) {
    	listvalues();
    	
    	    	lstsellprice.getItems().addAll(r);
    	lstsellprice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	lstsellpaper.getItems().addAll(p);
    	lstsellpaper.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    void fillArea() {
    	
    	
    	
        ArrayList<String> areas=new ArrayList<String>();
    	try {
    		pst=con.prepareStatement("select distinct areas from hawkers" );
    		ResultSet records=	pst.executeQuery();
    		
    		while(records.next())
    		{
    			String area=records.getString("areas");
    			areas.add(String.valueOf(area));
    		
    		}
    		cmbarea.getItems().addAll(areas);
    		
    	} 
    	catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    }
    void fillName() {
        ArrayList<String> names=new ArrayList<String>();
    	try {
    		/*pst=con.prepareStatement("select name from hawkers where areas=?" );
    		
    		
    		pst.setString(1,cmbarea.getSelectionModel().getSelectedItem());
    		ResultSet records=	pst.executeQuery();
    		
    		while(records.next())
    		{
    			String name=records.getString("name");
    			names.add(String.valueOf(name));
    		
    		}
    		cmbhawker.getItems().addAll(names);*/
    		pst=con.prepareStatement("select name from hawkers where areas like ?" );
    		pst.setString(1, "%"+cmbarea.getSelectionModel().getSelectedItem()+"%");
    		
    		ResultSet record=pst.executeQuery();
    		ArrayList<String> strList = new ArrayList<String>();
    		
    		while(record.next())
    		{
    			String name=record.getString("name");
    			names.add(String.valueOf(name));
    		}
    		cmbhawker.getItems().addAll(names);
    	} 
    	catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    }
    void fillPaper()
    {
    	ArrayList<String> pap=new ArrayList<String>();
    	ArrayList<String> pr=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select distinct * from papers");
			ResultSet r=pst.executeQuery();
			while(r.next())
			{
				String name=r.getString("paper");
    			pap.add(String.valueOf(name));
    			
    			String p=r.getString("rate");
    			pr.add(String.valueOf(p));
    			   
			}
			lstpapers.getItems().addAll(pap);
     	     lstpapers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     	     
     	    lstprices.getItems().addAll(pr);
    	     lstprices.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
       con=MysqlConnection.getConnection();
       fillArea();
       fillPaper();
    }
}
