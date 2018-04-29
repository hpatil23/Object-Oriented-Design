package hotel_management.Guests;

import java.io.IOException;

import hotel_management.Guest;
import hotel_management.hotel_database;
import hotel_management.view.AlertBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class give_feedback {
	@FXML
	private TextField name;
	@FXML
	private TextField contactno;
	@FXML
	private TextArea feedback;
	 @FXML
	 public Button close;
	 @FXML
	 public Button submit;
	 
	 @FXML
	 private void close() throws IOException
	 {
		Stage st= (Stage) close.getScene().getWindow();
		st.close();
	 }
	 
	 @FXML
	 private void submit() throws IOException
	 {
		 String g_name=name.getText();
		 String g_contactno=contactno.getText();
		 String g_feedback=feedback.getText();
		 
		 if(g_name.isEmpty())
		 {
			 AlertBox.show("Please enter Name");
		 }
		 else if(g_contactno.isEmpty())
		 {
			 AlertBox.show("Please enter contact no");
		 }
		 else if(g_feedback.isEmpty())
		 {
			 AlertBox.show("Please enter feedback");
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
	    			// check if guest was in history of hotel 
	    			// take feedback only if guest was
	    			 // otherwise reject the feedback 
	    			 boolean check=false;
	    			 for(Guest g:hotel_database.get_guests())
	    			 {
	    				 if(g_name.equalsIgnoreCase(g.get_name())&&g_contactno.equalsIgnoreCase(g.get_contactno()))
	    				 {
	    					 check=true;
	    					 break;
	    				 }
	    			 }
	    			 
	    			 if(check==true)
	    			 {
	    				 Guest g1= new Guest();
	    				 g1.set_name(g_name);
	    				 g1.set_contactno(g_contactno);
	    				int res= g1.submit_feedback(g_feedback);
	    				
	    				if(res==1)
	    				{
	    					AlertBox.show("Feedback submitted");

	    					Stage st= (Stage) close.getScene().getWindow();
	    					st.close();
	    					
	    				}
	    				else if(res==0)
	    				{
	    					AlertBox.show("Feedback submission error");
	    				}
	    			 }
	    			 else
	    			 {
	    				 AlertBox.show("Guest not valid to give Feedback");
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
