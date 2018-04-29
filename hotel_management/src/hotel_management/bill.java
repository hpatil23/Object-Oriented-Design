package hotel_management;
import java.time.temporal.ChronoUnit;
public class bill {
	public String guest_Name;
	public double generate_bill(Guest g)
	{
		long stay_duration=ChronoUnit.DAYS.between(g.get_checkInDate(),g.get_checkOut_date());
		double single_day_cost=0;
		for(service s: hotel_database.services)
		{
			if(s.get_name().equalsIgnoreCase(g.get_ServiceName()))
			{
				for(resource r:s.resources)
				{
					single_day_cost=single_day_cost+r.get_resourceCost();
				}
			}
		}
		
		if(stay_duration==0)
		{
			stay_duration=1;  //if guest leaves hotel befor 24 hrs of stay, still he needs to pay full bill of one day
		}
		g.get_points().update_points((int)stay_duration*20);
		return stay_duration*single_day_cost;
	}

}
