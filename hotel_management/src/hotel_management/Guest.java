package hotel_management;

import java.time.LocalDate;

import com.google.java.contract.Ensures;

@SuppressWarnings("unused")
public class Guest {
 // private fields for guest details
 private String name;
 private String adrs;
 private LocalDate dob;   // pattern for MM-DD-YYYY
 private String contactno;    // ten digit contact number
 private LocalDate checkIn_date;
 private LocalDate checkOut_date;
 private String Service_Name;     // Service of the hotel avaled by the user
 private Reward_Points points;
 int len=0;
 // methods to access guest fields
 public Guest()
 {
	 points=new Reward_Points();
 }
 public String get_name()
 {
	 return name;
 }
 public String get_adrs()
 {
	 return adrs;
 }
 public LocalDate get_dob()
 {
	 return dob;
 }
 public String get_contactno()
 {
	 return contactno;
 }
 public LocalDate get_checkInDate()
 {
	 return checkIn_date; 
 }
 public LocalDate get_checkOut_date()
 {
	 return checkOut_date;
 }
 public String get_ServiceName()
 {
	 return Service_Name;
 }
 public Reward_Points get_points() {
	 return points;
 }

 public void set_name(String name)
 {
	this.name=name;
 }
 public void set_adrs(String adrs)
 {
	 this.adrs=adrs;
 }
 public void set_dob(LocalDate dob)
 {
	 this.dob=dob;
 }
 public void set_contactno(String num)
 {
	 contactno=num;
 }
 
 public void set_checkInDate(LocalDate date)
 {
	 checkIn_date=date;     // format MM-DD-YYYY
 }
 
 public void set_checkOutDate(LocalDate date)
 {
	 checkOut_date=date;    // format MM-DD-YYYY
 }

 public void set_serviceName(String ser_Name)
 {
	 Service_Name=ser_Name;
 }
 // hotel related functions
 
 
/*
 @Ensures({
	 "hotel_database.checkInreq.size()-old(hotel_database.checkInreq.size())<=1",
     "contactno.length()==10"
 })*/
 public int checkin_request()

 {
   len=hotel_database.checkInreq.size();
     // This will prevent duplicate entries in check-In guest list for same user 
   boolean check=hotel_database.findIn_checkInreq(this);
   
   if(check==false)  // check in request is new for the user
   {	
	   boolean checkIn_status=hotel_database.addIn_list(this,"check-In");
	   len=hotel_database.checkInreq.size();
	   if(checkIn_status==true)
	   {
		return 1;
	   }
	   else
	   {
		  return -1;
	   }
   }
   else
   {
	   return 0;
   }
   //System.out.println(check);
 }
//contract
/**
 * @post hotel_database.checkOutreq.size()=hotel_database.checkOutreq.size()@pre+1;
 */
 /*
 @Ensures("hotel_database.checkOutreq.size()-old(hotel_database.checkOutreq.size())<=1")*/
 public int checkOut_request()
 {
	  
	   boolean check=hotel_database.findInactive(this);
	  boolean checkOut=hotel_database.findInCheckOut(this);
	  
	  for(Guest g: hotel_database.activeGuests)
	  {
		  if(g.get_name().equalsIgnoreCase(this.get_name()))
		  {
			  if(g.get_dob().equals(this.get_dob()))
			  {
				  if(g.get_contactno().equalsIgnoreCase(this.get_contactno()))
				  {
					  if(g.get_checkInDate().isAfter(this.checkOut_date))
					  {
						  return -2;
					  }
				  }
			  }
		  }
	  }
	   if(check==true && checkOut==false)  // user found in active list, initiate check-out request
	   {
		   hotel_database.checkOutreq.add(this);
		  return 1;
	   }
	   else if(check==true && checkOut==true)
	   {
		   return -1;
	   }
	   else 
	   {
		  return 0;
	   }
}
 
 public int submit_feedback(String s)
 {
	 String feedback="Name: ";
	 feedback=feedback.concat(this.get_name());
	 feedback=feedback.concat(", Contact Num: ");
	 feedback=feedback.concat(this.get_contactno());
	 feedback=feedback.concat("#Feedback:");
	 feedback=feedback.concat(s);
	 feedback=feedback.concat("# ");
	 
	 int res=hotel_database.add_feedback(feedback);
	 if(res==1)
	 {
		 return 1;
	 }
	 else
	 {
		 return 0;
	 }
 }

}
