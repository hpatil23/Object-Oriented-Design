package hotel_management.Guests;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import hotel_management.Guest;
import hotel_management.hotel_database;
import hotel_management.service;
import hotel_management.view.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Guest_Details_control {
	ObservableList<String> ser= FXCollections.observableArrayList();
	@FXML
	private TextField name;
	@FXML 
	private TextField contactno;
	@FXML
	private DatePicker DOB;
	@FXML 
	private TextField adrs;
	@FXML
	private DatePicker	checkIn;
	@FXML
	private ChoiceBox<String> ser_sele;
	
	 @FXML
	 public Button close;
	 @FXML
	 public Button add;
	 
	 @FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
	 
@FXML
	private void initialize() 
	{
		List<service> serv=hotel_database.get_service();
		for(service s:serv)
		{ 
			ser.add(s.get_name());
		}
		String temp=ser.get(0);
		ser_sele.setValue(temp);
		ser_sele.setItems(ser);
	}
@FXML
	private void submit() 
	{int result=-99;
	// data read from the form 
	 String guestName= name.getText();
	 String contn=contactno.getText();
     String addrs=adrs.getText();
     LocalDate dob;
     //=DOB.getValue();
     LocalDate checkIndate;
    // =checkIn.getValue();
     String serviceName=ser_sele.getValue();
     if(guestName.isEmpty())
     {
    	 AlertBox.show("Please enter Name");
     }
     else if (contn.isEmpty())
     {
    	 AlertBox.show("Please enter Contact Number");
     }
     else if (addrs.isEmpty())
     {
    	 AlertBox.show("Please enter Address");
     }
     else if(null==DOB.getValue())
     {
    	 AlertBox.show("Please enter Date of Birth");
     }
     else if(null==checkIn.getValue())
     {
    	 AlertBox.show("Please enter Check In date");
     }
     else if(serviceName.isEmpty())
     {
    	 AlertBox.show("Please select Service");
     }
     else
     {  // check if contact no is 10 digit long and numerical
    	 try {
    		  long num=Long.parseUnsignedLong(contn);
    		 if(num<1000000000L || num>9999999999L)
    		 {
    			 AlertBox.show("Contact # should be 10 digits long");
    		 }
    		 else
    		 {
    			 dob=DOB.getValue();
    			 checkIndate=checkIn.getValue();
    			 if(dob.equals(checkIndate)||dob.isAfter(checkIndate))
    			 {
    				 AlertBox.show("Conflict in DOB and CheckIn date");
    			 }
    			 else 
    			 {
    				 // Proceed with check-In
    				 Guest g=new Guest();
    				 g.set_name(guestName);
    				 g.set_adrs(addrs);
    				 g.set_contactno(contn);
    				 g.set_dob(dob);
    				 g.set_checkInDate(checkIndate);
    				 g.set_serviceName(serviceName);
    				 result= g.checkin_request();  // request is initiated

    				 // check the return for AlertBox
    				
    			 }
    		 }
    	 }
    	 catch(Exception e)
    	 {
    		 AlertBox.show("Contact no is Wrong");
    	 }
    	 
     }
     
     if(result==1)
	 {
		 AlertBox.show("Check-In Request Submitted");
		 Stage st= (Stage) close.getScene().getWindow();
		 st.close();
	 }
	 else if(result==0)
	 {
		 AlertBox.show("Check-In Request Already In System");
	 }
	 else if(result==-1)
	 {
		 AlertBox.show("Error in Request Submission");
	 }
	
	
	}
	
}
