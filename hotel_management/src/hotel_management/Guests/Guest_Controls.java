package hotel_management.Guests;




import java.io.IOException;

import hotel_management.Hotel_mgt;

import javafx.fxml.FXML;


public class Guest_Controls {
	
	

    @FXML
	private void get_guestDetails() throws IOException
	{
		
	    Hotel_mgt.get_guestInfo();  
	  
	}
    
    @FXML
    private void checkout_request() throws IOException
    {
    	Hotel_mgt.guest_checkout_req();
    }
	
	@FXML
	private void submit_feedback() throws IOException
	{
		Hotel_mgt.submit_feedback();
	}
}
