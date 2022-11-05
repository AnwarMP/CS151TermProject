package application.model;
import java.sql.*;

import application.SQLiteConnection;

public class LoginModel {
	Connection connection;
	public LoginModel() {
		connection = SQLiteConnection.Connector();
		if(connection == null) System.exit(1);
		
	}
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} //based on 
	}
	
	public boolean setSession(String user) throws SQLException  {
		
		try {
			
			String query = "UPDATE session SET username=? WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			statement.setInt(2, 1);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		return false;
	}
	
	public boolean isLogin(String user, String pass) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		//To query the db to find a user with a matching username and password
		String query = "select * from users where username = ? and password = ?";

		try  { 
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return true;
			}
			else { 
				return false;
			}
			
		} catch(Exception e){
			return false;
			
		} finally { 
			
			preparedStatement.close();
			resultSet.close();
		}
	}

}
