package hotel_management.Manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hotel_management.hotel_database;
import hotel_management.manager;
import hotel_management.resource;
import hotel_management.service;
import hotel_management.view.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class remove_service {
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
			allService.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		}
	 
	 @FXML
	 public Button close;
	 @FXML
	 public Button remove;
	 
	 @FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
	 
	 @FXML
	 private void remove() throws IOException
	 {
		 ObservableList<Integer> ser_1 = allService.getSelectionModel().getSelectedIndices();
		 
		 List<Integer> index= new ArrayList<Integer>();
		 
		 if(ser_1.isEmpty())
		 {
			 AlertBox.show("Please Select any service");
		 }
		 
		 else
		 {	
			for(Integer i:ser_1)
			{
				index.add(i);
			}
			 manager m= new manager("M","101");
			 int result=m.remove_service(index);
			 if(result==1)
			 {
				 AlertBox.show("Services Removed Successfully");
				 Stage st= (Stage) close.getScene().getWindow();
				 st.close();
			 }
			 else if(result==0)
			 {
				 AlertBox.show("Error in Service Remove");
				 Stage st= (Stage) close.getScene().getWindow();
				 st.close();
			 }
		 }
	 }
	 
}
