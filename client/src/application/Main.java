package application;
	
import java.io.IOException;

import application.Controller.CourseItemController;
import application.Controller.CreateCourseController;
import application.Controller.LoginController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private static Stage stg;
	
	public CourseItemController courseController;
	public CreateCourseController createCourseController;
	public Pane pane;
	
	public Main() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/course_item.fxml"));
		pane = loader.load();
		courseController = (CourseItemController) loader.getController();
		
	}
//	
	public Main(String fxml) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		loader.load();
		
        if(fxml.equals("views/create_course.fxml"))
        {
        	createCourseController = (CreateCourseController)loader.getController();
        }
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stg = primaryStage;
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("views/login.fxml"));
			
			Parent root = (Parent) loader.load();
			
			Scene scene = new Scene(root,1440,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setTitle("ProDex");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pane getPane() {
		return pane;
	}
	
	public void setCourseController(CourseItemController courseController) {
		this.courseController = courseController;
	}
	
	public CourseItemController getCourseController() {
		return courseController;
	}
	
	public CreateCourseController getCreateCourseController() {
		return createCourseController;
	}
	
	
	public void changeScene(String fxml) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(fxml));
		Scene scene = new Scene(parent);
		stg.setScene(scene);
		stg.show();
	}
	
	public void openNewWindow(String fxml, ActionEvent event) throws IOException {
		
		Parent add_parent = FXMLLoader.load(getClass().getResource(fxml));
		Scene add_scene = new Scene(add_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.hide();
		app_stage.setScene(add_scene);
		app_stage.show();
	 }
	
	public static void main(String[] args) {
		launch(args);
	}
}
