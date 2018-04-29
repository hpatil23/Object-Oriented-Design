package hotel_management;

import java.util.List;

public class employee {
private String emp_name;
private String id;

public employee(String e_name, String id)
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


public List<Guest> find_guest(int i)  // find a guest by his name and contact number
	{
	List<Guest> guests = null;
	if(i==0) {
	guests=hotel_database.get_guests();
	}
	else if(i==1)
	{
	guests=hotel_database.get_activeGuests();
	
	}
	return guests;
	}
}
