package hotel_mgt;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class managerTest {

	@Test
	public void test_addService() {

		//hotel_init();
		//fail("Not yet implemented");
		manager m= new manager("m","101");
		int bef_serv=hotel_database.services.size();
		m.add_service();
		int afr_serv=hotel_database.services.size();
		assertEquals(1,afr_serv-bef_serv);
	}
	
	@Test
	public void test_RemoveService()
	{
	//	hotel_init();
		manager m= new manager("m","101");
		int bef_serv=hotel_database.services.size();
		m.remove_service();
		int afr_serv=hotel_database.services.size();
		assertEquals(1,bef_serv-afr_serv);
	}
	@Test
	public void test_FindService()
	{
		manager m= new manager("m","101");
		boolean res=m.find_service("Delux");
		assertEquals(true,res);
		
	}
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
}
