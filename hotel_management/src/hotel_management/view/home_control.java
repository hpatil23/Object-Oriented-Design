package hotel_management.view;

import java.io.IOException;

import hotel_management.Hotel_mgt;
import javafx.fxml.FXML;

public class home_control {
	@FXML
	public void main_menu() throws IOException
	{
	
	String path_actor="view/Actors.fxml";
	Hotel_mgt.display_View(path_actor,"actors");
	Hotel_mgt.bp.setCenter(Hotel_mgt.actors);
		
	}

}
