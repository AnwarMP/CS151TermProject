package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.SQLiteConnection;
import application.model.CardModel;
import application.model.CourseModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class SelectedCourseController implements Initializable {


    @FXML
    private AnchorPane courseBoard;

    @FXML
    private Button addCard;

    @FXML
    private GridPane notLearnedLayout;

    @FXML
    private GridPane learnedLayout;

    @FXML
    private Button returnBtn;
 
	private int row = 0, col = 0, i = 0;
    
    private CourseModel courseModel;
    private CardModel cardModel; 
    
	private Connection conn;
	private Statement stmt;

	// constructor
	public SelectedCourseController() throws SQLException {
		courseModel = new CourseModel();
		cardModel = new CardModel();
		
		conn = SQLiteConnection.Connector();
	}
	
    @FXML
    public void returnToHomepageAction(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("views/homepage.fxml");
	}
    
    @FXML
    public void addCardAction(ActionEvent event) throws IOException {
    	Main m = new Main();
		m.changeScene("views/create_card.fxml");
	}
    
    
    // set up selected course for rendering
    public void setUpSelectedCourse(CourseModel courseModel) throws IOException {
    	Main m = new Main();
		CourseItemController courseController = m.getCourseController();
		
		courseController.setData(courseModel);
		
		m.getPane().getChildren().remove(m.getPane().lookup("#editBtn"));
		m.getPane().getChildren().remove(m.getPane().lookup("#editIcon"));
		m.getPane().getChildren().remove(m.getPane().lookup("#selectCourseBtn"));
		courseBoard.getChildren().add(m.getPane());
		courseBoard.getChildren().addAll(m.getPane().getChildren());
    }
    
   
    @Override 
	public void initialize(URL location, ResourceBundle resources) {

		List<CardModel> cards;
		try {
			
			// selected course UI
			cards = new ArrayList<CardModel>(setUpCards());
			
			List<CardModel> learnedCards = new ArrayList<CardModel>();
			List<CardModel> notLearnedCards = new ArrayList<CardModel>();
			
			
			for(int i = 0; i < cards.size(); i++) {
				if(cards.get(i).getIsLearned() == 0)
					notLearnedCards.add(cards.get(i));
			}
			
			for(int i = 0; i < cards.size(); i++) {
				if(cards.get(i).getIsLearned() == 1)
					learnedCards.add(cards.get(i));
			}
			
			// Learned layout
			while(row < learnedLayout.getRowConstraints().size()) {
				while(col < learnedLayout.getColumnConstraints().size() && i < learnedCards.size()) {
					Main m = new Main("views/card_item.fxml");
					CardItemController cardController = m.getCardController();

					cardController.setData(learnedCards.get(i));
					
					learnedLayout.add(m.getPane(), col, row);

					col++;
					i++;
				}
				row++;
				col = 0;
			}
			
			int row = 0, col = 0, i = 0; 
			// Not learned layout
			while(row < notLearnedLayout.getRowConstraints().size()) {
				while(col < notLearnedLayout.getColumnConstraints().size() && i < notLearnedCards.size()) {
					Main m = new Main("views/card_item.fxml");
					CardItemController cardController = m.getCardController();

					cardController.setData(notLearnedCards.get(i));
					
					notLearnedLayout.add(m.getPane(), col, row);

					col++;
					i++;
				}
				row++;
				col = 0;
			}
			
			System.out.println("LAYOUT" + notLearnedLayout.getChildren());
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    
    private List<CardModel> setUpCards() throws SQLException, IOException {	
		stmt = conn.createStatement();
		List<CardModel> list = new ArrayList<CardModel>();
		
		ResultSet selectedCourseRes = stmt.executeQuery("SELECT * FROM selected_course");

    	courseModel.setCourseId(selectedCourseRes.getInt("courseNumber"));
    	courseModel.setCourseName(selectedCourseRes.getString("courseName"));
		
		setUpSelectedCourse(courseModel);
		
		// select all cards from the selected course -- to set up the data for the card component
		StringBuffer query = new StringBuffer("SELECT * FROM ");
		query.append(selectedCourseRes.getString("courseName"));
		query.append("_detail");
		
		
        ResultSet cardResult = stmt.executeQuery(query.toString()); 
        
        // create list of all cards for rendering
        while (cardResult.next()) {
	    	cardModel = new CardModel();
	    	cardModel.setTermName(cardResult.getString("cardName"));
	    	cardModel.setTermDefinition(cardResult.getString("definition"));
	    	cardModel.setIsLearned(cardResult.getInt("isLearned"));
	        list.add(cardModel);
	    }
        
        cardResult.close();
        stmt.close();
        conn.close();
        
		return list;
	}
    
}
