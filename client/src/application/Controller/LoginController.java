package application.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import application.Main;
import application.model.LoginModel;
import javafx.event.ActionEvent;

public class LoginController implements Initializable {

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
	
	@FXML
	private Hyperlink forgotPwBtn;
	
	public LoginModel loginModel = new LoginModel();
	
	@FXML
	private Label isConnected;
	
	
	
	/**
	 * Method to login on valid login inputs
	 * @param event
	 * @throws Exception 
	 */
	public void loginBtnOnAction(ActionEvent event) throws Exception {
		checkDbLogin();
	}
	
	/**
	 * Method to run login 
	 * @throws IOException
	 */
	public void checkDbLogin() throws IOException {
		Main m = new Main();
		String username = usernameTextField.getText();
		String pw = passwordField.getText();
				
		try {
			if(loginModel.isLogin(usernameTextField.getText(), pw)) {
				loginModel.setSession(username);
				invalidLogin.setText("Success");
				m.changeScene("views/homepage.fxml");
			} else if(username.isEmpty() || pw.isEmpty()) {
				invalidLogin.setText("Please enter missing input.");
			} else { 
				invalidLogin.setText("Invalid login! Please try again.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(loginModel.isDbConnected()) {
			isConnected.setText("connected");
		} else {
			isConnected.setText("Not Connected");
		}
		
	}
	

}
