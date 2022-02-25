package paperMaster;

import jdbcc.MysqlConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PaperMasterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbpaper;

    @FXML
    private TextField txtrate;
    
    Connection con;
    PreparedStatement ps;
    
    
    @FXML
    void dopaper(ActionEvent event) {
     try {
		ps=con.prepareStatement("select rate from papers where paper=?");
		ps.setString(1,cmbpaper.getEditor().getText());
		ResultSet r= ps.executeQuery();
		if(r.next())
		{
			float p=r.getFloat("rate");
			txtrate.setText(String.valueOf(p));
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

    @FXML
    void doremove(ActionEvent event) {
       try {
		ps=con.prepareStatement("delete from papers where paper=?");
		ps.setString(1, cmbpaper.getEditor().getText());
		int r=ps.executeUpdate();
		if(r==1)
			System.out.println("Record deleted");
		else
			System.out.println("Record is not present");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

    @FXML
    void dosave(ActionEvent event) {
         try {
			ps=con.prepareStatement("insert into papers values(?,?)");
			ps.setString(1,cmbpaper.getSelectionModel().getSelectedItem());
			ps.setFloat(2,Float.parseFloat(txtrate.getText()));
		int c=	ps.executeUpdate();
		if(c==1)
			System.out.println("Record Saved");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doupdate(ActionEvent event) {
       try {
		ps=con.prepareStatement("update papers set rate=? where paper=?");
		ps.setFloat(1,Float.parseFloat(txtrate.getText()));
		ps.setString(2, cmbpaper.getEditor().getText());
		int c=ps.executeUpdate();
		if(c==1)
			System.out.println("Record updated");
		else
			System.out.println("Invalid papername");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    void fillNewspaper()
    {
    	ArrayList<String> pa=new ArrayList<String>();
			try {
				ps=con.prepareStatement("select * from papers");
				ResultSet records=	ps.executeQuery();
				
				while(records.next())
				{
					String p=records.getString("paper");
					pa.add(String.valueOf(p));
				
				}
				cmbpaper.getItems().addAll(pa);
				
			} 
			catch (SQLException e) {
				
				e.printStackTrace();
			}
    }

    @FXML
    void initialize() {
    	
     	con=MysqlConnection.getConnection();
     	fillNewspaper();
     	
    }
}//"Ajit road","Model town","Adarsh nagar","Nai Basti","Partap nagar"
