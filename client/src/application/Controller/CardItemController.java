package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.model.CardModel;
import application.model.CourseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CardItemController implements Initializable {

	@FXML
	private Pane cardItem;
	
	@FXML
	private Button selectCardBtn;
	
	@FXML
	private Text termName;
	
	@FXML
	private Text termDefinition;
	
	@FXML
	private Text isLearned;
	
	CardModel cardModel;
	
	public CardItemController() throws SQLException {
		cardModel = new CardModel();
	}
	
	// Set data of the current index card component
	public void setData(CardModel card) {
		termName.setText(card.getTermName());
		termDefinition.setText(card.getTermDefinition());
		isLearned.setText(Integer.toString(card.getIsLearned()));	
    }
	
    public String getTermName() {
    	return termName.getText();
    }
    
    public String getTermDefinition() {
    	return termDefinition.getText();
    }
    
    public String getIsLearned() {
    	return isLearned.getText();
    }
    
    // Set the selected card
	@FXML
	private void selectCardAction(ActionEvent event) throws IOException, SQLException {
		Main m = new Main();
		cardModel.setSelectedCard(getTermName(), getTermDefinition(), Integer.parseInt(getIsLearned()));
		m.openNewWindow("views/selected_card_page.fxml", event);
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
