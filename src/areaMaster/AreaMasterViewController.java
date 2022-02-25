package areaMaster;

import jdbcc.MysqlConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class AreaMasterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbarea;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void dodelete(ActionEvent event) {
    	try {
    		pst=con.prepareStatement("delete from areas where area=?");
    		pst.setString(1, cmbarea.getSelectionModel().getSelectedItem());
    		pst.executeUpdate();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @FXML
    void dosave(ActionEvent event) {
     try {
		pst=con.prepareStatement("insert into areas values(?)");
		pst.setString(1, cmbarea.getSelectionModel().getSelectedItem());
		pst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

    @FXML
    void doupdate(ActionEvent event) {

    }
    void doFillArea() {
    	ArrayList<String> arr=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select * from areas");
		ResultSet records=	pst.executeQuery();
		while(records.next())
		{
			String c=records.getString("area");
			arr.add(c);
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
        doFillArea();
    }
}
