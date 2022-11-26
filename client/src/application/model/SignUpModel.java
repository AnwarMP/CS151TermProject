package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.SQLiteConnection;

public class SignUpModel {
	Connection connection;
	
	public SignUpModel() {
		connection = SQLiteConnection.Connector();
		if(connection == null) System.exit(1);	
	}
	
	
	public boolean  signUp(String user, String pass) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		//To query the db to find a user with a matching username and password
		String query = "select * from users where username = ?";

		try  { 
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			
			resultSet = preparedStatement.executeQuery();
						
			if(resultSet.next()) {
				return false;
			}
			else { 
				StringBuffer sql1 = new StringBuffer("INSERT INTO users (username,password) "
						+ "VALUES('");

				sql1.append(user);	// query from db
				sql1.append("', '");
				sql1.append(pass);
				sql1.append("');");
		        
		    	// DB Command
				Statement stmt = connection.createStatement();
				
				stmt.executeUpdate(sql1.toString());

				stmt.close();
				
				return true;
			}
			
		} catch(Exception e){
			e.printStackTrace();
			return false;
			
		} finally { 
			
			preparedStatement.close();
			resultSet.close();
		}
	} 
	
	public boolean  signUp(String user, String pass, String answer) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		// encrypt pw
		String encryptedPw = "";
		char[] chars = pass.toCharArray();
		int key = 6;
		for(char c : chars) {
		c -= key;
			encryptedPw += c;
		}
				
		//To query the db to find a user with a matching username and password
		String query = "select * from users where username = ?";

		try  { 
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			
			resultSet = preparedStatement.executeQuery();
						
			if(resultSet.next()) {
				return false;
			}
			else { 
				StringBuffer sql1 = new StringBuffer("INSERT INTO users (username,password,answer) "
						+ "VALUES('");

				sql1.append(user);	// query from db
				sql1.append("', '");
				sql1.append(encryptedPw);
				sql1.append("', '");
				sql1.append(answer);
				sql1.append("');");
		        
		    	// DB Command
				Statement stmt = connection.createStatement();
				
				stmt.executeUpdate(sql1.toString());

				stmt.close();
				
				return true;
			}
			
		} catch(Exception e){
			e.printStackTrace();
			return false;
			
		} finally { 
			
			preparedStatement.close();
			resultSet.close();
		}
	} 
	
}
