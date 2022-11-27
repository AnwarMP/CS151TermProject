package application.model;

import java.sql.*;
import java.util.*;

import application.SQLiteConnection;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;

public class AccountModel {
	Connection connection;
	
	
	public AccountModel() {
		connection = SQLiteConnection.Connector();
		if(connection == null) System.exit(1);
	}
	
	/**
	 * Update password
	 * @param password
	 * @param answer
	 * @return
	 * @throws SQLException
	 */
	public boolean updatePassword(String password, String answer) throws SQLException {
		
		PassUtil passUtil = new PassUtil();
		String encryptedPass = passUtil.encrypt(password);
		
		//get username
		Statement stmt = connection.createStatement();
		ResultSet sessionRes = stmt.executeQuery("SELECT * FROM session");
		String user = sessionRes.getString("username");
		sessionRes.close();
		
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
			statement.setString(1, encryptedPass);
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
	
	
	/**
	 * Update security answer
	 * @param password
	 * @param username
	 * @param answer
	 * @return
	 * @throws SQLException
	 */
	public boolean updateSecurityAnswer(String password, String username, String answer) throws SQLException {
		PassUtil passUtil = new PassUtil();
		String encryptedPass = passUtil.encrypt(password);
		
		//get username
		Statement stmt = connection.createStatement();
		
		String query = "UPDATE users SET answer=? WHERE username=?";
		String answerVerify = "select * from users where username = ? and password = ?";

		
		PreparedStatement statement;
		try {
			//Run verfication first to check if the password and username match
			PreparedStatement verification = connection.prepareStatement(answerVerify);
			verification.setString(1, username);
			verification.setString(2, encryptedPass);
			
			
			ResultSet resultSet = verification.executeQuery();		
			
			//if it doesnt match then we can throw a false
			if(!resultSet.next()) {
				
				System.out.println("the answer was wrong");
				return false;
			}
			resultSet.close();
			
			//if it does match we can continue 
			statement = connection.prepareStatement(query);
			statement.setString(1, answer);
			statement.setString(2, username);
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
	

	/**
	 * Delete account and all tables associate to the deleted account
	 * @param password
	 * @param username
	 * @param answer
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteAccount(String password, String username, String answer) throws SQLException {
		
		Statement stmt = connection.createStatement();
		
		PassUtil passUtil = new PassUtil();
		String encryptedPass = passUtil.encrypt(password);
		
		//Run verfication first to check if the password, security answer, and username match
		String answerVerify = "select * from users where username = ? and password = ? and answer = ?";
		PreparedStatement verification = connection.prepareStatement(answerVerify);
		verification.setString(1, username);
		verification.setString(2, encryptedPass);
		verification.setString(3, answer);
		
		ResultSet resultSet = verification.executeQuery();		
		
		//if it doesnt match then we can throw a false
		if(!resultSet.next()) {
			
			System.out.println("the answer was wrong");
			return false;
		}
		resultSet.close();
		
		// delete all courses from list of courses
		StringBuffer sql2 = new StringBuffer("SELECT * FROM ");
    	sql2.append(username);
    	sql2.append("_courses");
		
		
		ResultSet coursesResult = stmt.executeQuery(sql2.toString());		
		
		ArrayList<String> coursesArray = new ArrayList<String>();
		while(coursesResult.next()) {
			coursesArray.add(coursesResult.getString("courseName"));
		}
		coursesResult.close();

		for(int i = 0; i < coursesArray.size(); i++) {
			PreparedStatement dropCourse = connection.prepareStatement(
				String.format("DROP TABLE IF EXISTS %s", coursesArray.get(i) + "_detail"));
			dropCourse.executeUpdate();
			dropCourse.close();
		}
		
		
    	// delete courses table of associated account
    	StringBuffer sql3 = new StringBuffer("DROP TABLE ");
    	sql3.append(username);
    	sql3.append("_courses");
    	stmt.executeUpdate(sql3.toString());
    	

		// delete course from table
    	StringBuffer sql4 = new StringBuffer("DELETE FROM users WHERE username='");
		sql4.append(username);
		sql4.append("' AND password='");
		sql4.append(encryptedPass);
		sql4.append("' AND answer='");
		sql4.append(answer);
		sql4.append("'");
    	stmt.executeUpdate(sql4.toString());

    	stmt.close();
    	connection.close();
    	return true;
	} 
	
}
