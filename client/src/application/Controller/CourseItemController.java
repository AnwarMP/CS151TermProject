package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import application.model.CourseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CourseItemController implements Initializable {

	@FXML
	private Pane courseItem;
	
	@FXML
	private Pane courseInfo;
	
	@FXML
	private Pane selectedPane;
	
    @FXML
    private Label courseName;

    @FXML
    private Label courseId;

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;
    
    @FXML
    private Button saveEditBtn;
    
    @FXML
    private Button cancelEditBtn;
    
    @FXML
    private TextField editTextField;
    
    @FXML
    private Button selectCourseBtn;
    
    @FXML
    private Label editLabel;
    
    @FXML
    private ImageView editIcon;
    
    private CourseModel courseModel = new CourseModel();
    
    public void setData(CourseModel course) {
    	courseName.setText(course.getCourseName());
    	courseId.setText(Integer.toString(course.getCourseId()));
    }
    
    public String getCourseName() {
    	return courseName.getText();
    }
    
    public String getCourseId() {
    	return courseId.getText();
    }
    
    // Set the current selected course
    @FXML
    private void selectCourseAction(ActionEvent event) throws IOException, SQLException {
    	Main m = new Main();
    	courseModel.setSelectedCourse(Integer.parseInt(getCourseId()), getCourseName());
    	m.openNewWindow("views/selected_course.fxml", event);
    	
    }

	
	@FXML
	private void editCourseBtnAction(ActionEvent event) throws IOException, SQLException {
		for (Node child : courseInfo.getChildren()) {
            child.setVisible(!child.isVisible());
        }
	}
	
	/**
	 * Edit course info
	 * @param event
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	@FXML
	private void saveEditBtnAction(ActionEvent event) throws IOException, NumberFormatException, SQLException {
    	
    	Main m = new Main();
    	
    	courseModel.updateCourse(Integer.parseInt(getCourseId()), getCourseName(), editTextField.getText());

    	for (Node child : courseInfo.getChildren()) {
            child.setVisible(!child.isVisible());
        }
    	m.changeScene("views/homepage.fxml");
    	
    }
	
	@FXML
    private void cancelEditBtnAction(ActionEvent event) throws IOException {
		for (Node child : courseInfo.getChildren()) {
            child.setVisible(!child.isVisible());
        }
    }

	
	@FXML
	private void deleteCourseBtnAction(ActionEvent event) throws IOException, SQLException {
		courseModel.deleteCourse(Integer.parseInt(courseId.getText()), courseName.getText());
		
		Main m = new Main();
    	m.openNewWindow("views/homepage.fxml", event);
			
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	
}
