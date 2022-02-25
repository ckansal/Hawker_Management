package billCollector;

import jdbcc.MysqlConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class BillCollectorViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtamount;

    @FXML
    private DatePicker dtfrom;

    @FXML
    private DatePicker dtupto;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void dofetch(ActionEvent event) {
      try {
		pst=con.prepareStatement("select * from bills where mobile=? and status=0");
		pst.setString(1, txtmobile.getText());
	ResultSet record=	pst.executeQuery();
	float b=0;
	String df = null;
	String dup = null;
	if(record.next())
	{
		 b=record.getFloat("bill");
         df=record.getString("dos");
		 dup=record.getString("dupto");
	}
	//System.out.println("hllo");
	//System.out.println(b+" "+df+" "+dup);
	
	txtamount.setText(String.valueOf(b));
	dtfrom.setValue(LocalDate.parse(df));
	dtupto.setValue(LocalDate.parse(dup));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	
    }

    @FXML
    void dopaid(ActionEvent event) {
        try {
			pst=con.prepareStatement("update bills set status=? where mobile=?");
			pst.setInt(1, 1);
			pst.setString(2, txtmobile.getText());
		int r=	pst.executeUpdate();
		if(r==1)
			System.out.println("Status updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
       con=MysqlConnection.getConnection();

    }
}
