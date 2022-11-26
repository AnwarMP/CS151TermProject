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
	
	// create user's course table
	public void setupHomepage() throws SQLException {
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
    	stmt.close();
    	res.close();
    	connection.close();
	}
	/**
	 * Create unique courses table for each user and add courses to that table 
	 * @param courseName
	 * @throws SQLException
	 */
	public void createCourse(String courseName) throws SQLException {
		
    	Statement stmt = connection.createStatement();
    	
    	ResultSet res = stmt.executeQuery("SELECT * FROM session");
		// insert courses into table
    	StringBuffer sql1 = new StringBuffer("INSERT INTO ");
    	sql1.append(res.getString("username"));
    	sql1.append("_courses (courseName)" + " VALUES ( " + "'" + courseName + "');");
    	
    	
    	stmt.executeUpdate(sql1.toString());

    	// Create course detail table if not exist
    	StringBuffer sql2 = new StringBuffer("CREATE TABLE IF NOT EXISTS ");

    	sql2.append(courseName);	// query from db
    	sql2.append("_detail (\n"
    					+ " id			INTEGER PRIMARY KEY AUTOINCREMENT,\n"
    					+ " cardName	TEXT	NOT NULL,\n"
    					+ " definition	TEXT	NOT NULL,\n"
    					+ " isLearned	BOOLEAN NOT NULL CHECK (isLearned IN (0, 1))\n"
    					+ ");");
    	stmt.executeUpdate(sql2.toString());
				
    					
    	stmt.close();
    	res.close();
    	connection.close();
	}
	
	
	public void setSelectedCourse(int courseNumber, String courseName) throws SQLException {
		connection = SQLiteConnection.Connector();
		Statement stmt = connection.createStatement();
		String sql = "REPLACE INTO selected_course (id,courseName,courseNumber) " +
                  "VALUES (1, 'empty',0);"; 
		stmt.executeUpdate(sql);
		
		String query = "UPDATE selected_course SET courseName=? WHERE id=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, courseName);
		statement.setInt(2, 1);
		statement.executeUpdate();
		
		statement.close();
		stmt.close();
		
    	connection.close();
	}
	
	
	public void updateCourse(int courseId, String courseName, String updatedValue) throws SQLException {
		Statement stmt = connection.createStatement();
        
        
        StringBuffer sql1 = new StringBuffer("ALTER TABLE ");

		sql1.append(courseName);	// query from db
		sql1.append("_detail RENAME TO ");
		sql1.append(updatedValue);
		sql1.append("_detail;");
		
		System.out.println("UPDATE COURSE DETAIL" + sql1.toString());
		stmt.executeUpdate(sql1.toString());

        StringBuffer sql2 = new StringBuffer("UPDATE ");

        ResultSet res = stmt.executeQuery("SELECT * FROM session");
		sql2.append(res.getString("username"));	// query from db
		sql2.append("_courses SET courseName='");
		sql2.append(updatedValue);
		sql2.append("' WHERE id=");
		sql2.append(courseId);
		sql2.append(" AND courseName='");
		sql2.append(courseName);
		sql2.append("'");
		
		System.out.println("UPDATE COURSE NAMEEEEE" + sql2.toString());
		stmt.executeUpdate(sql2.toString());
		
		stmt.close();
		res.close();
		connection.close();
	}
	
	public void deleteCourse(int courseId, String courseName) throws SQLException{
		Statement stmt = connection.createStatement();

    	// delete course from table
    	StringBuffer sql1 = new StringBuffer("DROP TABLE ");
    	sql1.append(courseName);
    	sql1.append("_detail");
		System.out.println(sql1.toString());
    	stmt.executeUpdate(sql1.toString());
    	
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
    	stmt.executeUpdate(sql.toString());

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
