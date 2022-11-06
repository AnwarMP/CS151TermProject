package application.Controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ResetPasswordController {

	@FXML
	private Button doneBtn;
	
	@FXML
	private void doneBtnAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/login.fxml");
	}
}
