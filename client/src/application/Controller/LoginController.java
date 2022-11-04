package application.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
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
	
	private Connection conn;
	
	
	/**
	 * Method to login on valid login inputs
	 * @param event
	 * @throws Exception 
	 */
	public void loginBtnOnAction(ActionEvent event) throws Exception {
		checkLogin();
	}
	
	
	public void checkLogin() throws Exception {
		Main m = new Main();
		String username = usernameTextField.getText();
		String pw = passwordField.getText();
		
		// mock username and pw for login
		if(username.toString().equals("admin") && pw.toString().equals("123456")) {
			invalidLogin.setText("Success");
			m.changeScene("views/homepage.fxml");
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
		m.changeScene("views/register.fxml");
	}
	

}
