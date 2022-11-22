package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChangeUsername implements Initializable{
	@FXML
	private Button doneBtn;
	
	@FXML
	private TextField newUsername;
	
	@FXML
	private TextField reenterNewUsername;
	
	@FXML
	private ComboBox<String> securityBox;
	
	@FXML 
	private TextField answerTextField;
	
	@FXML
	private Label emptyFields;
	
	@FXML
	private Button logoutBtn; 
	
	@FXML
	private void accountSettingsOnAction(ActionEvent event) throws IOException, SQLException{
		Main m = new Main();
		m.changeScene("views/AccountPage.fxml");
	}
	
	public void logoutBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/login.fxml");
	}
	
	public void changeUsernameOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/change_username.fxml");
	}
	
	@FXML
	private void doneBtnAction(ActionEvent event) throws IOException {
	
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] questions = {"In what city were you born?", "What is the name of your favorite pet?", "What is your mother's maiden name?",
				"What high school did you attend?", "What was your favorite food as a child?" };
		securityBox.getItems().addAll(questions);
//		
	}
}
