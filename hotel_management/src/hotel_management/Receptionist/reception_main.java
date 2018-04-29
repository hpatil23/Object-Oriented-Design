package hotel_management.Receptionist;

import java.io.IOException;

import hotel_management.Hotel_mgt;
import javafx.fxml.FXML;

public class reception_main {
	 @FXML
	 private void confirm_checkIn() throws IOException
	 {
		 Hotel_mgt.confirm_guestCheckIn();
	 }
	 
	 
	 @FXML
	 private void view_guests() throws IOException
	 {
		// call hotel_mgt function
		 Hotel_mgt.view_guests();
	 }
	 
	 @FXML
	 private void confirm_checkOut() throws IOException
	 {
		 Hotel_mgt.confirm_guestCheckOut();
	 }
	 
	 @FXML
	 private void view_feedback() throws IOException
	 {
		 Hotel_mgt.view_feedback();
	 }
}
