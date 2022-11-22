package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.SQLiteConnection;
import application.model.ResetPasswordModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class AccountPage implements Initializable {
	@FXML
	private Label accountName;
	
	@FXML
	private Button changeUsername; 
	
	@FXML
	private Button changePassword; 
	
	@FXML
	private Button changeSecQuestion; 
	
	@FXML
	private Button homePage;
	
	public void logoutBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/login.fxml");
	}
	
	public void homeBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/homepage.fxml");
	}

	
	@FXML
	public void changeUsernameOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/change_username.fxml");
	}
	
	@FXML
	public void changePasswordOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/reset_password.fxml");
	}
	
	@FXML
	public void changeSecQuestionOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/security_question.fxml"); //change to security question fxml
	}
	
	@FXML
	private void accountSettingsOnAction(ActionEvent event) throws IOException, SQLException{
		Main m = new Main();
		m.changeScene("views/AccountPage.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
}
