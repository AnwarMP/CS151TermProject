package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.SQLiteConnection;

public class CourseModel {
	private String courseName;
	private int courseId;
	
	Connection connection;
	public CourseModel() {
		connection = SQLiteConnection.Connector();
		if(connection == null) System.exit(1);
		
	}
	
	/**
	 * Create unique courses table for each user and add courses to that table 
	 * @param courseName
	 * @throws SQLException
	 */
	public void createCourse(String courseName) throws SQLException {
		
    	Statement stmt = connection.createStatement();
    	 
    	ResultSet res = stmt.executeQuery("SELECT * FROM session");
    	
		// Create username_courses table if not exist
		StringBuffer sql1 = new StringBuffer("CREATE TABLE IF NOT EXISTS ");

		sql1.append(res.getString("username"));	// query from db
		sql1.append("_courses (\n"
				+ " id			INTEGER PRIMARY KEY AUTOINCREMENT,\n"
				+ " courseName	TEXT	NOT NULL\n"
				+ ");");
        
    	stmt.executeUpdate(sql1.toString());

    	ResultSet res2 = stmt.executeQuery("SELECT * FROM session");
		// insert courses into table
    	StringBuffer sql2 = new StringBuffer("INSERT INTO ");
    	sql2.append(res2.getString("username"));
    	sql2.append("_courses (courseName)" + " VALUES ( " + "'" + courseName + "');");
    	
    	
    	stmt.executeUpdate(sql2.toString());

    	stmt.close();
    	res.close();
    	res2.close();
    	connection.close();
	}
	
	public void updateCourse(int courseId, String courseName, String updatedValue) throws SQLException {
		Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM session");
        
        StringBuffer sql = new StringBuffer("UPDATE ");

		sql.append(res.getString("username"));	// query from db
		sql.append("_courses SET courseName='");
		sql.append(updatedValue);
		sql.append("' WHERE id=");
		sql.append(courseId);
		sql.append(" AND courseName='");
		sql.append(courseName);
		sql.append("'");
		
		stmt.executeUpdate(sql.toString());
		
		stmt.close();
		res.close();
		connection.close();
	}
	
	public void deleteCourse(int courseId, String courseName) throws SQLException{
		Statement stmt = connection.createStatement();
   	 
    	ResultSet res = stmt.executeQuery("SELECT * FROM session");
    	
        
		// delete course from table
    	StringBuffer sql = new StringBuffer("DELETE FROM ");
    	sql.append(res.getString("username"));
    	sql.append("_courses");
    	sql.append(" WHERE id=");
		sql.append(courseId);
		sql.append(" AND courseName='");
		sql.append(courseName);
		sql.append("'");
		System.out.println(sql.toString());
    	stmt.executeUpdate(sql.toString());
    	
    	System.out.println("SUCCESS");

    	stmt.close();
    	res.close();
    	connection.close();
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	
	
	
}
