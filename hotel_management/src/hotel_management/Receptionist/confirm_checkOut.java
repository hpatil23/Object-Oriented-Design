package hotel_management.Receptionist;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import hotel_management.Guest;
import hotel_management.Hotel_mgt;
import hotel_management.hotel_database;
import hotel_management.receptionist;
import hotel_management.view.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class confirm_checkOut {
	 ObservableList<String> Guests= FXCollections.observableArrayList();
	 @FXML
	 public Button confirm;
	 @FXML
	 public Button close;
	 @FXML
	 ListView<String> allGuests;
	 @FXML
	 public void initialize()
	 {
		 List<Guest> checkOutGuests= hotel_database.get_checkOutReq();
		 
		 DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/d/uuuu");
		 for(Guest g:checkOutGuests)
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
		 
	 }
	 
	 @FXML
	public void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
	 
	 public void refresh()
	 {
		 allGuests.getItems().clear();
 List<Guest> checkOutGuests= hotel_database.get_checkOutReq();
		 
		 DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/d/uuuu");
		 for(Guest g:checkOutGuests)
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
			int res= r.checkOut_guest(guestInd);
			if(res==1)
			{
				Hotel_mgt.bill_generate();
				refresh();
			}
			else if(res==0)
			{
				AlertBox.show("Some Error in Checkout");
			}
			else if(res==-1)
			{
				AlertBox.show("Checkout Date cannot come before CheckIn date");
			}
		 }
	 }

}
