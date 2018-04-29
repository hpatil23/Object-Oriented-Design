package hotel_management.Manager;

import java.io.IOException;
import java.util.List;


import hotel_management.hotel_database;
import hotel_management.resource;
import hotel_management.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.stage.Stage;

public class services {
	ObservableList<String> serv= FXCollections.observableArrayList();
	 @FXML
	 ListView<String> allService;
	 @FXML
	private void initialize()
	{
		String list;
		List<service> ser=hotel_database.get_service();
		for(service s:ser)
		{
	    list=s.get_name();
	    list=list.concat(" : (");
	    for(resource r: s.resources)
	    {
	    	list=list.concat(r.get_resourceName());
	    	
	    	list=list.concat(", ");
	    }
	    list=list.concat(")");
		serv.add(list);
		
		}
		allService.setItems(serv);
	}
	 @FXML
	 public Button close;
	 @FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
}
