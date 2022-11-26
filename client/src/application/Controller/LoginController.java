package application.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.SQLiteConnection;
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
		
		// encrypt pw
		String encryptedPw = "";
		char[] chars = pw.toCharArray();
		int key = 6;
		for(char c : chars) {
			c -= key;
			encryptedPw += c;
		}
				
		try {
			if(loginModel.isLogin(usernameTextField.getText(), encryptedPw)) {
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

	
	@FXML
	public void forgotPwBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/reset_password.fxml");
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
