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
    
    private CourseModel courseModel = new CourseModel();
    
    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException, SQLException {
    	
    	courseModel.createCourse(courseName.getText());
    	
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
    	
    	System.out.println("FORM TEXT INSIDE CREATE COURSE");
    	System.out.println(getFormCourseName());
    }
    
    
    @FXML
    public String getFormCourseName() {
    	return courseName.getText();
    }
    
}
