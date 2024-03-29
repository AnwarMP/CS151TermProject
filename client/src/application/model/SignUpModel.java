package application.model;

import java.sql.*;

import application.SQLiteConnection;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;

public class SignUpModel {
	Connection connection;
	
	public SignUpModel() {
		connection = SQLiteConnection.Connector();
		if(connection == null) System.exit(1);	
	}
	
	/**
	 * Sign up method -- insert account info into users table if account does not exist, otherwise, return false
	 * @param user
	 * @param pass
	 * @return
	 * @throws SQLException
	 */
	public boolean  signUp(String user, String pass, String answer) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		PassUtil passUtil = new PassUtil();
		String encryptedPass = passUtil.encrypt(pass);
				
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
				sql1.append(encryptedPass);
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
