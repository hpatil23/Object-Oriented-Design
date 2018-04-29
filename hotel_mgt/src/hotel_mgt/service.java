package hotel_mgt;

import java.util.ArrayList;
import java.util.List;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

@Invariant("resources.size()>=0")
// services offered by the hotels
// list of all services will be stored in hotel_database
public class service {
 private String service_name;
 public List<resource> resources;
 
 service()
 {
	 resources=new ArrayList<resource>();
 }
 
 public String get_name()
 {
	 return service_name;
 }
 public void set_name(String name)
 {
	 service_name=name;
 }
 public void print_ResourceList()
 {
	 int i=1;
	 System.out.println("Service: "+service_name);
	 if(resources.isEmpty())
	 {
		 System.out.println("No Resource in this Service");
	 }
	 else 
	 	{
		 System.out.println("    Includes");
	      for(resource r: resources)
	       {   // print all resources included in service
		     System.out.println(" "+i+". "+ r.get_resourceName());
		     i++;
	        }
	 }
 }
 
 
 //contract
 /**
  * 
  * @pre r!=null;
  * @inv resources.size()>=0;
  * @post resources.size()==resources.size()@pre +1;
  */
 
 @Requires("r!=null")
 @Ensures("resources.size()-old(resources.size())<=1")
 public void add_resource(resource r)
 {
	 boolean check=false;
	 if(resources.isEmpty()==false) {
		 for(resource sys_r: resources)
	 
	 {
		 if(r.get_resourceName().equalsIgnoreCase(sys_r.get_resourceName()))
		 {
			 System.out.println("Resource already in Service");
			 check=true;
			 break;
		 }
	 }
	 }
	 else
	 {
		 check=false;
	 }
	 if(check==false)
	 {
		 resources.add(r);
		 System.out.println("Resource "+r.get_resourceName()+" added into service: "+service_name);
	 }
 }
 
 // contract
 /**
  * 
  * @pre r!=null;
  * @inv resources.szie()>=0;
  * @post resources.size()==resources.size()@pre-1;
  */
 @Requires("r!=null")
 @Ensures("old(resources.size)-resources.size<=1")
 public void remove_resource(resource r)
 {
	 boolean check;
	 check=resources.remove(r);
	 if(check==true)
	 {
		 System.out.println("Resource Removed Successfully");
	 }
	 else
	 {
		 System.out.println("Resource remove failed, Please try again");
	 }
	 
 }
	


}
