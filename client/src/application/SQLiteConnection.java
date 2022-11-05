package application;
import java.sql.*;

public class SQLiteConnection {
	
	public static Connection Connector() {
		try { 
			Class.forName("org.sqlite.JDBC"); //returns the class object given as a string argument 
			Connection conn = DriverManager.getConnection("jdbc:sqlite:prodex.sqlite"); //searches or creates a database if one does not exist 
			

			return conn;
		} catch (Exception e) {
			//TODO
			System.out.println(e);
			return null;
		}
	}
}
