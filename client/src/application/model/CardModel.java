package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.SQLiteConnection;

public class CardModel {

	Connection connection;
	Statement stmt;
	private String termName;
	private String termDefinition;
	private int isLearned;
	
	public CardModel() throws SQLException {
		connection = SQLiteConnection.Connector();
		if(connection == null) System.exit(1);
		stmt = connection.createStatement();
		
	}
	
	public void setTermName(String name) {
		termName = name;
	}
	
	public String getTermName() {
		return termName;
	}
	
	public void setTermDefinition(String definition) {
		termDefinition = definition;
	}
	
	public String getTermDefinition() {
		return termDefinition;
	}
	
	public void setIsLearned(int isLearned) {
		this.isLearned = isLearned;
	}
	
	public int getIsLearned() {
		return isLearned;
	}
	
	/**
	 * Add cards to selected course table 
	 * @param courseName
	 * @throws SQLException
	 */
	public void createCard(String cardName, String cardDefinition, int isLearned) throws SQLException {
		
    	ResultSet res = stmt.executeQuery("SELECT * FROM selected_course");
    	
		// insert card into course table
    	StringBuffer sql1 = new StringBuffer("INSERT INTO ");
    	sql1.append(res.getString("courseName"));
    	sql1.append("_detail (cardName, definition, isLearned)" + " VALUES ('" 
    				+ cardName + "', '" + cardDefinition + "', " + isLearned + ");");
    	
    	System.out.println("UPDATE " + sql1.toString());
    	stmt.executeUpdate(sql1.toString());
    					
    	stmt.close();
    	connection.close();
	}
	
	/**
	 * Update card name
	 * @param courseName
	 * @throws SQLException
	 */
	public void updateCardName(String prevCardName, String newCardName, String definition) throws SQLException {
		connection = SQLiteConnection.Connector();
		Statement stmt = connection.createStatement();
		
		String query = "UPDATE selected_card SET cardName=? WHERE cardName=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, newCardName);
		statement.setString(2, prevCardName);
		
		statement.executeUpdate();
		
		ResultSet res = stmt.executeQuery("SELECT * FROM selected_course");
		

		query = "UPDATE " + res.getString("courseName") + "_detail SET cardName=? WHERE cardName=? AND definition=?";
		PreparedStatement statement2 = connection.prepareStatement(query);
		statement2.setString(1, newCardName);
		statement2.setString(2, prevCardName);
		statement2.setString(3, definition);
		
		statement2.executeUpdate();
		
		statement2.close();
		res.close();
		statement.close();
		stmt.close();
		
    	connection.close();
	}
	
	
	/**
	 * Update card definition
	 * @param courseName
	 * @throws SQLException
	 */
	public void updateCardDefinition(String cardName, String newDefinition, String prevDefinition) throws SQLException {
		connection = SQLiteConnection.Connector();
		Statement stmt = connection.createStatement();
		
		String query = "UPDATE selected_card SET definition=? WHERE cardName=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, newDefinition);
		statement.setString(2, cardName);
		
		statement.executeUpdate();
		
		ResultSet res = stmt.executeQuery("SELECT * FROM selected_course");
		

		query = "UPDATE " + res.getString("courseName") + "_detail SET definition=? WHERE cardName=? AND definition=?";
		PreparedStatement statement2 = connection.prepareStatement(query);
		statement2.setString(1, newDefinition);
		statement2.setString(2, cardName);
		statement2.setString(3, prevDefinition);
		
		statement2.executeUpdate();
		
		statement2.close();
		res.close();
		statement.close();
		stmt.close();
		
    	connection.close();
	}
	
	/**
	 * Update card name and definition
	 * @param courseName
	 * @throws SQLException
	 */
	public void updateCardInfo(String prevCardName, String newCardName, String newDefinition, String prevDefinition) throws SQLException {
		connection = SQLiteConnection.Connector();
		Statement stmt = connection.createStatement();
		
		String query = "UPDATE selected_card SET cardName=?, definition=? WHERE cardName=? AND definition=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, newDefinition);
		statement.setString(2, newCardName);
		statement.setString(1, prevDefinition);
		statement.setString(2, prevCardName);
		
		statement.executeUpdate();
		
		ResultSet res = stmt.executeQuery("SELECT * FROM selected_course");
		

		query = "UPDATE " + res.getString("courseName") + "_detail SET cardName=?, definition=? WHERE cardName=? AND definition=?";
		PreparedStatement statement2 = connection.prepareStatement(query);
		statement2.setString(1, newDefinition);
		statement2.setString(2, newCardName);
		statement2.setString(1, prevDefinition);
		statement2.setString(2, prevCardName);
		
		statement2.executeUpdate();
		
		statement2.close();
		res.close();
		statement.close();
		stmt.close();
		
    	connection.close();
	}
	
	
	/**
	 * Update card name and definition
	 * @param courseName
	 * @throws SQLException
	 */
	public void deleteCard(String cardName, String definition) throws SQLException {
		connection = SQLiteConnection.Connector();
		Statement stmt = connection.createStatement();
		
    	
    	String query = "DELETE FROM selected_card WHERE cardName=? AND definition=?";	
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, cardName);
		statement.setString(2, definition);
		
		statement.executeUpdate();
		
		
		ResultSet res = stmt.executeQuery("SELECT * FROM selected_course");
		
		query = "DELETE FROM " + res.getString("courseName") + "_detail WHERE cardName=? AND definition=?";
		PreparedStatement statement2 = connection.prepareStatement(query);
		statement2.setString(1, cardName);
		statement2.setString(2, definition);
		
		statement2.executeUpdate();
		
    	
    	System.out.println("SUCCESS DELETE CARD");
		
		
		statement2.close();
		res.close();
		statement.close();
		stmt.close();
		
    	connection.close();
	}
	
	public void setSelectedCard(String cardName, String definition, int isLearned) throws SQLException {
		connection = SQLiteConnection.Connector();
		Statement stmt = connection.createStatement();
		String sql = "REPLACE INTO selected_card (id, cardName, definition, isLearned) " +
                  "VALUES (1, 'empty', 'empty', 0);"; 
		stmt.executeUpdate(sql);
		
		String query = "UPDATE selected_card SET cardName=?, definition=?, isLearned=? WHERE id=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, cardName);
		statement.setString(2, definition);
		statement.setInt(3, isLearned);
		statement.setInt(4, 1);
		statement.executeUpdate();
		
		statement.close();
		stmt.close();
		
    	connection.close();
	}
	
	// change isLearned status when checkbox is clicked
	public void updateIsLearned(String cardName, String definition, int isLearned) throws SQLException {
		connection = SQLiteConnection.Connector();
		Statement stmt = connection.createStatement();
		
		String query = "UPDATE selected_card SET isLearned=? WHERE cardName=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, isLearned);
		statement.setString(2, cardName);
		
		statement.executeUpdate();
		
		ResultSet res = stmt.executeQuery("SELECT * FROM selected_course");
		

		query = "UPDATE " + res.getString("courseName") + "_detail SET isLearned=? WHERE cardName=? AND definition=?";
		PreparedStatement statement2 = connection.prepareStatement(query);
		statement2.setInt(1, isLearned);
		statement2.setString(2, cardName);
		statement2.setString(3, definition);
		
		statement2.executeUpdate();
		
		statement2.close();
		res.close();
		statement.close();
		stmt.close();
		
    	connection.close();
	}
	
	
}
