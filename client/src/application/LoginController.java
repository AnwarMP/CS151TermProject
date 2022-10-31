package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


import javafx.event.ActionEvent;

public class LoginController {

	@FXML 
	private TextField usernameTextField;
	
	@FXML 
	private PasswordField passwordField;
	
	@FXML 
	private Button loginBtn;
	
	@FXML 
	private Label invalidLogin;
	
	@FXML
	private Hyperlink toSignUpBtn;
	
	/**
	 * Method to login on valid login inputs
	 * @param event
	 * @throws IOException
	 */
	public void loginBtnOnAction(ActionEvent event) throws IOException {
		checkLogin();
		Stage stage = (Stage) loginBtn.getScene().getWindow();
		stage.close();
	}
	
	public void checkLogin() throws IOException {
		Main m = new Main();
		String username = usernameTextField.getText();
		String pw = passwordField.getText();
		
		// mock username and pw for login
		if(username.toString().equals("admin") && pw.toString().equals("123456")) {
			m.changeScene("/homepage.fxml");
		} 
		// if username or pw field is empty, prompt user to fill all
		else if(username.isEmpty() || pw.isEmpty()) {
			invalidLogin.setText("Please enter missing input.");
		}
		// if account does not exist, prompt user to try again
		else {
			invalidLogin.setText("Invalid login! Please try again.");
		}
	}

	/**
	 * Method to navigate to sign up page from login page
	 * @param event
	 * @throws IOException
	 */
	public void toSignupBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("/register.fxml");
	}

}
