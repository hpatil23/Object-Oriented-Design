package hotel_management;


import java.util.ArrayList;
import java.util.List;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

@Invariant("hotel_database.services.size()>=0")
public class manager extends employee {
	
	public manager(String name, String id){
		super(name,id);
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
	//		 s.print_ResourceList();    // printing the service with its resources
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
 public int add_service(String name,List<Integer> res_ind)       // adds new service (unique by Name) to hotel system
 {
	
	// check if service with same name is already present or not
	 boolean check_ser=find_service(name);
	 
	 if(check_ser==false) {
	 // creating service object
	  service new_s = new service();
	  new_s.set_name(name);
	  for (Integer i:res_ind)
	  {
		  new_s.add_resource(hotel_database.resources.get(i));
	  }
	  // adding service into hotel database service list
	  
	  hotel_database.services.add(new_s);
	  return 1;
	 }
	 else
	 {return 0;
		
	 }
	 
 }
 //contract
 /**
  * @inv hotel_database.services.size()>=0;
  * @post hotel_database.services.size()=hotel_database.services.size()@pre -1;
  */
@Ensures("old(hotel_database.services.size())-hotel_database.services.size()<=1")
 public int remove_service(List<Integer> servNo)
 {
	List<service> to_delete= new ArrayList<service>();
	for(Integer i:servNo)
	{
		to_delete.add(hotel_database.services.get(i));
	}
	for(service s: to_delete)
	{
		boolean res=hotel_database.services.remove(s);
		if(res==false)
		{
			return 0;
		}
	}
	
	return 1;
 }
public int remove_resource(List<Integer> resNo)
{
	List<resource> to_delete= new ArrayList<resource>();
	for(Integer i: resNo)
	{
		to_delete.add(hotel_database.resources.get(i));
	}
	
	for(resource r: to_delete)
	{
		for(service s: hotel_database.services)
		{
			s.remove_resource(r);
		}
	}
	for(resource r: to_delete)
	{
		boolean res=hotel_database.resources.remove(r);
		if(res==false)
		{
			return 0;
		}
	}
	
	
return 1;
}
public int add_resource(String name, double cost)
{
	resource r= new resource();
	r.set_resourceName(name);
	r.set_resourceCost(cost);
	for(resource res:hotel_database.resources)
	{
		if(res.get_resourceName().equals(name))
		{
			return 0;
		}
		
	}
	hotel_database.resources.add(r);
	return 1;
}

}
