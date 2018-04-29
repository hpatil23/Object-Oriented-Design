package hotel_management.view;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import hotel_management.Guest;
import hotel_management.employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class view_guests {
	ObservableList<String> guests= FXCollections.observableArrayList();
	@FXML private RadioButton currGuests;
	@FXML private RadioButton allGuests;
	@FXML ListView<String> guestView;
	 @FXML
	 public Button close;
	@FXML
	public void guestShow(ActionEvent e)
	{     guestView.getItems().clear();
	      employee emp = new employee("e","101");
		 DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/d/uuuu");
		if(currGuests.isSelected())
		{
			
			List<Guest> curr = emp.find_guest(1);
			for(Guest g:curr)
			 {
				
				String entry="Name->";
				entry=entry.concat(g.get_name());
				entry=entry.concat(" ");
				entry=entry.concat("DOB->");
				entry=entry.concat(g.get_dob().format(formatters));
				entry=entry.concat(" ");
				entry=entry.concat("Contact#->");
				entry=entry.concat(g.get_contactno());
				guests.add(entry);
			 }
			 guestView.setItems(guests);
		}
	   if(allGuests.isSelected())
		{
			List<Guest> all = emp.find_guest(0);
		for(Guest g:all)
		 {
			
			String entry="Name->";
			entry=entry.concat(g.get_name());
			entry=entry.concat(" ");
			entry=entry.concat("DOB->");
			entry=entry.concat(g.get_dob().format(formatters));
			entry=entry.concat(" ");
			entry=entry.concat("Contact#->");
			entry=entry.concat(g.get_contactno());
			guests.add(entry);
		 }
		 guestView.setItems(guests);
			
		}
	}
	
	 @FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
	 @FXML
	 private void initialize()
	 {guestView.setItems(null);
	 employee emp = new employee("e","101");
	 DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/d/uuuu");
	 List<Guest> curr = emp.find_guest(1);
		for(Guest g:curr)
		 {
			
			String entry="Name->";
			entry=entry.concat(g.get_name());
			entry=entry.concat(" ");
			entry=entry.concat("DOB->");
			entry=entry.concat(g.get_dob().format(formatters));
			entry=entry.concat(" ");
			entry=entry.concat("Contact#->");
			entry=entry.concat(g.get_contactno());
			guests.add(entry);
		 }
		 guestView.setItems(guests);
		 
	 }
}
