package hotel_management.view;

import java.io.IOException;
import java.util.List;

import hotel_management.hotel_database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class view_feedback {
	
	ObservableList<String> feedbacks= FXCollections.observableArrayList();
	
	@FXML
	 public Button close;
	@FXML
	public ListView<String> feeds;
	
	@FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
	
	 @FXML
	 private void initialize()
	 {
		 List<String> fs=hotel_database.get_feedbacks();
		 for(String s:fs)
		 {
			 String[]s2=s.split("#");
			 feedbacks.add(s2[0]);
			 feedbacks.add(s2[1]);
			 feedbacks.add(s2[2]);
		 }
		 feeds.setItems(feedbacks);
	 }

}
