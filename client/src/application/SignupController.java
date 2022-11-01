package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController implements Initializable {
	
	@FXML 
	private TextField usernameTextField;
	
	@FXML 
	private PasswordField passwordField;
	
	@FXML 
	private Button signupBtn;
	
	@FXML 
	private Label invalidSignup;
	
	@FXML 
	private ComboBox<String> securityBox;
	
	@FXML 
	private TextField answerTextField;
	
	@FXML 
	private Hyperlink toLoginBtn;
	
	/**
	 * Method to sign up on valid signup inputs
	 * @param event
	 * @throws IOException
	 */
	public void signupBtnOnAction(ActionEvent event) throws IOException {
		checkSignup();
	}
	
	public void checkSignup() throws IOException {
		String username = usernameTextField.getText();
		Main m = new Main();
		
		// mock data to check for existing accounts
		
		// if missing any input value, prompt user to fill all
		if(username.isEmpty() || passwordField.getText().isEmpty() || securityBox.getValue() == null || answerTextField.getText().isEmpty()) {
			invalidSignup.setText("Please fill out all missing information.");
		} 
		else if(username.toString().equals("admin")) {
			invalidSignup.setText("Account already exists.");
		}
		// if username is new and all info are filled out, redirect to login
		else {
			m.changeScene("/login.fxml");
		}
	}
	
	/**
	 * Method to navigate to login page from sign up page
	 * @param event
	 * @throws IOException
	 */
	public void toLoginBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("/login.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String[] questions = {"In what city were you born?", "What is the name of your favorite pet?", "What is your mother's maiden name?",
				"What high school did you attend?", "What was your favorite food as a child?" };
		securityBox.getItems().addAll(questions);
	}
}
