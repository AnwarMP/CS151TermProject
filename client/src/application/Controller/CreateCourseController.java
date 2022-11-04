package application.Controller;

import java.io.IOException;

import application.Main;
import application.model.Course;
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
    
    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException {
    	// DB Commands
    	
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
