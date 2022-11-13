package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.SQLiteConnection;
import application.model.CourseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CourseItemController implements Initializable {

	@FXML
	private Pane courseBackground;
	
	@FXML
	private Pane courseInfo;
	
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
    private Label editLabel;
    
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
    void saveEditBtnAction(ActionEvent event) throws IOException, NumberFormatException, SQLException {
    	
    	Main m = new Main();
    	
    	courseModel.updateCourse(Integer.parseInt(getCourseId()), getCourseName(), editTextField.getText());

    	for (Node child : courseInfo.getChildren()) {
            child.setVisible(!child.isVisible());
        }
    	m.changeScene("views/homepage.fxml");
    	
    }
	
	@FXML
    void cancelEditBtnAction(ActionEvent event) {
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
