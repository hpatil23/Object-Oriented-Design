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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class add_service {
	
	ObservableList<String> res= FXCollections.observableArrayList();
	 @FXML
	 ListView<String> allres;
	 @FXML
	private void initialize()
	{
		List<resource> res_temp=hotel_database.get_resource();
		for(resource r:res_temp)
		{
		res.add(r.get_resourceName());
		}
		allres.setItems(res);
		allres.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	 
	 @FXML
	 public Button close;
	 @FXML
	 public Button add;
	 
	 @FXML
	 public TextField newService;
	 @FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
	 
	 @FXML
	 private void add() throws IOException
	 {
		 String serviceName= newService.getText();
		 ObservableList<Integer> res = allres.getSelectionModel().getSelectedIndices();
		 if(serviceName.isEmpty())
		 {
			 AlertBox.show("Please add service Name");
		 }
		 else if(res.size()==0)
		 {
			 AlertBox.show("Please select at least one Resource");
		 }
		 else {
		 List<Integer> resourceIndex= new ArrayList<Integer>();
		 for(Integer i: res)
		 {
			 resourceIndex.add(i);
		 }
		 manager m= new manager("M","101");
		 int result=m.add_service(serviceName, resourceIndex);
		 
		 if(result==1)
		 {
			 AlertBox.show("Service Added");
			 Stage st= (Stage) close.getScene().getWindow();
			 st.close();
		 }
		 else if(result==0)
		 {
			 AlertBox.show("Service Already Present");
		 }
		 }
		 
	 }

}
