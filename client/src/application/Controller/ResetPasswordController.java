package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import application.model.AccountModel;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;


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
	private Button logoutBtn; 
	
	@FXML
	private Button accountPage;
	
	@FXML
	private Button homeButton;
	
	public AccountModel resetModel = new AccountModel(); 
	
	@FXML
	private void doneBtnAction(ActionEvent event) throws IOException {
		updatePassword();
	}
	
	@FXML
	public void homeBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/homepage.fxml");
	}
	
	/**
	 * Update password method
	 * @throws IOException
	 */
	public void updatePassword() throws IOException {
		Main m = new Main();

		String pw = newPasswordField.getText();
		String repw = reenterPasswordField.getText();
		String answer = answerTextField.getText();
		
		// if missing any input value, prompt user to fill all
		try {
			if(repw.isEmpty() || pw.isEmpty() || answerTextField.getText().isEmpty()) {
				emptyFields.setText("Please fill out all missing information.");
				//set the label to above
			}
			// mock data to check for existing accounts
			else if(!resetModel.updatePassword(pw, answer) || !repw.equals(pw)) {
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
	
	@FXML
	public void logoutBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/login.fxml");
	}
	
	@FXML
	public void myCoursesBtnOnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/homepage.fxml");
	}
	
	@FXML
	private void accountSettingsOnAction(ActionEvent event) throws IOException, SQLException{
		Main m = new Main();
		m.changeScene("views/AccountPage.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		String[] questions = {"In what city were you born?", "What is the name of your favorite pet?", "What is your mother's maiden name?",
				"What high school did you attend?", "What was your favorite food as a child?" };
		securityBox.getItems().addAll(questions);
	}
}
