package hotel_management.Manager;

import java.io.IOException;
import java.util.List;

import hotel_management.Guest;
import hotel_management.hotel_database;
import hotel_management.service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class service_consumption {
	ObservableList<String> serv= FXCollections.observableArrayList();
	 @FXML
	 private Button close;
	
	 @FXML
	 private ListView<String> serv_com;
	 
	 @FXML
		private void initialize()
		{
		 List<service> curr_serv=hotel_database.services;
		 
		 String entry;
		 for(service s:curr_serv)
		 {
			 entry=s.get_name();
			 int total_guest=0;
			 for(Guest g: hotel_database.get_activeGuests())
			 {
				 if(s.get_name().equalsIgnoreCase(g.get_ServiceName()))
					 {
					 total_guest=total_guest+1;
					 }
			 }
			 entry=entry.concat("-->");
			 entry=entry.concat(Integer.toString(total_guest));
			 entry=entry.concat("/");
			 entry=entry.concat(Integer.toString(hotel_database.get_activeGuests().size()));
			 double perc;
			 if(hotel_database.get_activeGuests().size()==0)
			 {perc=0.0;
				 
			 }
			 else
				 {
				 perc=(((double)total_guest)/hotel_database.get_activeGuests().size())*100.0;
				 }
			 entry=entry.concat("("+Double.toString(perc)+"%)");
			 serv.add(entry);
		 }
		 serv_com.setItems(serv);
		 
		}
	 
	 @FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
}
