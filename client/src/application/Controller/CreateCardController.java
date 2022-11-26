package application.Controller;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.SQLiteConnection;
import application.model.CardModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateCardController implements Initializable  {

	@FXML
    private Label cardModalTitle;

    @FXML
    private TextField termName;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextArea termDefinition;
    
    @FXML
    private Label errorMsg;
    
    private String selectedCourseName;
    private String selectedCourseId;
    
    
    private CardModel cardModel;
    
    private SelectedCourseController selectedCourseController;
    
	private Connection conn;
	
    public CreateCardController() throws SQLException {
    	cardModel = new CardModel();
    	selectedCourseController = new SelectedCourseController();
    }
   
    
    private void setSelectedCourseName(String courseName) {
    	selectedCourseName = courseName;
    }
    
    private String getSelectedCourseName() {
    	return this.selectedCourseName;
    }
    
    private void setSelectedCourseId(String id) {
    	selectedCourseId = id;
    }
    
    private String getSelectedCourseId() {
    	return this.selectedCourseId;
    }
    
    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException, SQLException {
    	
    	if(!termName.getText().isEmpty() && !termDefinition.getText().isEmpty()) {
    		cardModel.createCard(termName.getText(), termDefinition.getText(), 0);
        	Main m = new Main();
        	m.openNewWindow("views/selected_course.fxml", event);
    	} else {
    		errorMsg.setText("Please enter the term name and definition");
    	}	
    }
    
    
    @FXML
    private void cancelBtnAction(ActionEvent event) throws IOException, SQLException {
    	Main m = new Main();
    	m.openNewWindow("views/selected_course.fxml", event);
    }
    
    // add new card to selected course table
    private void setUpSelectedCourse() throws SQLException {
    	conn = SQLiteConnection.Connector();

    	Statement stmt = conn.createStatement();
    	ResultSet res = stmt.executeQuery("SELECT * FROM selected_course");
    	
    	setSelectedCourseId(Integer.toString(res.getInt("courseNumber")));
    	setSelectedCourseName(res.getString("courseName"));
    	
		res.close();
		stmt.close();
		conn.close();
    }
    
    @Override 
	public void initialize(URL location, ResourceBundle resources) {
    	try {
    		setUpSelectedCourse();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
