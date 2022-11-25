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
		                + "	username	TEXT	NOT NULL,\n"
		                + "	password	TEXT	NOT NULL,\n"
		                + " answer		TEXT	NOT NULL"
		                + ");";	
			
			
			stmt.executeUpdate(sql);
			
	        //create session table to keep track of who is currently logged in
	        sql = "CREATE TABLE IF NOT EXISTS session (\n"
	                + " id			INT PRIMARY KEY,\n"
	                + "	username	TEXT	NOT NULL\n"
	                + ");";	
	        
	        stmt.executeUpdate(sql);  
	        
	      //create selected course table to keep track of the currently selected course
	        sql = "CREATE TABLE IF NOT EXISTS selected_course (\n"
	                + " id			INT PRIMARY KEY,\n"
	                + "	courseName	TEXT	NOT NULL,\n"
	                + "	courseNumber	INT	PRIMARY KEY\n"
	                + ");";	
	        
	        stmt.executeUpdate(sql); 
	        
	        
	      //create selected card table to keep track of the currently selected card
	        sql = "CREATE TABLE IF NOT EXISTS selected_card (\n"
	                + " id			INT PRIMARY KEY,\n"
					+ " cardName	TEXT	NOT NULL,\n"
					+ " definition	TEXT	NOT NULL,\n"
					+ " isLearned	BOOLEAN NOT NULL CHECK (isLearned IN (0, 1))\n"
	                + ");";	
	        
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
