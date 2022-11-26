package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.SQLiteConnection;

public class DeleteAccountModel {
	Connection connection;
	
	
	public DeleteAccountModel() {
		connection = SQLiteConnection.Connector();
		if(connection == null) System.exit(1);
	}
	
	public boolean deleteAccount(String password, String username, String answer) throws SQLException {
		
		//get username
		Statement stmt = connection.createStatement();
		
		StringBuffer tableName = new StringBuffer(username);
		tableName.append("_courses");
		
		
		String query = "DELETE FROM users WHERE username = ?";
		String dropCourses = "DROP TABLE IF EXISTS '?' ";
		String answerVerify = "select * from users where username = ? and password = ? and answer = ?";
		
		PreparedStatement statement;
		try {
			//Run verfication first to check if the password and username match
			PreparedStatement verification = connection.prepareStatement(answerVerify);
			verification.setString(1, username);
			verification.setString(2, password);
			verification.setString(3, answer);
			
			
			ResultSet resultSet = verification.executeQuery();		
			
			//if it doesnt match then we can throw a false
			if(!resultSet.next()) {
				
				System.out.println("the answer was wrong");
				return false;
			}
			resultSet.close();
			
			//if it does match we can continue 
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.executeUpdate();
			
			PreparedStatement drop = connection.prepareStatement(dropCourses);
			drop.setString(1, tableName.toString());
			
			drop.close();
			statement.close();
			verification.close();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			
		}
		return false; 
	} 
	
	

}
