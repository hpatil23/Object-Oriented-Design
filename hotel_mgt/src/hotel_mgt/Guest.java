package hotel_mgt;
import com.google.java.contract.Ensures;

public class Guest {
 // private fields for guest details
 private String name;
 private String adrs;
 private String dob;   // pattern for MM-DD-YYYY
 private String contactno;    // ten digit contact number
 private String checkIn_date;
 private String checkOut_date;
 private String Service_Name;     // Service of the hotel avaled by the user
 int len=0;
 // methods to access guest fields
 public String get_name()
 {
	 return name;
 }
 public String get_adrs()
 {
	 return adrs;
 }
 public String get_dob()
 {
	 return dob;
 }
 public String get_contactno()
 {
	 return contactno;
 }
 public String get_checkInDate()
 {
	 return checkIn_date; 
 }
 public String get_checkOut_date()
 {
	 return checkOut_date;
 }
 public String get_ServiceName()
 {
	 return Service_Name;
 }

 public void set_name(String name)
 {
	this.name=name;
 }
 public void set_adrs(String adrs)
 {
	 this.adrs=adrs;
 }
 public void set_dob(String dob)
 {
	 this.dob=dob;
 }
 public void set_contactno(String num)
 {
	 contactno=num;
 }
 
 public void set_checkInDate(String date)
 {
	 checkIn_date=date;     // format MM-DD-YYYY
 }
 
 public void set_checkOutDate(String date)
 {
	 checkOut_date=date;    // format MM-DD-YYYY
 }

 public void set_serviceName(String ser_Name)
 {
	 Service_Name=ser_Name;
 }
 // hotel related functions
 
 //contract
 /**
  * @post hotel_database.checkInreq.size()=hotel_database.checkInreq.size()@pre+1;
 */

 @Ensures({
	 "hotel_database.checkInreq.size()-old(hotel_database.checkInreq.size())<=1",
     "contactno.length()==10"
 })
 public void checkin_request()

 {
   len=hotel_database.checkInreq.size();
   System.out.println("Guest Check-In Request Started");
   System.out.println("Enter Name: ");
   set_name(hotel_mgt.readLine());
   System.out.println("Enter Address: ");
   set_adrs(hotel_mgt.readLine());
   System.out.println("Enter Date of Birth (MM-DD-YYYY): ");
   set_dob(hotel_mgt.readLine());
   System.out.println("Enter Contact number (10 digits): ");
   set_contactno(hotel_mgt.readLine());
   System.out.println("Enter Check-In date (MM-DD-YYYY): ");
   set_checkInDate(hotel_mgt.readLine());
   
   
   
   // check if check_in request by the user is already in the system or not. 
   // This will prevent duplicate entries in check-In guest list for same user 
   boolean check=hotel_database.findIn_checkInreq(this);
   
   if(check==false)  // check in request is new for the user
   {	
	   boolean checkIn_status=hotel_database.addIn_list(this,"check-In");
	   len=hotel_database.checkInreq.size();
	   if(checkIn_status==true)
	   {
		// Show services to Guest to select
		   System.out.println("Please select any one service from below");
		   int i=1;
		   for(service s:hotel_database.services)
		   {
			     System.out.print(i+".");
				 s.print_ResourceList();   // function to print name of service as well as resources inside it 
				 i++;
			   
		   }
		   System.out.println("Enter Service Choice: ");
		   int n=Integer.parseInt(hotel_mgt.readLine());
		   set_serviceName(hotel_database.services.get(n-1).get_name());  // setting service name for guest
		   System.out.println("Check-In request submitted sucessfully");
	   }
	   else
	   {
		   System.out.println("Error in request submission, please try again");
	   }
   }
   else
   {
	   System.out.println("Request Denied: check-In already submitted or Completed");
   }
   //System.out.println(check);
 }
//contract
/**
 * @post hotel_database.checkOutreq.size()=hotel_database.checkOutreq.size()@pre+1;
 */
 
 @Ensures("hotel_database.checkOutreq.size()-old(hotel_database.checkOutreq.size())<=1")
 public void checkOut_request()
 {
	   System.out.println("Guest Check-Out Request Started");
	   System.out.println("Enter Name: ");
	   set_name(hotel_mgt.readLine());
	   System.out.println("Enter Date of Birth (MM-DD-YYYY): ");
	   set_dob(hotel_mgt.readLine());
	   System.out.println("Enter Contact number (10 digits): ");
	   set_contactno(hotel_mgt.readLine());
	   boolean check=hotel_database.findInactive(this);
	   if(check==true)  // user found in active list, initiate check-out request
	   {
		   hotel_database.checkOutreq.add(this);
		   System.out.println("Check-Out Request Submitted Sucessfully");   
	   }
	   else 
	   {
		   System.out.println("Guest with given Data not found");
	   }
}

}
