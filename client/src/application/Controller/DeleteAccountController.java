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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DeleteAccountController implements Initializable{
	@FXML
	private Button doneBtn;
	
	@FXML
	private TextField Username;
	
	@FXML
	private TextField Password;
	
	@FXML
	private ComboBox<String> securityBox;
	
	@FXML 
	private TextField answerTextField;
	
	@FXML
	private Label emptyFields;
	
	@FXML
	private Button logoutBtn; 
	
	@FXML
	private Button homeButton;
	
	private AccountModel model = new AccountModel();
	
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
	
	public void homeBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/homepage.fxml");
	}

	
	@FXML
	private void doneBtnAction(ActionEvent event) throws IOException {
		updatePassword();
	}
	
	public void updatePassword() throws IOException {
		Main m = new Main();

		String user= Username.getText();
		String pw = Password.getText();
		String answer = answerTextField.getText();
		
		
		// if missing any input value, prompt user to fill all
		try {
			if(user.isEmpty() || pw.isEmpty() || answerTextField.getText().isEmpty()) {
				emptyFields.setText("Please fill out all missing information.");
				//set the label to above
			} 
			// mock data to check for existing accounts
			else if(!model.deleteAccount(pw, user, answer)) {
				emptyFields.setText("Incorrect information");
				//set label above to 'wrong answer' 
			}
			// if username is new and all info are filled out, redirect to login
			else {
				m.changeScene("views/login.fxml");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] questions = {"In what city were you born?", "What is the name of your favorite pet?", "What is your mother's maiden name?",
				"What high school did you attend?", "What was your favorite food as a child?" };
		securityBox.getItems().addAll(questions);
//		
	}
}