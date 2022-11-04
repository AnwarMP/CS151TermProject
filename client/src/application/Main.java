package application;
	
import java.io.IOException;
import java.net.URL;

import application.Controller.CourseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private static Stage stg;
	
	public CourseController courseController;
	public Pane pane;
	
	public Main() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("views/course_item.fxml"));
		pane = loader.load();
		courseController = (CourseController) loader.getController();
//		System.out.println(courseController);
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			stg = primaryStage;
			
			Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));

			
			
			System.out.println(getClass().getResource("views/course_item.fxml"));
			
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
	
	public CourseController getCourseController() {
		return courseController;
	}
	public void changeScene(String fxml) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
//		stg.getScene().setRoot(pane);
		Scene scene = new Scene(pane);
		stg.setScene(scene);
		stg.show();
	}
	
	public FXMLLoader openNewWindow(URL url) {
//	 {
//	     //ChildNode child;
//	    try {                    
//	        URL url = getClass().getResource(FXMLFile);
//	        FXMLLoader fxmlLoader = new FXMLLoader();
//	        fxmlLoader.setLocation(url);
//	        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
//	        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 
//
//	        pane.getChildren().clear();///name of pane where you want to put the fxml
//	        pane.getChildren().add(page);
//	    } 
//	    catch (IOException e) {
//	        e.printStackTrace();
//	    }
		
		FXMLLoader fxmlLoader = new FXMLLoader();
    	fxmlLoader.setLocation(url);
    	fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
    	return fxmlLoader;
	 }
	
	public static void main(String[] args) {
		launch(args);
	}
}
