package application.Controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

import application.*;
import application.model.CourseModel;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class HomePageController implements Initializable {
	
	@FXML
	private Button logoutBtn; 
	
	@FXML
	private GridPane courseLayout;
	
	@FXML
	private Button addCourseBtn;
	
	@FXML
	private Pane courseBackground;
	
	@FXML
	private Button accountPage;
	
	@FXML
	private Button homeButton;
	
	
	private int row = 0, col = 1, i = 0;
	
	private CourseModel courseModel;
	
	private Connection conn;
	private Statement stmt;
	
	// constructor
	public HomePageController() {
		conn = SQLiteConnection.Connector();
	}
	
	@FXML
	public void logoutBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/login.fxml");
	}

	@FXML
	public void homeBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/homepage.fxml");
	}


	// Render course items on load
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		courseModel = new CourseModel();

		List<CourseModel> courses;
		try {
			// create user course table
			courseModel.setupHomepage();

			courses = new ArrayList<CourseModel>(setUpCourses());
			
			while(row < courseLayout.getRowConstraints().size()) {
				while(col < courseLayout.getColumnConstraints().size() && i < courses.size()) {
					Main m = new Main();
					CourseItemController courseController = m.getCourseController();

					courseController.setData(courses.get(i));
					
					courseLayout.add(m.getPane(), col, row);

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

	@FXML
	private void resetPasswordBtnAction(ActionEvent event) throws IOException, SQLException{
		Main m = new Main();
		m.openNewWindow("views/reset_password.fxml", event);
	}
	
	@FXML
	private void accountSettingsOnAction(ActionEvent event) throws IOException, SQLException{
		Main m = new Main();
		m.changeScene("views/AccountPage.fxml");
	}
	
	// Add all courses of user to list for rendering
	private List<CourseModel> setUpCourses() throws SQLException {	
		stmt = conn.createStatement();
		List<CourseModel> list = new ArrayList<CourseModel>();
		
		ResultSet sessionRes = stmt.executeQuery("SELECT * FROM session");
		
		// get all course entries from username_courses table
		StringBuffer query = new StringBuffer("SELECT * FROM ");
		query.append(sessionRes.getString("username"));
		query.append("_courses");
		
		
        ResultSet courseResult = stmt.executeQuery(query.toString()); 
        
        // create list of all courses for rendering
        while (courseResult.next()) {
	    	courseModel = new CourseModel();
	    	courseModel.setCourseId(courseResult.getInt(1));
	    	courseModel.setCourseName(courseResult.getString("courseName"));
	        list.add(courseModel);
	    	
	    }
        
        courseResult.close();
        stmt.close();
        conn.close();
        
		return list;
	}
	
}