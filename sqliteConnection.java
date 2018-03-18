import java.sql.*;
import javax.swing.*;
public class sqliteConnection {
	Connection conn = null;
	public static Connection dbConnector()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/nitix/eclipse-workspace/Shopping Mall Management System/Shopping mall management system.sqlite");
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}