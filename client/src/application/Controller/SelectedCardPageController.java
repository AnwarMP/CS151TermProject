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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class SelectedCardPageController implements Initializable {
	
	@FXML
    private Button prevBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private AnchorPane cardRegion;

    
	private Connection conn;
	private Statement stmt;
	private CardModel cardModel;
	
	// constructor
	public SelectedCardPageController() throws SQLException {
		cardModel = new CardModel();
		
		conn = SQLiteConnection.Connector();
	}
	

	// set up selected card info for rendering
	private CardModel setUpSelectedCard() throws SQLException {	
		stmt = conn.createStatement();
		
		ResultSet selectedCardRes = stmt.executeQuery("SELECT * FROM selected_card");

	    cardModel.setTermName(selectedCardRes.getString("cardName"));
	    cardModel.setTermDefinition(selectedCardRes.getString("definition"));
	    cardModel.setIsLearned(selectedCardRes.getInt("isLearned"));

        
	    selectedCardRes.close();
        stmt.close();
        conn.close();
        
		return cardModel;
	}
	
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		CardModel card;
		try {
			
			// selected card page UI
			card = setUpSelectedCard();
			Main m = new Main("views/selected_card_item.fxml");
			SelectedCardItemController cardFrontController = m.getSelectedCardFrontController();
			cardFrontController.setData(card);
			cardRegion.getChildren().add(m.getPane());
			cardRegion.setLeftAnchor(m.getPane(), 202.0);
			cardRegion.setRightAnchor(m.getPane(), 202.0);
			cardRegion.setTopAnchor(m.getPane(), 127.0);
			cardRegion.setBottomAnchor(m.getPane(), 127.0);
			
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
