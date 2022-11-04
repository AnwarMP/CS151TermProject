package application.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CourseController implements Initializable {

    @FXML
    private Label courseName;

    @FXML
    private Label courseId;

    @FXML
    private ImageView editBtn;

    @FXML
    private ImageView deleteBtn;

    public void setData(Course course) {
    	courseName.setText(course.getCourseName());
    	courseId.setText(course.getCourseId());
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
}
