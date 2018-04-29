package hotel_management;


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
			 //System.out.println("Resource already in Service");
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
		 //System.out.println("Resource "+r.get_resourceName()+" added into service: "+service_name);
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
 //@Ensures("old(resources.size)-resources.size<=1")
 public void remove_resource(resource r)
 {
	 resources.remove(r);
	 
	 
 }
	


}
