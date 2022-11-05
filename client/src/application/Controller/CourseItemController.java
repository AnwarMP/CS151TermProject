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
		Main m = new Main("views/edit_course.fxml");
		System.out.println("COURSE NAME IN COURSE ITEM");
		System.out.println(getCourseName());
		m.openNewWindow("views/edit_course.fxml", event);
//        controller.setFormCourseName(getCourseName()); // nameText val queried from db to display in input field

        // update data in DB -- manipulate fx:id for each edit btn, "rowId+edit"
        
	}
	
	@FXML
	private void deleteCourseBtnAction(ActionEvent event) throws IOException {
		System.out.println("DEL BTN Parent");
		Pane pane = (Pane) deleteBtn.getParent();
		
//		System.out.println(pane.getChildren());
		System.out.println(getCourseName());
		
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Main m;
		try {
			m = new Main("views/edit_course.fxml");
			EditCourseController editCourseController = (EditCourseController) m.getEditCourseController();
			System.out.println("EDIT CONTROLLER IN COURSE ITEM");
			System.out.println(getCourseName());
			editCourseController.setCourseNameLabel("HIII");
			editCourseController.setCourseId(getCourseId());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
