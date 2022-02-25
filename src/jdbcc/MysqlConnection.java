package jdbcc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MysqlConnection {
	public static Connection getConnection()
	{
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/paper","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}

}
