package hotel_mgt;
import com.google.java.contract.Requires;

import java.util.ArrayList;
import java.util.List;

public class employee {
private String emp_name;
private String id;

employee(String e_name, String id)
{
	emp_name=e_name;
	this.id=id;
}

public String get_name()
{
	return emp_name;
}
public String get_id()
{
	return id;
}

//contract
/**
 * 
 * @param name!=null
 */

@Requires("name!=null")
public void find_guest(String name)  // find a guest by his name and contact number
	{
	
	List<Guest> list=hotel_database.get_guests();
	List<Guest> g_found= new ArrayList<Guest>();   
	for(Guest g: list)
	{
		if(name.equalsIgnoreCase(g.get_name()))
		{
			g_found.add(g);
		}
	}
	
	if(g_found.isEmpty())
	{
		System.out.println("Guest not found with Name: "+name);
	}
	else  //display all the guest found
	{
		System.out.println("Total Guest found: "+g_found.size());
		int i=1;
		for(Guest g:g_found)
		{     
			System.out.println(" "+(i)+". Name: "+g.get_name()+"  Contact No: "+g.get_contactno());
		}
	
	}

	}
}
