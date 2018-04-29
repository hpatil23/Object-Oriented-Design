package hotel_mgt;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

@Invariant("hotel_database.services.size()>=0")
public class manager extends employee {
	
	manager(String name, String id){
		super(name,id);
	}
	
 public void list_service() {
	 int i=1;
	 for(service s:hotel_database.services)
	 {
		 System.out.print(i+".");
		 s.print_ResourceList();   // function to print name of service as well as resources inside it 
		 i++;
	 }

 }
 
 //contract
 /**
  * 
  * @pre ser!=null;
  *  
  */
 @Requires("ser!=null")
 public boolean find_service(String ser) {
	 
	 boolean found=false;
	 for(service s:hotel_database.services)
	 {
		 if(ser.equalsIgnoreCase(s.get_name()))
		 {
			 s.print_ResourceList();    // printing the service with its resources
			 found=true;
			 break;
		 }
	 }

	 return found;
 }
 
 // contract 
 /**
  * @inv hotel_database.services.size()>=0;
  * @post hotel_database.services.size()=hotel_database.services.size()@pre +1;
  */
 
@Ensures("hotel_database.services.size()-old(hotel_database.services.size())<=1")
 public void add_service()       // adds new service (unique by Name) to hotel system
 {
	 System.out.println("Enter new Service Name: ");
	 String name=hotel_mgt.readLine();
	// check if service with same name is already present or not
	 boolean check_ser=find_service(name);
	 
	 if(check_ser==false) {
		 System.out.println("Please enter the index of resources to add in Service Ex ( 1 3 4): ");
		 int i=1;
		 for(resource r: hotel_database.resources)
		 {
			 System.out.println("  "+i+". :"+r.get_resourceName());
			 i++;
		 }
		 String ind=hotel_mgt.readLine();   // space separated index of resources for new service
		 String[] inx=ind.split(" ");
	 // creating service object
	  service new_s = new service();
	  new_s.set_name(name);
	  int res_size=hotel_database.resources.size();
	  for(String i_r: inx)
	  {
		  int a=Integer.parseInt(i_r);
		  if(a>res_size)
		  {
			  System.out.println("Resource Index Seems out of bound, Skiping this index");
		  }
		  else {
			  new_s.add_resource(hotel_database.resources.get(a-1));
		  }
	  }
	 
	  // adding service into hotel database service list
	  
	  hotel_database.services.add(new_s);
	  System.out.println("New Service added into the system");
	 }
	 else
	 {
		 System.out.println("Service already in System");
	 }
 }
 //contract
 /**
  * @inv hotel_database.services.size()>=0;
  * @post hotel_database.services.size()=hotel_database.services.size()@pre -1;
  */
@Ensures("old(hotel_database.services.size())-hotel_database.services.size()<=1")
 public void remove_service()
 {
	 System.out.println("Enter Service Name to remove");
	 String name=hotel_mgt.readLine();
	 boolean check=find_service(name);
	 if(check==false)
	 {
		 System.out.println("Service not found");
	 }
	 else 
	 {
		 for(service s:hotel_database.services)
		 {
			 if(s.get_name().equalsIgnoreCase(name))
			 {
				 hotel_database.services.remove(s);
				 System.out.println("Service Removed from the System");
				 break;
			 }
		 }
	 }
 }

}
