package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import application.model.LoginModel;
import application.model.SignUpModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class ResetPasswordController implements Initializable {
	@FXML 
	private PasswordField newPasswordField;
	
	@FXML 
	private PasswordField reenterPasswordField;
	
	@FXML 
	private ComboBox<String> securityBox;
	
	@FXML 
	private TextField answerTextField;
	
	@FXML
	private Button doneBtn;
	
	@FXML
	private Button myCoursesBtn;
	
	@FXML
	private Label emptyFields;
	
	@FXML
	private void doneBtnAction(ActionEvent event) throws IOException {
		checkTextFields();
	}
	
	public void logoutBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/login.fxml");
	}
	
	public void myCoursesBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/homepage.fxml");
	}
	
	public void checkTextFields() throws IOException{
		Main m = new Main();
		
		String newPassword = newPasswordField.getText();
		String reNewPassword = reenterPasswordField.getText();
		
		//if any fields are empty
		if(newPassword.isEmpty() || reNewPassword.isEmpty() || securityBox.getValue() == null || answerTextField.getText().isEmpty()) {
			emptyFields.setText("Please fill out all missing information.");
		} 
		else {
			m.changeScene("views/login.fxml");
		}
		
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String[] questions = {"In what city were you born?", "What is the name of your favorite pet?", "What is your mother's maiden name?",
				"What high school did you attend?", "What was your favorite food as a child?" };
		securityBox.getItems().addAll(questions);
	}
}
