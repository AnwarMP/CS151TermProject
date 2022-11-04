package application.Controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.model.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomePageController implements Initializable {
	
	@FXML
	private Button logoutBtn; 
	
	@FXML
	private GridPane courseLayout;
	
	@FXML
	private Pane courseBackground;
	
	private int row = 0, col = 1, i = 0;
	public void logoutBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/login.fxml");
	}


	// adding course item component to list
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Course> courses = new ArrayList<Course>(courses());
		System.out.println(courses);
		while(row < 3) {
			while(col < 3 && i < courses.size()) {
					try {
						Main m = new Main();
						CourseController courseController = m.getCourseController();
						System.out.println(m.getPane());
						courseController.setData(courses.get(i));
						courseLayout.add(m.getPane(), col, row);
				
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					col++;
					i++;
				}
				row++;
				col = 0;
			}
		
	}
	
	// mock data for courses
	private List<Course> courses() {
		List<Course> ls = new ArrayList<Course>();
		Course course = new Course();
		
		Integer id = 0;
				
		course.setCourseName("ISE 130");
		course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		course = new Course();
		course.setCourseName("CS 151");
		course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		course = new Course();
		course.setCourseName("CMPE 133");
		course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		course = new Course();
		course.setCourseName("CMPE 149");
		course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		System.out.println(ls.get(1).getCourseName());
		return ls;
	}
}
