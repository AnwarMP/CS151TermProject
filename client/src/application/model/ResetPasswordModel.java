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
	
	public boolean updatePassword(String password, String answer) throws SQLException {
		
		// encrypt pw
		String encryptedPw = "";
		char[] chars = password.toCharArray();
		int key = 6;
		for(char c : chars) {
			c -= key;
			encryptedPw += c;
		}
		
		//get username
		Statement stmt = connection.createStatement();
		ResultSet sessionRes = stmt.executeQuery("SELECT * FROM session");
		String user = sessionRes.getString("username");

		
		String query = "UPDATE users SET password=? WHERE username=?";
		String answerVerify = "select * from users where username=? AND answer=?";
		
		PreparedStatement statement;
		try {
			//Run verfication first to check if the answers match
			PreparedStatement verification = connection.prepareStatement(answerVerify);
			verification.setString(1, user);
			verification.setString(2, answer);
			
			
			ResultSet resultSet = verification.executeQuery();		
			
			//if it doesnt match then we can throw a false
			if(!resultSet.next()) {
				
				System.out.println("the answer was wrong");
				return false;
			}
			resultSet.close();
			
			//if it does match we can continue 
			statement = connection.prepareStatement(query);
			statement.setString(1, encryptedPw);
			statement.setString(2, user);
			statement.executeUpdate();
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
	
	public boolean checkPassword(String password, String reentry) {
		return password.equals(reentry);
	}
	
}
