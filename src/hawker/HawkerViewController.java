package hawker;

import jdbcc.MysqlConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class HawkerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbname;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtcontact;

    @FXML
    private ComboBox<String> cmbarea;

    @FXML
    private TextField txtareas;

    @FXML
    private ImageView prev;

    @FXML
    private TextField txtadhaar;
    
    Connection con;
    PreparedStatement pst;
    String filePath;
   

    @FXML
    void dobrowse(ActionEvent event) {

FileChooser chooser=new FileChooser();
    	
    	chooser.setTitle("Select Profile Pic:");
    	
    	chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("*.*", "*.*")
                
            );
    	File file=chooser.showOpenDialog(null);
    	filePath=file.getAbsolutePath();
    
    	
    	try {
		      prev.setImage(new Image(new FileInputStream(file)));
		} 
    	catch (FileNotFoundException e) {	e.printStackTrace();}


    }

       @FXML
       void dochange(ActionEvent event) {
    	try
    	{
    	pst=con.prepareStatement("update hawkers set address=?,contact=?,areas=?,pic=?,adhaar=?,where name=?");
		pst.setString(6, cmbname.getSelectionModel().getSelectedItem());
		pst.setString(1, txtaddress.getText());
		pst.setString(2, txtcontact.getText());
		pst.setString(3,area);
		pst.setString(4,filePath);
		pst.setString(5, txtadhaar.getText());
		
		pst.executeUpdate();
		System.out.println("Record Updated");
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}

    }

    @FXML
    void dofetch(ActionEvent event) {
    	try {
			pst=con.prepareStatement("select * from hawkers where name=?" );
			pst.setString(1, cmbname.getSelectionModel().getSelectedItem());
			
			
			ResultSet records=	pst.executeQuery();
			
			if(records.next())
			{
				String n=records.getString("name");
				String add=records.getString("address");
				String c=records.getString("contact");
				String ar=records.getString("areas");
				
				String path=records.getString("pic");
				String ad=records.getString("adhaar");
				
				cmbname.getEditor().setText(n);
				txtaddress.setText(add);
				txtcontact.setText(c);
				txtareas.setText(ar);
				Image image;
				try {
					image = new Image(new FileInputStream(path));
					prev.setImage(image);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				txtadhaar.setText(ad);
				
			}
			else
				System.out.println("Invalid name");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



    }
    String area="";
    @FXML
    void dofillarea(ActionEvent event) {
        String ar=cmbarea.getSelectionModel().getSelectedItem();
        area+=ar+",";
        txtareas.setText(area);
    }

    @FXML
    void dofillname(ActionEvent event) {

    }

    @FXML
    void doleft(ActionEvent event) {
    	try {                                                
			pst=con.prepareStatement("delete from hawkers where name=?");
			pst.setString(1, cmbname.getSelectionModel().getSelectedItem());
			
			int count=pst.executeUpdate();
			if(count==0)
				System.out.println("Invalid name");
			else
				System.out.println("Record Deleted");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}



    }

    @FXML
    void doregister(ActionEvent event) {
    	try
    	{
    	pst=con.prepareStatement("insert into hawkers values(?,?,?,?,?,?)");
		pst.setString(1, cmbname.getSelectionModel().getSelectedItem());
		pst.setString(2, txtaddress.getText());
		pst.setString(3, txtcontact.getText());
		pst.setString(4,area);
		pst.setString(5,filePath);
		pst.setString(6, txtadhaar.getText());
		
		pst.executeUpdate();
		System.out.println("Record Saved");
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}

    }
    void fillName() {
        ArrayList<String> names=new ArrayList<String>();
    	try {
    		pst=con.prepareStatement("select name from hawkers" );
    		ResultSet records=	pst.executeQuery();
    		
    		while(records.next())
    		{
    			String name=records.getString("name");
    			names.add(String.valueOf(name));
    		
    		}
    		cmbname.getItems().addAll(names);
    		
    	} 
    	catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
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

    @FXML
    void initialize() {
       con=MysqlConnection.getConnection();
       fillName();
       fillArea();
    }
}
