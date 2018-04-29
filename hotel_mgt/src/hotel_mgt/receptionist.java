package hotel_mgt;

import com.google.java.contract.Ensures;

public class receptionist extends employee{
	
	receptionist (String name, String id){
		super(name,id);
	}
//contract
	/**
	 * @post hotel_database.activeGuests.size()=hotel_database.activeGuests.size()@pre +1;
	 * @post hotel_database.checkInreq.size()=hotel_database.checkInreq.size()@pre-1;
	 */
	@Ensures({
		"hotel_database.activeGuests.size()-old(hotel_database.activeGuests.size())<=1",
		"old(hotel_database.checkInreq.size())-hotel_database.checkInreq.size()<=1"
	})
	public void checkIn_guest() {
		if(hotel_database.checkInreq.isEmpty()==false)
		{
			System.out.println("Lists of Guests to Check-In");
		
		int i=1;
		for(Guest g:hotel_database.checkInreq)
		{
			System.out.println(" "+i+". Name: "+g.get_name()+" Contact#: "+g.get_contactno());
			i++;
		}
		System.out.println("Select the Guest: ");
		int n=Integer.parseInt(hotel_mgt.readLine());
		if(n>hotel_database.checkInreq.size())
		{
			System.out.println("Wrong Guest Number selection");
		}
		else {
		// create a temporary Guest object to store details.
		Guest temp= new Guest();
		temp=hotel_database.checkInreq.get(n-1);    // store the guest from check-in request array
		boolean check=false;
		
		for(Guest sys_g:hotel_database.hotel_guests)
		{
			if(sys_g.get_name().equalsIgnoreCase(temp.get_name())&& sys_g.get_dob().equalsIgnoreCase(temp.get_dob())&& sys_g.get_contactno().equalsIgnoreCase(temp.get_contactno()))
			{
				System.out.println("Guest history present");
				check=true;
				break;
			}
		}
		if(check==false)
		{
			System.out.println("No previous Guest History, Adding into the system");
			hotel_database.hotel_guests.add(temp);
		}
		System.out.println("");
		// removing the guest from the check-in request list
		hotel_database.checkInreq.remove(n-1);
		
		// Adding Guest in Active Guests list
		hotel_database.activeGuests.add(temp);
		System.out.println("Guest Check-In Succesful");
		}
		}
		else
		{
			System.out.println("No Request to Check-In");
		}
	}
	
	public void list_activeGuests() {
		if(hotel_database.activeGuests.isEmpty())
		{
			System.out.println("No Active Guests ");
		}
		else
		{
			int i=1;
			System.out.println("List of Current Guests: ");
			for(Guest g: hotel_database.activeGuests)
			{
				System.out.println(" "+i+". Name: "+g.get_name()+" Contact#: "+g.get_contactno());
				i++;
			}
		}
	}
	
	//contract 
	/**
	 * @post hotel_database.activeGuests.size()=hotel_database.activeGuests.size()@pre-1;
	 * @post hotel_database.activeGuests.size()>=0;
	 */
	@Ensures({
		"old(hotel_database.activeGuests.size())-hotel_database.activeGuests.size()<=1",
		"hotel_database.activeGuests.size()>=0"
	})
	public void checkOut_guest()
	{
		if(hotel_database.checkOutreq.isEmpty()==false)
		{
			System.out.println("Lists of Guests to Check-Out");
			
			int i=1;
			for(Guest g:hotel_database.checkOutreq)
			{
				System.out.println(" "+i+". Name: "+g.get_name()+" Contact#: "+g.get_contactno());
				i++;
			}
			
			System.out.println("Select the Guest: ");
			int n=Integer.parseInt(hotel_mgt.readLine());
			if(n>hotel_database.checkOutreq.size())
			{
				System.out.println("Wrong Guest Number selection");
			}
			else
			{
				// create a temporary Guest object to store details.
				Guest temp= new Guest();
				temp=hotel_database.checkOutreq.get(n-1);
				// removing the guest from the active guest list
				for(Guest g: hotel_database.activeGuests)
				{
					if(temp.get_name().equalsIgnoreCase(g.get_name())&& temp.get_contactno().equalsIgnoreCase(g.get_contactno()))
				{
					hotel_database.activeGuests.remove(g);
					break;
				}
				}
				// removing guest from active check-out request list
				hotel_database.checkOutreq.remove(n-1);
				System.out.println("Check-out Processed");
				System.out.println("Implemenetation of bill, and reward points is left");
			}
		}
		else
		{
			System.out.println("No Request to Check-Out");
		}
	}
}
