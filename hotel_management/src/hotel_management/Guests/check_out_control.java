package hotel_management.Guests;

import java.io.IOException;
import java.time.LocalDate;

import hotel_management.Guest;
import hotel_management.view.AlertBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class check_out_control {
	@FXML
	private TextField name;
	@FXML 
	private TextField contactno;
	@FXML
	private DatePicker DOB;
	@FXML
	private DatePicker checkOutDate;
	 @FXML
	 public Button submit;
	 @FXML
	 public Button close;
	 
	 @FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
	 
	 @FXML
	 private void submit() throws IOException
	 {
		 int result=-99;      // initial negative initialization
		 String g_name=name.getText();
		 String g_contactno=contactno.getText();
		 LocalDate dob;
	     LocalDate checkOut;
		 if(g_name.isEmpty())
		 {
			 AlertBox.show("Please enter Name");
		 }
		 else if(g_contactno.isEmpty())
		 {
			 AlertBox.show("Please enter contact no");
		 }
		 else
		 {
			 try {
				 long num=Long.parseUnsignedLong(g_contactno);
	    		 if(num<1000000000L || num>9999999999L)
	    		 {
	    			 AlertBox.show("Contact # should be 10 digits long");
	    		 }
	    		 else
	    		 {
	    			 dob=DOB.getValue();
	    			 checkOut=checkOutDate.getValue();
	    			 if(dob.equals(checkOut)||dob.isAfter(checkOut))
	    			 {
	    				 AlertBox.show("Conflict in DOB and CheckIn date");
	    			 }
	    			 else 
	    			 {
	    				 // Proceed with check-out
	    				 Guest g=new Guest();
	    				 g.set_name(g_name);
	    				 g.set_contactno(g_contactno);
	    				 g.set_dob(dob);
	                     g.set_checkOutDate(checkOut);
	    				 result= g.checkOut_request();  // request is initiated
	    				 
	    				 // check the return for AlertBox
	    				 if(result==1)
	    				 {
	    					 AlertBox.show("Check-Out Request Submitted Sucessfully");
	    					 Stage st= (Stage) close.getScene().getWindow();
	    						st.close();
	    				 }
	    				 else if(result==0)
	    				 {
	    					 AlertBox.show("Guest is not Checked-In");
	    				 }
	    				 else if(result==-1)
	    				 {
	    					 AlertBox.show("Request Already Submitted");
	    				 }
	    				 else if(result==-2)
	    				 {
	    					 AlertBox.show("Check-out date cannot be before Check-In date");
	    				 }
	    				
	    			 }
	    		 }
			 }
			 catch(Exception e)
			 {
				AlertBox.show("Contact no is Wrong");
	
			 }
		 }
	 }

}
