package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import application.model.AccountModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AccountPage implements Initializable {
	@FXML
	private Label accountName;
	
	@FXML
	private Button DeleteAccount; 
	
	@FXML
	private Button changePassword; 
	
	@FXML
	private Button changeSecQuestion; 
	
	@FXML
	private Button homePage;
	
	@FXML
	private Button logoutBtn;
	
	@FXML
	private Button homeButton;
	
	public AccountModel accountPage = new AccountModel(); 

	public void logoutBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/login.fxml");
	}
	
	public void homeBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/homepage.fxml");
	}

	
	@FXML
	public void deleteAccountAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/delete_account.fxml");
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
