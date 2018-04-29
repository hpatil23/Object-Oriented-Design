package hotel_mgt;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class GuestTest {

	
	@BeforeClass
	  public static void hotel_init() {	   
		   // initial resources in restaurent 
		   String[] resNames= {"Room","Food", "Casino","Pool"};
		   double[] resCost= {200,100,500,50};
		   for(int i=0;i<4;i++)   // Adding resources into hotel resources list in hotel_database
		   {
			   resource res= new resource();
			   res.set_resourceName(resNames[i]);
			   res.set_resourceCost(resCost[i]);
			   hotel_database.resources.add(res);
		   }
		   
		   // initial 3 services offered by hotel, added for the demo.
		   String[] serName= {"Basic Room","Delux","Premium"};
		   for(int i=0;i<3;i++)
		   {
			   service s= new service();
			   s.set_name(serName[i]);
			   hotel_database.services.add(s);
		   }
		   
		   // Adding required Resources to first 3 Services
		   //1
		   hotel_database.services.get(0).add_resource(hotel_database.resources.get(0));
		   
		   //2
		   hotel_database.services.get(1).add_resource(hotel_database.resources.get(0));
		   hotel_database.services.get(1).add_resource(hotel_database.resources.get(1));
		   
		   //3
		   hotel_database.services.get(2).add_resource(hotel_database.resources.get(0));
		   hotel_database.services.get(2).add_resource(hotel_database.resources.get(1));
		   hotel_database.services.get(2).add_resource(hotel_database.resources.get(2));
		   hotel_database.services.get(2).add_resource(hotel_database.resources.get(3));
	}
	@Test
	public void test_checkIn() {
		//fail("Not yet implemented");
		//hotel_init();
		Guest g= new Guest();
		int count_bef=hotel_database.checkInreq.size();
		g.checkin_request();
		int count_aft=hotel_database.checkInreq.size();
		
		assertEquals(1,count_aft-count_bef);
	}
}
