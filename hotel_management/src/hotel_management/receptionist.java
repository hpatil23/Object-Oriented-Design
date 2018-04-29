package hotel_management;


import java.util.ArrayList;
import java.util.List;

import com.google.java.contract.Ensures;


public class receptionist extends employee{
	
	public receptionist (String name, String id){
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
	public int checkIn_guest( List<Integer> guestInd) {
		try {
		// create a temporary Guest object to store details.
		List<Guest> to_checkIn= new ArrayList<Guest>();      // list of guests required to check-in
		List<Guest> to_add=new ArrayList<Guest>();
		for(Integer i: guestInd)
		{
			to_checkIn.add(hotel_database.checkInreq.get(i));
		}
		// adding new guests to Hotel_database
		for(Guest g: to_checkIn)
		{
			if(hotel_database.hotel_guests.isEmpty())
			{
				hotel_database.hotel_guests.add(g);
			}
			else {
			for(Guest sys_g:hotel_database.hotel_guests)
			{
				if(!(sys_g.get_name().equalsIgnoreCase(g.get_name())&& sys_g.get_dob().equals(g.get_dob())&& sys_g.get_contactno().equalsIgnoreCase(g.get_contactno())))
				{
					to_add.add(g);
				}
			}
			}
		}
		
		for(Guest p:to_add)
		{
			hotel_database.hotel_guests.add(p);
		}
		// adding guests to active guests and removing from waiting list
		for(Guest g:to_checkIn)
		{
		hotel_database.activeGuests.add(g);
		hotel_database.checkInreq.remove(g);
		
		}
		return 1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
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
	public int checkOut_guest(List<Integer> index)
	{
		try {
		// create a temporary Guest object to store details.
				List<Guest> to_checkOut= new ArrayList<Guest>();      // list of guests required to check-in
				for(Integer i: index)     // get all the guests who need to check out
				{
					to_checkOut.add(hotel_database.checkOutreq.get(i));
				}
				
			// remvoing from the active guest list
				List<Guest> to_remove=new ArrayList<Guest>();
				
				for(Guest g: to_checkOut)
				{
					for(Guest act_g:hotel_database.activeGuests)
					{
						if(g.get_name().equalsIgnoreCase(act_g.get_name())&& g.get_contactno().equalsIgnoreCase(act_g.get_contactno())&& g.get_dob().equals(act_g.get_dob()))
						{
							to_remove.add(act_g);
						}
					}
				}
		    
				// remove to_remove from the active list
				// remove to_checkout from the checkout list
				stay stay_guest=new stay();
				for(Guest r: to_remove)
				{
					hotel_database.activeGuests.remove(r);
					stay_guest.set_guest(r);
					stay_guest.set_status(false);    // make it true in bill generation 
				}
				for(Guest l: to_checkOut)
				{
					stay_guest.get_guest().set_checkOutDate(l.get_checkOut_date());  // setting up checkout day in stay
					hotel_database.checkOutreq.remove(l);
				}
				hotel_database.hotel_stays.add(stay_guest);
				System.out.println(hotel_database.hotel_stays.size());
				return 1;
		}catch(Exception e)
		{
		return 0;
		}
	}
	
}
