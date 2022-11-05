package application.Controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import application.Main;
import application.SQLiteConnection;
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
	
	private Connection conn;
	private Statement stmt;
	
	public void logoutBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/login.fxml");
	}


	// adding course item component to list
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		List<CourseModel> courses;
		try {
			courses = new ArrayList<CourseModel>(setUpCourses());
			System.out.println(courseLayout.getRowConstraints().size());
			
			while(row < courseLayout.getRowConstraints().size()) {
				while(col < courseLayout.getColumnConstraints().size() && i < courses.size()) {
					Main m = new Main();
					CourseItemController courseController = m.getCourseController();
					
					courseController.setData(courses.get(i));
					courseLayout.add(m.getPane(), col, row);
					// if dleteBtn is clicked, remove course from courseLayout, decrement col
					//courseLayout.getChildren().remove(i)
					col++;
					i++;
				}
				row++;
				col = 0;
			}
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	@FXML
	private void addCourseBtnAction(ActionEvent event) throws IOException, SQLException {
		Main m = new Main();
		m.openNewWindow("views/create_course.fxml", event);
	}

	
	

	private List<CourseModel> setUpCourses() throws SQLException {	
		conn = SQLiteConnection.Connector();
		stmt = conn.createStatement();
		List<CourseModel> list = new ArrayList<CourseModel>();
		CourseModel courseModel;
		
		ResultSet sessionRes = stmt.executeQuery("SELECT * FROM session");
		
		// get all course entries from username_courses table
		StringBuffer query = new StringBuffer("SELECT * FROM ");
		query.append(sessionRes.getString("username"));
		query.append("_courses");
		
		
        ResultSet courseResult = stmt.executeQuery(query.toString()); 
        
        // query all results from table and add to list
        while (courseResult.next()) {
	    	courseModel = new CourseModel();
	    	courseModel.setCourseId(courseResult.getInt(1));
//	    	System.out.println(courseResult.getInt(1));
	    	courseModel.setCourseName(courseResult.getString("courseName"));
	        list.add(courseModel);
	    	
	    }
        
		return list;
	}
	
}