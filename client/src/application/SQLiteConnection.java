package application;
import java.sql.*;

public class SQLiteConnection {
	
	public static Connection Connector() {
		try { 
			Class.forName("org.sqlite.JDBC"); //returns the class object given as a string argument 
			Connection conn = DriverManager.getConnection("jdbc:sqlite:prodex.sqlite"); //searches or creates a database if one does not exist 
			
			//Creating a table for the user 
			Statement stmt = conn.createStatement();
			
			String sql = "CREATE TABLE IF NOT EXISTS users (\n"
		                + " id			INT PRIMARY KEY,\n"
		                + "	username	TEXT	NOT NULL,\n"
		                + "	password	TEXT	NOT NULL\n"
		                + ");";	
			
			stmt.executeUpdate(sql);
			
			sql = "REPLACE INTO users (id,username,password) " +
	                  "VALUES (8, 'admin', '123456');"; 
	        stmt.executeUpdate(sql);
			
	        stmt.close();

			return conn;
		} catch (Exception e) {
			//TODO
			System.out.println(e);
			return null;
		}
	}
}
