package hotel_mgt;
import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import java.util.ArrayList;
import java.util.List;

/* this class will act as database for the project
  It will contain list of requests , in progress and completed for the hotel 
  ex. Check-In requests , service requests and current guests. 
  
  Need to create only one object for this class. 
  
*/
public class hotel_database {

	// various static lists to model various data for hotel use
	
	 static List<Guest> hotel_guests= new ArrayList<Guest>();      // all guests record who has visited the hotel
	 static List<Guest> checkInreq= new ArrayList<Guest>();        // guests who has requested the check-In 
	 static List<Guest> checkOutreq= new ArrayList<Guest>();       // guests who has requested to check-out
	 static List<Guest> activeGuests= new ArrayList<Guest>();      // guests who are checked-In and staying
	 static List<resource> resources= new ArrayList<resource>();   // current resources provided by the hotel
	 static List<service> services= new ArrayList<service>();      // current services that guests can consume
	 static List<stay> hotel_stays= new ArrayList<stay>();         // List of stays for calculation of bills per users
 	 // function to check if Guest has already submitted check-In request
	 //contract
	 /**
	  * 
	  * @param g!=null;
	  * 
	  */
	 @Requires("g!=null")
	 public static boolean findIn_checkInreq(Guest g)   
	 {   
		 boolean res=false;
		
		 for(Guest act_g: activeGuests)    // check if guest is already staying in the hotel
		 {
			 if(g.get_name().equalsIgnoreCase(act_g.get_name()) && g.get_dob().equalsIgnoreCase(act_g.get_dob()) && g.get_contactno().equalsIgnoreCase(act_g.get_contactno()))
			 {
				 res=true;   //  unprocessed guest check-in request is already in the system
				 break;
			 }
		 }
		 
		 for(Guest sys_g: checkInreq)
		 {
			 if(g.get_name().contentEquals(sys_g.get_name()) && g.get_dob().equalsIgnoreCase(sys_g.get_dob()) && g.get_contactno().equalsIgnoreCase(sys_g.get_contactno()))
			 {
				 res=true;   //  unprocessed guest check-in request is already in the system
				 break;
			 }
		 }
		 return res;
	 }
	 //contract
	 /**
	  * 
	  * @param g!=null;
	  * 
	  */
	 
	@Requires("g!=null")
	 public static boolean findInactive(Guest g)
	 {
		 boolean res=false;
			
		 for(Guest act_g: activeGuests)    // check if guest is already staying in the hotel
		 {
			 if(g.get_name().equalsIgnoreCase(act_g.get_name()) && g.get_dob().equalsIgnoreCase(act_g.get_dob()) && g.get_contactno().equalsIgnoreCase(act_g.get_contactno()))
			 {
				 res=true;   //  unprocessed guest check-in request is already in the system
				 break;
			 }
		 }
		 return res;
	 }
	 
	 //contract
	 /**
	  * 
	  * @param g!=null;
	  * @param list!=null;
	  */
	@Requires({
		"g!=null",
		"list!=null"
	})
	@Ensures({
		"checkInreq.size()-old(checkInreq.size())<=1",
		"hotel_guests.size()-old(hotel_guests.size())<=1"
	})
	 public static boolean addIn_list(Guest g, String list)
	 {
		 boolean status= false;
		 if(list=="check-In")
		 {
			 checkInreq.add(g);
			 status=true;
		 }
		 else if(list=="new guest")
		 {
			 hotel_guests.add(g);
			 status=true;
		 }
		 return status;
	 }
	 //contract
	 /**
	  * 
	  * @return !=null;
	  */
	@Ensures("hotel_guests!=null")
	 public static List<Guest> get_guests(){
		 return hotel_guests;
	 }
}


