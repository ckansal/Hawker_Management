package billGenerator;

import java.time.temporal.ChronoUnit;
import jdbcc.MysqlConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
//import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class BillGeneratorViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtmobile;

    @FXML
    private DatePicker dtdos;

    @FXML
    private DatePicker dtdoe;

    @FXML
    private TextField txtprice;

    @FXML
    private TextField txtbill;
    
    Connection con;
    PreparedStatement pst;
    float r=0;

    @FXML
    void dogeneratebill(ActionEvent event) {
    	
    	
    	
    	LocalDate dos=dtdos.getValue();
    	LocalDate doe=dtdoe.getValue();
        long diff=dos.until(doe, ChronoUnit.DAYS);
       // System.out.println(diff);
    	//long daysBetween = DAYS.between(dateBefore, dateAfter);
        float total=diff*r;
        txtbill.setText(String.valueOf(total));

    }

    @FXML
    void dosaverecord(ActionEvent event) {
          try {
			pst=con.prepareStatement("insert into bills (mobile,dos,dupto,bill) values(?,?,?,?)");
			pst.setString(1,txtmobile.getText());
			LocalDate ldos=dtdos.getValue();
			pst.setDate(2, Date.valueOf(ldos));
			LocalDate ldoe=dtdoe.getValue();
			pst.setDate(3, Date.valueOf(ldoe));
			pst.setFloat(4,Float.parseFloat(txtprice.getText()));
		int r=	pst.executeUpdate();
		if(r==1)
			System.out.println("Record Saved");
		
		pst=con.prepareStatement("update customers set dos=? where mobile=?");
		pst.setDate(1, Date.valueOf(ldoe));
		pst.setString(2, txtmobile.getText());
		pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void filldos(ActionEvent event) {
       try {
		pst=con.prepareStatement("select * from customers where mobile=?");
		pst.setString(1,txtmobile.getText());
	    ResultSet records=	pst.executeQuery();
	    String rate="";
	    if(records.next())
	    {
	    	String dt=records.getString("dos");
	    	LocalDate ld=LocalDate.parse(dt);
	    	dtdos.setValue(ld);
	    	
	    	rate+=records.getString("sel_price"); 
	    }
	    
	    String[] c=rate.split(",");
	    for(String s:c)
	    {
	    	r+=Float.parseFloat(s);
	    }
	    //System.out.println(r);
	    String ra=String.valueOf(r);
	    txtprice.setText(ra);
	   
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
