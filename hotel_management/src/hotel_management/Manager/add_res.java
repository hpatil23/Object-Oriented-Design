
package hotel_management.Manager;

import java.io.IOException;
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

public class add_res {
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
	 public TextField newResource;
	 @FXML
	 public TextField newCost;
	 @FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
	 
	 @FXML
	 private void add() throws IOException
	 {
		 String resourceName= newResource.getText();
		 String resourceCost= newCost.getText();
		 if(resourceName.isEmpty())
		 {
			 AlertBox.show("Please add Resource Name");
		 }
		 else if(resourceCost.isEmpty())
		 {
			 AlertBox.show("Please add Resource Cost");
		 }
		 else {
			 try {
				 double cost=Double.parseDouble(resourceCost);
				 if(cost<=(double)0)
				 {
					 AlertBox.show("Cost Cannot be zero or Negative");
				 }
				 else {
				 manager m= new manager("M","101");
				 int result=m.add_resource(resourceName,cost );
				 
				 if(result==1)
				 {
					 AlertBox.show("Resource Added");
					 Stage st= (Stage) close.getScene().getWindow();
					 st.close();
				 }
				 else if(result==0)
				 {
					 AlertBox.show("Resource Already Present");
				 }
				 }
			 }
			 catch(Exception e)
			 {
				AlertBox.show("Resource Cost is Wrong");
			 }
		 
		 }
		 
	 }
	 
}
