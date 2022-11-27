package application.Controller;

import java.io.IOException;
import java.sql.SQLException;

import application.Main;
import application.model.CourseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    	
    	Main m = new Main();
    	m.openNewWindow("views/homepage.fxml", event);
    }
    
    
    @FXML
    private void cancelBtnAction(ActionEvent event) throws IOException {
    	Main m = new Main();
    	m.openNewWindow("views/homepage.fxml", event);
    }
    
    
    @FXML
    public String getFormCourseName() {
    	return courseName.getText();
    }
    
}
