package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import application.SQLiteConnection;
import application.model.CardModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SelectedCardItemController implements Initializable {

	@FXML
    private Pane cardItem;
	
	@FXML
    private AnchorPane termInfo;

    @FXML
    private Label termName;

    @FXML
    private Label termDefinition;

    @FXML
    private Text isLearned;

    @FXML
    private Button flipCardBtn;

    @FXML
    private CheckBox isLearnedCheckBox;

    @FXML
    private AnchorPane editCardLayout;

    @FXML
    private TextField cardNameTextField;

    @FXML
    private TextArea definitionTextField;

    @FXML
    private Label cardNameEditLabel;

    @FXML
    private Label definitionEditLabel;
    
    @FXML
    private Button saveEditBtn;

    @FXML
    private Button cancelEditBtn;
    
    @FXML
    private Button closeBtn;
    
    @FXML
    private Button editBtn;
    
    @FXML
    private Label errorMsg;

    @FXML
    private Button deleteBtn;
    
    private Connection connection;
    
    private CardModel cardModel;
	
    // constructor
    public SelectedCardItemController() throws SQLException {
    	cardModel = new CardModel();
    }
	
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
    
    @FXML
    private void flipCardBtnAction(ActionEvent event) {
    	for (Node child : termInfo.getChildren()) {
            child.setVisible(!child.isVisible());
        }
    }
    
    @FXML
    private void editCardBtnAction(ActionEvent event) {
    	if(!cardItem.getChildren().contains(editCardLayout))
    		cardItem.getChildren().add(editCardLayout);
    }
    
    @FXML
    private void cancelEditBtnAction(ActionEvent event) {
    	
    	cardItem.getChildren().remove(editCardLayout);
    }
    
    @FXML
    private void saveEditBtnAction(ActionEvent event) throws SQLException, IOException {
    	// update card name
    	if(!cardNameTextField.getText().isEmpty() && definitionTextField.getText().isEmpty()) {
    		cardModel.updateCardName(getTermName(), cardNameTextField.getText(), getTermDefinition());
    		termName.setText(cardNameTextField.getText());
    	} 
    	// update card definition
    	else if(cardNameTextField.getText().isEmpty() && !definitionTextField.getText().isEmpty()) {
    		cardModel.updateCardDefinition(getTermName(), definitionTextField.getText(), getTermDefinition());
    		termDefinition.setText(definitionTextField.getText());
    	} 
    	// update both name and definition
    	else if(!cardNameTextField.getText().isEmpty() && !definitionTextField.getText().isEmpty()) {
    		cardModel.updateCardInfo(getTermName(), cardNameTextField.getText(), definitionTextField.getText(), getTermDefinition());
    		termName.setText(cardNameTextField.getText());
    		termDefinition.setText(definitionTextField.getText());
    	} 
		 Main m = new Main();
		 cardModel.setSelectedCard(getTermName(), getTermDefinition(), Integer.parseInt(getIsLearned()));
		 m.openNewWindow("views/selected_card_page.fxml", event);
    }
    
    @FXML
    private void closeBtnAction(ActionEvent event) throws IOException, SQLException {
    	Main m = new Main();
    	m.openNewWindow("views/selected_course.fxml", event);
    }
    
    @FXML
    private void deleteBtnAction(ActionEvent event) throws SQLException, IOException {
    	Main m = new Main();
    	cardModel.deleteCard(getTermName(), getTermDefinition());
    	m.openNewWindow("views/selected_course.fxml", event);
    }
    
    // update learned status on selected
    @FXML
    private void checkIsLearned(ActionEvent event) throws SQLException {
    	if(isLearnedCheckBox.isSelected()) {
    		isLearned.setText("1");
    		
    	} else {
    		isLearned.setText("0");
    	}
    	// update isLearned in DB
    	cardModel.updateIsLearned(getTermName(), getTermDefinition(), Integer.parseInt(getIsLearned()));
    }
    
    // Check learned status and set state of checkbox based on status -- if learned, set checkbox to selected; otherwise, set checkbox to unselected
    public void renderLearnedStatus() throws SQLException {
		connection = SQLiteConnection.Connector();
		Statement stmt = connection.createStatement();
		
		ResultSet res = stmt.executeQuery("SELECT isLearned from selected_card");
		
		
		if(res.getInt("isLearned") == 1) {
			isLearnedCheckBox.setSelected(true);
		} else
			isLearnedCheckBox.setSelected(false);

		res.close();

		stmt.close();
		
		connection.close();
	}
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	try {
    		cardItem.getChildren().remove(editCardLayout);
			renderLearnedStatus();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
