package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import application.model.CourseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditCourseController implements Initializable {

	
	@FXML
    private Label courseModalTitle;

    @FXML
    private TextField courseName;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    private CourseItemController courseItemController;
    private CourseModel courseModel = new CourseModel();
    
    private String courseNameLabel = "HIII";
    private String courseId;
    @FXML
    void cancelBtnAction(ActionEvent event) {

    }

    void setCourseId(String courseId) {
    	this.courseId = courseId;
    }
    
    void setCourseNameLabel(String courseNameLabel) {
    	this.courseNameLabel = courseNameLabel;
    }
    
    
    @FXML
    void saveBtnAction(ActionEvent event) throws IOException, NumberFormatException, SQLException {
    	Main m = new Main();
    	
    	
    	System.out.println("COURSE ITEM NAME IN EDIT COURSE");
    	System.out.println(courseNameLabel);
    	System.out.println("COURSE ID IN EDIT COURSE");
    	System.out.println(courseId);
    	courseModel.updateCourse(Integer.parseInt(courseId), courseNameLabel, courseName.getText());
    	
    	
    	m.changeScene("views/homepage.fxml");

    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

    	
    	
	}


}
