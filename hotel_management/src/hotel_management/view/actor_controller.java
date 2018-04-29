package hotel_management.view;


import java.io.IOException;

import hotel_management.Hotel_mgt;
import javafx.fxml.FXML;

public class actor_controller {
	
	@FXML
	private void display_managerMenu() throws IOException
	{
		Hotel_mgt.display_manager();
	}
	@FXML
	private void display_receptionistMenu() throws IOException
	{
		Hotel_mgt.display_receptionist();
	}
	
	@FXML
	private void display_guestsMenu() throws IOException
	{
		Hotel_mgt.display_guests();
	}
	
}
