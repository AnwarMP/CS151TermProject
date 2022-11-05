package application.Controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.model.CourseModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;

public class HomePageController implements Initializable {
	
	@FXML
	private Button logoutBtn; 
	
	@FXML
	private GridPane courseLayout;
	
	@FXML
	private Button addCourseBtn;
	
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
		List<CourseModel> courses = new ArrayList<CourseModel>(courses());
		System.out.println(courseLayout.getRowConstraints().size());
		
		while(row < courseLayout.getRowConstraints().size()) {
			while(col < courseLayout.getColumnConstraints().size() && i < courses.size()) {
				try {
					Main m = new Main();
					CourseItemController courseController = m.getCourseController();
					courseController.setData(courses.get(i));
					courseLayout.add(m.getPane(), col, row);
					// if dleteBtn is clicked, remove course from courseLayout, decrement col
					//courseLayout.getChildren().remove(i)
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
	
	@FXML
	private void addCourseBtnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.openNewWindow("views/create_course.fxml", event);
	}

	
	// mock data for courses
	private List<CourseModel> courses() {
		List<CourseModel> ls = new ArrayList<CourseModel>();
		CourseModel course = new CourseModel();
		
		Integer id = 0;

		course.setCourseName("ISE 130");
		//course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		course = new CourseModel();
		course.setCourseName("CS 151");
		//course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		course = new CourseModel();
		course.setCourseName("CMPE 133");
		//course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		course = new CourseModel();
		course.setCourseName("CMPE 149");
		//course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		course = new CourseModel();
		course.setCourseName("CMPE 149");
		//course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		course = new CourseModel();
		course.setCourseName("CMPE 149");
		//course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		course = new CourseModel();
		course.setCourseName("CMPE 149");
		//course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		course = new CourseModel();
		course.setCourseName("CMPE 149");
		//course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		course = new CourseModel();
		course.setCourseName("CMPE 149");
		//course.setCourseId(Integer.toString(id++));
		ls.add(course);
		
		System.out.println(ls.get(1).getCourseName());
		return ls;
	}
}
