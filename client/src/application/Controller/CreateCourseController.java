package application.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import application.SQLiteConnection;
import application.model.CourseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateCourseController {

	@FXML
    private Label courseModalTitle;

    @FXML
    private TextField courseName;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;
    
    private Connection conn;
    
    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException, SQLException {
    	conn = SQLiteConnection.Connector();
    	 Statement stmt = conn.createStatement();
    	 
    	 ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE username='admin' AND password='123456'");
    	

		StringBuffer sql1 = new StringBuffer("CREATE TABLE IF NOT EXISTS ");

		sql1.append(res.getString("username"));	// query from db
		sql1.append("_courses (\n"
				+ " courseName	TEXT	NOT NULL\n"
				+ ");");
        
    	// DB Commands
        
    	StringBuffer sql2 = new StringBuffer("INSERT INTO ");
    	sql2.append(res.getString("username"));
    	sql2.append("_courses (courseName)" + " VALUES ( " + "'" + courseName.getText() + "');");
    	
    	stmt.executeUpdate(sql1.toString());
    	stmt.executeUpdate(sql2.toString());
    	stmt.close();
    	
    	
    	
    	
    	
    	// Set UI
//    	Course course = new Course();
//    	course.setCourseName(courseName.getText());
//    	course.setCourseId("3");	// query param from db
    	
    	Main m = new Main();
    	m.openNewWindow("views/homepage.fxml", event);
    }
    
    
    @FXML
    private void cancelBtnAction(ActionEvent event) throws IOException {
    	Main m = new Main();
    	m.openNewWindow("views/homepage.fxml", event);
    }
    
    // set course name
    public void setCourseName(String name) {
    	courseName.setText(name);
    }
    
}
