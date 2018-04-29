package hotel_management.Receptionist;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import hotel_management.Guest;
import hotel_management.hotel_database;
import hotel_management.receptionist;
import hotel_management.view.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class confirm_checkIn {
	 ObservableList<String> Guests= FXCollections.observableArrayList();
	 @FXML
	 public Button confirm;
	 @FXML
	 public Button close;
	 @FXML
	 ListView<String> allGuests;
	 @FXML
	 private void initialize()
	 {
		 List<Guest> checkInGuests= hotel_database.get_checkInguests();
		 
		 DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/d/uuuu");
		 for(Guest g:checkInGuests)
		 {
			
			String entry="Name->";
			entry=entry.concat(g.get_name());
			entry=entry.concat(" ");
			entry=entry.concat("DOB->");
			entry=entry.concat(g.get_dob().format(formatters));
			entry=entry.concat(" ");
			entry=entry.concat("Contact#->");
			entry=entry.concat(g.get_contactno());
			Guests.add(entry);
		 }
		 allGuests.setItems(Guests);
		 allGuests.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		 
	 }
	 
	 @FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
	 
	 @FXML 
	 private void confirm() throws IOException
	 {
		 ObservableList<Integer> guestIndex = allGuests.getSelectionModel().getSelectedIndices();
		 List<Integer> guestInd= new ArrayList<Integer>();
		 for(Integer i: guestIndex)
		 {
			 guestInd.add(i);
		 }
		 if(guestIndex.isEmpty())
		 {
			 AlertBox.show("Please select Guest");
		 }
		 else
		 {
			 receptionist r=new receptionist("R","201");
			int res= r.checkIn_guest(guestInd);
			if(res==1)
			{
				AlertBox.show("Check-In confirmed");
				Stage st= (Stage) close.getScene().getWindow();
				st.close();
			}
			else if(res==0)
			{
				AlertBox.show("Some Error in CheckIn");
			}
		 }
	 }
}
	 
