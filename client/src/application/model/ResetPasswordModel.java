package application.model;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.SQLiteConnection;

public class ResetPasswordModel {
	Connection connection;
	
	
	public ResetPasswordModel() {
		connection = SQLiteConnection.Connector();
		if(connection == null) System.exit(1);
	}
	
	public boolean updatePassword(String password, String rpassword, String answer) throws SQLException {
		
		//get username
		Statement stmt = connection.createStatement();
		ResultSet sessionRes = stmt.executeQuery("SELECT * FROM session");
		String user = sessionRes.getString("username");

		
		String query = "UPDATE users SET password=? WHERE username=?";
		String answerVerify = "select * from users where username=? AND answer=?";
		
		//Check if passwords match 
		if(!checkPassword(password, rpassword)) return false;
		
		PreparedStatement statement;
		try {
			//Run verfication first to check if the answers match
			PreparedStatement verification = connection.prepareStatement(answerVerify);
			verification.setString(1, user);
			verification.setString(2, answer);
			
			
			ResultSet resultSet = verification.executeQuery();
			verification.close();
			
			//if it doesnt match then we can throw a false
			if(!resultSet.next()) {
				
				System.out.println("the answer was wrong");
				return false;
			}
			resultSet.close();
			
			//if it does match we can continue 
			statement = connection.prepareStatement(query);
			statement.setString(1, password);
			statement.setString(2, user);
			statement.executeUpdate();
			statement.close();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			
		}
		return false; 
	} 
	
	public boolean checkPassword(String password, String reentry) {
		return password.equals(reentry);
	}
	
}
