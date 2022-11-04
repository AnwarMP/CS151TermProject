package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CourseItemController implements Initializable {

	@FXML
	private Pane courseBackground;
	
    @FXML
    private Label courseName;

    @FXML
    private Label courseId;

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;

    public void setData(Course course) {
    	courseName.setText(course.getCourseName());
    	courseId.setText(course.getCourseId());
    }
    
	
	@FXML
	private void editCourseBtnAction(ActionEvent event) throws IOException {
		Main m = new Main("views/create_course.fxml");
		m.openNewWindow("views/create_course.fxml", event);
		
		CreateCourseController controller = m.getCreateCourseController();
//		 System.out.println("INSIDE COURSE ITEM");
//	        System.out.println(controller);
        controller.setCourseName("nameText"); // nameText val queried from db to display in input field
	}
	
	@FXML
	private void deleteCourseBtnAction(ActionEvent event) throws IOException {
//		System.out.println("DEL BTN Parent");
//		Pane pane = (Pane) deleteBtn.getParent();
//		
//		System.out.println(pane.getChildren());
		
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
}
