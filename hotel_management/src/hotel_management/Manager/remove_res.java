package hotel_management.Manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hotel_management.hotel_database;
import hotel_management.manager;
import hotel_management.resource;
import hotel_management.view.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class remove_res {
	ObservableList<String> res= FXCollections.observableArrayList();
	 @FXML
	 ListView<String> allResource;
	 @FXML
	 private void initialize()
		{
			String list;
			List<resource> allRes=hotel_database.get_resource();
			for(resource r:allRes)
			{
		    list=r.get_resourceName();
		    
			res.add(list);
			}
			
			allResource.setItems(res);
			allResource.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
		 ObservableList<Integer> ser_1 = allResource.getSelectionModel().getSelectedIndices();
		 
		 List<Integer> index= new ArrayList<Integer>();
		 
		 if(ser_1.isEmpty())
		 {
			 AlertBox.show("Please Select any Resource");
		 }
		 
		else
		 {	
			for(Integer i:ser_1)
			{
				index.add(i);
			}
			 manager m= new manager("M","101");
			 int result=m.remove_resource(index);
			 if(result==1)
			 {
				 AlertBox.show("Resource Removed Successfully");
				 Stage st= (Stage) close.getScene().getWindow();
				 st.close();
			 }
			 else if(result==0)
			 {
				 AlertBox.show("Error in Resource Remove");
				 Stage st= (Stage) close.getScene().getWindow();
				 st.close();
			 }
		 }
	 }

}
